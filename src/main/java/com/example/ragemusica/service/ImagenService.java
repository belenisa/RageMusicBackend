
package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Imagenes;
import com.example.ragemusica.repository.ImagenRepositorio;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepositorio repo;

    @Transactional(readOnly = true)
    public List<Imagenes> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Imagenes findById(int id) {
        return repo.findById(id).orElseThrow(
            () -> new jakarta.persistence.EntityNotFoundException("Imagen no encontrada: id=" + id)
        );
    }

    @Transactional
    public Imagenes save(Imagenes imagen) {
        if (imagen.getUrl() == null ||
            !(imagen.getUrl().startsWith("http://") || imagen.getUrl().startsWith("https://"))) {
            throw new IllegalArgumentException("URL inválida: debe comenzar con http/https");
        }
        if (imagen.getNombre() == null || imagen.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (imagen.getProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        return repo.save(imagen);
    }

    @Transactional
    public Imagenes partialUpdate(Imagenes imagen) {
        Integer idObj = imagen.getId();
        if (idObj == null) {
            throw new IllegalArgumentException("El ID es obligatorio para actualización parcial");
        }
        final int id = idObj;

        Imagenes existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return null; // o lanzar EntityNotFoundException según tu contrato
        }

        if (imagen.getNombre() != null) {
            if (imagen.getNombre().isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            existing.setNombre(imagen.getNombre());
        }

        if (imagen.getUrl() != null) {
            if (!(imagen.getUrl().startsWith("http://") || imagen.getUrl().startsWith("https://"))) {
                throw new IllegalArgumentException("URL inválida: debe comenzar con http/https");
            }
            existing.setUrl(imagen.getUrl());
        }

        if (imagen.getProducto() != null) {
            existing.setProducto(imagen.getProducto());
        }

        return repo.save(existing);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
