package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ragemusica.model.TipoProducto;
import com.example.ragemusica.repository.TipoProductoRepository;

@Service
public class TipoProductoService {

    private final TipoProductoRepository repo;

    public TipoProductoService(TipoProductoRepository repo) {
        this.repo = repo;
    }

    public List<TipoProducto> listar() {
        return repo.findAll();
    }

    public TipoProducto guardar(TipoProducto tipo) {
        return repo.save(tipo);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
