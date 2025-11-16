package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ragemusica.model.SubGenero;
import com.example.ragemusica.repository.SubGeneroRepository;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@SuppressWarnings("null")
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

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
