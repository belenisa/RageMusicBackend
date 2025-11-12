package com.example.ragemusica.service;

import com.example.ragemusica.model.SubGenero;
import com.example.ragemusica.repository.SubGeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubGeneroService {

    private final SubGeneroRepository repo;

    public SubGeneroService(SubGeneroRepository repo) {
        this.repo = repo;
    }

    public List<SubGenero> listar() {
        return repo.findAll();
    }

    public SubGenero guardar(SubGenero subGenero) {
        return repo.save(subGenero);
    }
}
