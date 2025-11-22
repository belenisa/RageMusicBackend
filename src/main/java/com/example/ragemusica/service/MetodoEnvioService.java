package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.MetodoEnvio;
import com.example.ragemusica.repository.MetodoEnvioRepositorio;

@Transactional
@Service
@SuppressWarnings("null")
public class MetodoEnvioService {

    private final  MetodoEnvioRepositorio envioRepositorio;

    public MetodoEnvioService(MetodoEnvioRepositorio envioRepositorio) {
        this.envioRepositorio = envioRepositorio;
    }

    public List<MetodoEnvio> findAll() {
        return envioRepositorio.findAll();
    }

    public MetodoEnvio findById(Integer id) {
        return envioRepositorio.findById(id).orElse(null);
    }

    public MetodoEnvio save(MetodoEnvio metodoEnvio) {
        return envioRepositorio.save(metodoEnvio);
    }

    public MetodoEnvio update(MetodoEnvio metodoEnvio) {
        return save(metodoEnvio);
    }

    public void deleteById(Integer id) {
        envioRepositorio.deleteById(id);
    }


}
