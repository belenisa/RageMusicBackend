package com.example.ragemusica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Venta;
import com.example.ragemusica.repository.VentaRepositorio;

@Transactional
@Service
@SuppressWarnings("null")
public class VentaService {

    @Autowired
    private  VentaRepositorio repo;

    public List<Venta> getVenta() {
        return repo.findAll();
    }

    public Venta saveVenta(Venta venta) {
        calcularPrecioTotal(venta);
        return repo.save(venta);
    }

     public Venta getVentaId(int id) {
        return repo.findById(id).orElse(null);
    }

    public Venta updateVenta(Venta venta) {
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


    private void calcularPrecioTotal(Venta venta) {
        if (venta.getProducto() != null && venta.getCantidad() > 0) {
            double precioUnitario = venta.getProducto().getPrecio();
            double total = precioUnitario * venta.getCantidad();
            venta.setPrecioTotal(total); // total es double
        } else {
            venta.setPrecioTotal(0.0);
        }
    }
    
}
