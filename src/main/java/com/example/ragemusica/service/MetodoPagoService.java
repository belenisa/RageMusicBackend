package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ragemusica.model.MetodoPago;
import com.example.ragemusica.repository.MetodoPagoRepositorio;

import jakarta.transaction.Transactional;

@Transactional
@Service
@SuppressWarnings("null")
public class MetodoPagoService {

    
    private final  MetodoPagoRepositorio metodoRepositorio;

    public MetodoPagoService(MetodoPagoRepositorio metodoRepositorio) {
        this.metodoRepositorio = metodoRepositorio;
    }

    public List<MetodoPago> findAll() {
        return metodoRepositorio.findAll();
    }

    public MetodoPago findById(Integer id) {
        return metodoRepositorio.findById(id).orElse(null);
    }

    public MetodoPago save(MetodoPago metodoPago) {
        return metodoRepositorio.save(metodoPago);
    }

    public MetodoPago update(MetodoPago metodoPago) {
        return save(metodoPago);
    }

    public void deleteById(Integer id) {
        metodoRepositorio.deleteById(id);
    }

}