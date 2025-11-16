package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.repository.ArtistaRepository;

@Transactional
@Service
@SuppressWarnings("null")
public class ArtistaService {

    private final ArtistaRepository repo;

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public Artista findById(Integer id) {
        Artista artista = repo.findById(id).orElse(null);
        return artista;
    }

    public List<Artista> listar() {
        return repo.findAll();
    }

    public Artista guardar(Artista artista) {
        return repo.save(artista);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
