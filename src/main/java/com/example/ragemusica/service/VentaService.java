// com.example.ragemusica.service.VentaService
package com.example.ragemusica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.EstadoVenta;
import com.example.ragemusica.model.Producto;
import com.example.ragemusica.model.Venta;
import com.example.ragemusica.repository.EstadoVentaRepositorio;
import com.example.ragemusica.repository.ProductoRepository;
import com.example.ragemusica.repository.VentaRepositorio; // 💡 IMPORTACIÓN AGREGADA

import jakarta.persistence.EntityNotFoundException;

@Transactional
@Service
@SuppressWarnings("null")
public class VentaService {

    private final VentaRepositorio repo;
    private final EstadoVentaRepositorio estadoRepo;
    private final ProductoRepository productoRepo; // 💡 DEPENDENCIA AGREGADA

    // 💡 CONSTRUCTOR ACTUALIZADO PARA INCLUIR PRODUCTOREPOSITORY
    public VentaService(VentaRepositorio repo, EstadoVentaRepositorio estadoRepo, ProductoRepository productoRepo) {
        this.repo = repo;
        this.estadoRepo = estadoRepo;
        this.productoRepo = productoRepo;
    }

    public List<Venta> getVenta() {
        return repo.findAll();
    }

    // 💡 MÉTODO SAVE VENTA CORREGIDO
    public Venta saveVenta(Venta venta) {
        validarVenta(venta);
        
        // --- 💡 LÓGICA DE CARGA Y DESCUENTO DE STOCK AÑADIDA ---
        
        // 1. Cargar el Producto COMPLETO por su ID para obtener Precio y Stock
        // Esto resuelve el NullPointerException al acceder a venta.getProducto().getPrecio()
        Producto productoReal = productoRepo.findById(venta.getProducto().getId())
            .orElseThrow(() -> new EntityNotFoundException("Producto (id=" + venta.getProducto().getId() + ") no existe."));
            
        venta.setProducto(productoReal); // Asignar el objeto Producto completo a la Venta

        // 2. Verificar Stock
        if (productoReal.getStock() < venta.getCantidad()) {
             throw new IllegalArgumentException("Stock insuficiente para el producto: " + productoReal.getNombre() + 
                                               ". Stock disponible: " + productoReal.getStock());
        }
        
        // 3. Descontar Stock y Guardar Producto
        productoReal.setStock(productoReal.getStock() - venta.getCantidad());
        productoRepo.save(productoReal); // Persistir el cambio de stock
        
        // --- FIN LÓGICA DE STOCK ---

        // Calcular precio total (ahora funciona porque productoReal está cargado)
        calcularPrecioTotal(venta); 

        // Asignar el estado "PENDIENTE" (ID 1)
        EstadoVenta estadoPendiente = estadoRepo.findById(1)
            .orElseThrow(() -> new EntityNotFoundException("Estado PENDIENTE (id=1) no existe en BD"));
        venta.setEstadoVenta(estadoPendiente);

        return repo.save(venta);
    }

    public Venta getVentaId(int id) {
        return repo.findById(id).orElse(null);
    }

    public Venta updateVenta(Venta venta) {
        validarVenta(venta);
        calcularPrecioTotal(venta);
        return repo.save(venta);
    }

    public boolean deleteVenta(int id) {
        Optional<Venta> venta = repo.findById(id);
        if (venta.isPresent()) {
            repo.delete(venta.get());
            return true;
        }
        return false;
    }

    private void validarVenta(Venta venta) {
        if (venta == null) throw new IllegalArgumentException("La venta no puede ser null");
        if (venta.getProducto() == null) throw new IllegalArgumentException("La venta debe tener un producto");
        if (venta.getProducto().getId() == null || venta.getProducto().getId() <= 0) throw new IllegalArgumentException("El producto debe tener un ID válido"); // 💡 VALIDACIÓN ADICIONAL DE ID
        if (venta.getCantidad() <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        if (venta.getPago() == null) throw new IllegalArgumentException("Debe indicar método de pago");
        if (venta.getEnvio() == null) throw new IllegalArgumentException("Debe indicar método de envío");
        if (venta.getUsuario() == null) throw new IllegalArgumentException("Debe indicar el usuario");
    }

    private void calcularPrecioTotal(Venta venta) {
        if (venta.getProducto() != null && venta.getCantidad() > 0) {
            // Se asume que venta.getProducto() ya es el objeto cargado y contiene el precio.
            double precioUnitario = venta.getProducto().getPrecio();
            double total = precioUnitario * venta.getCantidad();
            venta.setPrecioTotal(total);
        } else {
            venta.setPrecioTotal(0.0);
        }
    }
}