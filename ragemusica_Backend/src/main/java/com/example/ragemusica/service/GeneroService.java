package com.example.ragemusica.service;

import com.example.ragemusica.model.Genero;
import com.example.ragemusica.repository.GeneroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private final GeneroRepository repo;

    public GeneroService(GeneroRepository repo) {
        this.repo = repo;
    }

    public List<Genero> listar() {
        return repo.findAll();
    }

    public Genero guardar(Genero genero) {
        return repo.save(genero);
    }
}
