package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.Venta;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Integer> {

    @Query("SELECT SUM(d.precioTotal) FROM Venta d")
    Double calcularTotalGlobal();
}
