package com.example.ragemusica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ragemusica.model.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    Usuario findByCorreo(String correo);
}
