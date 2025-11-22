package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.EstadoVenta;
import com.example.ragemusica.repository.EstadoVentaRepositorio;

@Service
@SuppressWarnings("null")
@Transactional
public class EstadoVentasService {

    @Autowired
    private EstadoVentaRepositorio estadoRepositorio;

    public List<EstadoVenta> findAll() {
        return estadoRepositorio.findAll();
    }

    public EstadoVenta findById(Integer id) {
        return estadoRepositorio.findById(id).orElse(null);
    }

    public EstadoVenta save(EstadoVenta estado) {
        return estadoRepositorio.save(estado);
    }

    public void deleteById(Integer id) {
        estadoRepositorio.deleteById(id);
    }

}

