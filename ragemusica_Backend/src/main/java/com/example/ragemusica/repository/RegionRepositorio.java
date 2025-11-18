package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.Region;

@Repository
public interface RegionRepositorio extends JpaRepository<Region, Integer>{

}
