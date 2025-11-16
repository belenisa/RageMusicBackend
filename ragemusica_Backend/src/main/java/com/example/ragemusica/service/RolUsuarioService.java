package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.RolUsuarios;
import com.example.ragemusica.repository.RolUsuarioRepositorio;

import jakarta.transaction.Transactional;


@Service
@SuppressWarnings("null")
@Transactional
public class RolUsuarioService {
     @Autowired
    private RolUsuarioRepositorio rolRepository;

    public List<RolUsuarios> findAll() {
        return rolRepository.findAll();
    }

    @SuppressWarnings("null")
    public RolUsuarios findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public RolUsuarios save(RolUsuarios rol) {
        return rolRepository.save(rol);
    } 

    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }
}
