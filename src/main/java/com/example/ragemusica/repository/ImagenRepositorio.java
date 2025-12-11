
package com.example.ragemusica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ragemusica.model.Imagenes;

public interface ImagenRepositorio extends JpaRepository<Imagenes, Integer>{
    
    boolean existsByUrl(String url);
    
    // CORRECCIÓN: Usar Optional para buscar una sola imagen por producto ID.
    // Esto se mapea a la primera o única imagen encontrada.
    Optional<Imagenes> findByProductoId(Integer productoId); 
}