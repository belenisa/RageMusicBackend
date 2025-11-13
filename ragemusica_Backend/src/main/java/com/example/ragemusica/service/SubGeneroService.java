package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.SubGenero;
import com.example.ragemusica.model.Usuario;
import com.example.ragemusica.repository.SubGeneroRepository;

@Service
public class SubGeneroService {


    @Autowired
    private final SubGeneroRepository repo;

    public SubGeneroService(SubGeneroRepository repo) {
        this.repo = repo;
    }

    public List<SubGenero> listar() {
        return repo.findAll();
    }

    public SubGenero guardar(SubGenero subGenero) {
        return repo.save(subGenero);
    }
    
    public SubGenero partialUpdate(SubGenero subGenero){
        SubGenero existingSubGenero = repo.findById(subGenero.getId()).orElse(null);
        if (existingSubGenero != null) {
            if (subGenero.getNombre() != null) {
                existingSubGenero.setNombre(subGenero.getNombre());
            }
            return repo.save(existingSubGenero);
        }
        return null;
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
