package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.TipoProducto;
import com.example.ragemusica.repository.TipoProductoRepository;


@SuppressWarnings("null")
@Service
public class TipoProductoService {

    @Autowired
    private final TipoProductoRepository repo;

    public TipoProductoService(TipoProductoRepository repo) {
        this.repo = repo;
    }

    public List<TipoProducto> findAll() {
        List<TipoProducto> tipoProductos = repo.findAll();
        return tipoProductos;
    }

    public TipoProducto findById(Integer id) {
        TipoProducto tipoProducto = repo.findById(id).orElse(null);
        return tipoProducto;
    }


    public TipoProducto guardar(TipoProducto tipo) {
        return repo.save(tipo);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
