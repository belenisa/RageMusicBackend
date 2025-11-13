package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ragemusica.model.Artistas;

public interface ArtistasRepositorio extends JpaRepository<Artistas, Long> {

}
