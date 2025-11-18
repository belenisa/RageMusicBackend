package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.SubGenero;

@Repository
public interface SubGeneroRepository extends JpaRepository<SubGenero, Integer> {}
