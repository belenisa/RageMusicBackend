
// com.example.ragemusica.service.VentaService
package com.example.ragemusica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.EstadoVenta;
import com.example.ragemusica.model.Venta;
import com.example.ragemusica.repository.EstadoVentaRepositorio;
import com.example.ragemusica.repository.VentaRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Transactional
@Service
@SuppressWarnings("null")
public class VentaService {

    private final VentaRepositorio repo;
    private final EstadoVentaRepositorio estadoRepo;

    public VentaService(VentaRepositorio repo, EstadoVentaRepositorio estadoRepo) {
        this.repo = repo;
        this.estadoRepo = estadoRepo;
    }

    public List<Venta> getVenta() {
        return repo.findAll();
    }

    public Venta saveVenta(Venta venta) {
        validarVenta(venta);
        calcularPrecioTotal(venta);

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
        if (venta.getCantidad() <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        if (venta.getPago() == null) throw new IllegalArgumentException("Debe indicar método de pago");
        if (venta.getEnvio() == null) throw new IllegalArgumentException("Debe indicar método de envío");
        if (venta.getUsuario() == null) throw new IllegalArgumentException("Debe indicar el usuario");
    }

    private void calcularPrecioTotal(Venta venta) {
        if (venta.getProducto() != null && venta.getCantidad() > 0) {
            double precioUnitario = venta.getProducto().getPrecio();
            double total = precioUnitario * venta.getCantidad();
            venta.setPrecioTotal(total); // Nota: considera BigDecimal en el futuro
        } else {
            venta.setPrecioTotal(0.0);
        }
    }
}
