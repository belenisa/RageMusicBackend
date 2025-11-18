package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Comuna;
import com.example.ragemusica.repository.ComunaRepositorio;

@Transactional
@Service
@SuppressWarnings("null")
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

    public Comuna partialUpdate(Comuna comuna) {
        return comunaRepository.findById(comuna.getId())
                .map(existingComuna -> {
                    if (comuna.getNombre() != null) {
                        existingComuna.setNombre(comuna.getNombre());
                    }
                    if (comuna.getRegion() != null) {
                        existingComuna.setRegion(comuna.getRegion());
                    }
                    return comunaRepository.save(existingComuna);
                })
                .orElse(null);
    }

    public void deleteById(Integer id) {
        comunaRepository.deleteById(id);
    }

}
