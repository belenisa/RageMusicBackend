package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.EstadoVenta;

@Repository
public interface EstadoVentaRepositorio extends JpaRepository<EstadoVenta, Integer>{

}
