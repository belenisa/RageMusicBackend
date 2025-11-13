package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Artistas;
import com.example.ragemusica.repository.ArtistasRepositorio;

@SuppressWarnings("null")
@Service
public class ArtistasService {

    @Autowired
    private final ArtistasRepositorio repo;


     public List<Artistas> listar() {
        return repo.findAll();
    }

    public Artistas findById(Integer id) {
        Artistas artistas = repo.findById(id).orElse(null);
        return artistas;
    }

    public ArtistasService(ArtistasRepositorio repo) {
        this.repo = repo;
    }

    public Artistas guardar(Artistas artistas) {
        return repo.save(artistas);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
