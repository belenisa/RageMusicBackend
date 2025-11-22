package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Artistas;
import com.example.ragemusica.repository.ArtistasRepositorio;

@Transactional
@Service
@SuppressWarnings("null")
public class ArtistasService {

    @Autowired //inyectar automáticamente una instancia del bean correspondiente en este campo.
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
