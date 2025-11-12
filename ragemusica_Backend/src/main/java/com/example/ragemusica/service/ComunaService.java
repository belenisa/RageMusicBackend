package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Comuna;
import com.example.ragemusica.repository.ComunaRepositorio;

@Service
public class ComunaService {

     @Autowired
    private ComunaRepositorio comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public Comuna findById(Integer id) {
        Comuna comuna = comunaRepository.findById(id).orElse(null);
        return comuna;
    }

     public Comuna save(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna partialUpdate(Comuna comuna){
        Comuna existingComuna = comunaRepository.findById(comuna.getId()).orElse(null);
        if (existingComuna != null) {
            if (comuna.getNombre() != null) {
                existingComuna.setNombre(comuna.getNombre());
            }
            return comunaRepository.save(existingComuna);
        }
        return null;
    }

    public void deleteById(Integer id) {
        comunaRepository.deleteById(id);
    }

}
