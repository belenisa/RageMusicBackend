package com.example.ragemusica.service;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.repository.ArtistaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ArtistaService {

    @Autowired
    private final ArtistaRepository repo;


     public List<Artista> listar() {
        return repo.findAll();
    }

    public Artista findById(Integer id) {
        Artista artista = repo.findById(id).orElse(null);
        return artista;
    }

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public Artista partialUpdate(Artista artista){
        Artista existingArtista = repo.findById(artista.getId()).orElse(null);
        if (existingArtista != null) {
            if (artista.getNombre() != null) {
                existingArtista.setNombre(artista.getNombre());
            }
            return repo.save(existingArtista);
        }
        return null;
    }

    public Artista guardar(Artista artista) {
        return repo.save(artista);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
