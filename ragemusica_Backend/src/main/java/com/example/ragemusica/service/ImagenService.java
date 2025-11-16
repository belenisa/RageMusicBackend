package com.example.ragemusica.service;

import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Imagenes;
import com.example.ragemusica.repository.ImagenRepositorio;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ImagenService {

    private final ImagenRepositorio repo;

    public ImagenService(ImagenRepositorio repo) {
        this.repo = repo;
    }

    public Imagenes findById(Integer id) {
        Imagenes imagen = repo.findById(id).orElse(null);
        return imagen;
    }

     public Imagenes save(Imagenes imagen) {
        return repo.save(imagen);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

}
