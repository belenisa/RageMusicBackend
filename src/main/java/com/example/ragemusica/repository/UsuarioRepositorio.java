package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
    Usuario findByNombre(String nombre);
}
