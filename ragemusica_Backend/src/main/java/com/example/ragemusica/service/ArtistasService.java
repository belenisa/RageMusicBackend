package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Artistas;
import com.example.ragemusica.model.Comuna;
import com.example.ragemusica.repository.ArtistasRepositorio;
import com.example.ragemusica.repository.ComunaRepositorio;

@Service
public class ArtistasService {

    @Autowired
    private ArtistasRepositorio artistasRepository;

    public List<Artistas> findAll() {
        return artistasRepository.findAll();
    }

    public Artistas findById(Integer id) {
        Artistas artistas = artistasRepository.findById(id).orElse(null);
        return artistas;
    }

    
    public Artistas save(Artistas artistas) {
        return artistasRepository.save(artistas);
    }

    public void deleteById(Integer id) {
        artistasRepository.deleteById(id);
    }

}
