package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.MetodoPago;

@Repository
public interface MetodoPagoRepositorio extends JpaRepository<MetodoPago, Integer>{

}
