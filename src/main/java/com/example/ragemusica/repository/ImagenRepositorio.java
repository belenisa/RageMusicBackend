package com.example.ragemusica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ragemusica.model.Imagenes;

public interface ImagenRepositorio extends JpaRepository<Imagenes, Integer>{
    
    boolean existsByUrl(String url);
    List<Imagenes> findByProductoId(Integer productoId);

}
