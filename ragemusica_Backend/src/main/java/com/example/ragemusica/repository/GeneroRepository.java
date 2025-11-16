package com.example.ragemusica.repository;

import com.example.ragemusica.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;


@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {}