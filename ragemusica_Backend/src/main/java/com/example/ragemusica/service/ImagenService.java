package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.ragemusica.model.Imagenes;
import com.example.ragemusica.repository.ImagenRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings("null")
@Service
public class ImagenService {

    private final ImagenRepositorio repo;

    public ImagenService(ImagenRepositorio repo) {
        this.repo = repo;
    }

    public List<Imagenes> listar() {
        return repo.findAll();
    }

    public Imagenes findById(Integer id) {
        Imagenes imagen = repo.findById(id).orElse(null);
        return imagen;
    }

     public Imagenes save(Imagenes imagen) {
        return repo.save(imagen);
    }

    public Imagenes partialUpdate(Imagenes imagen){
        Imagenes existingImagenes = repo.findById(imagen.getId()).orElse(null);
        if (existingImagenes != null) {
            if (imagen.getNombre() != null) {
                existingImagenes.setNombre(imagen.getNombre());
            }
            }
        return null;
    }


    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

}
