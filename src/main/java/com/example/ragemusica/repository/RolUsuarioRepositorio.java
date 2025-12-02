package com.example.ragemusica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.RolUsuarios;

@Repository
public interface RolUsuarioRepositorio extends JpaRepository<RolUsuarios, Integer> {
    Optional<RolUsuarios> findByRol(RolUsuarios.Rol rol);
}
