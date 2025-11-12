package com.example.ragemusica.service;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository repo;

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public List<Artista> listar() {
        return repo.findAll();
    }

    public Artista guardar(Artista artista) {
        return repo.save(artista);
    }
}
