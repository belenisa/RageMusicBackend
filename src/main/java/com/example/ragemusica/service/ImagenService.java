package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Comentario: Importante para la gestión de transacciones

import com.example.ragemusica.model.Imagenes;
import com.example.ragemusica.repository.ImagenRepositorio;

/**
 * Servicio para la gestión de la entidad Imagenes.
 * * @Transactional: Indica que todos los métodos públicos de esta clase serán gestionados
 * dentro de una transacción de base de datos.
 * - Si un método falla (lanza una excepción no comprobada), la transacción se revierte (rollback).
 * - Si un método termina con éxito, los cambios se confirman (commit).
 * * @Transactional(readOnly = true): Optimización para consultas de solo lectura. 
 * Le indica a la base de datos que no necesita bloquear recursos ni realizar seguimiento de cambios, 
 * mejorando el rendimiento para las operaciones de lectura (ej: listar y findById).
 */
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
   
    @Transactional(readOnly = true)
    public Imagenes findByProductoId(int productoId) {
        return repo.findByProductoId(productoId).orElse(null);
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
        if (imagen.getProducto() == null || imagen.getProducto().getId() == null) {
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
            return null;
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