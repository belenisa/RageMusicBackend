package com.example.ragemusica.service;

import com.example.ragemusica.model.Genero;
import com.example.ragemusica.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    private final GeneroRepository repo;

    public GeneroService(GeneroRepository repo) {
        this.repo = repo;
    }

    public List<Genero> listar() {
        return repo.findAll();
    }

    public Genero guardar(Genero genero) {
        return repo.save(genero);
    }

    
    public Genero partialUpdate(Genero genero) {
        return repo.findById(genero.getId())
                .map(existingGenero -> {
                    if (genero.getNombre() != null) {
                        existingGenero.setNombre(genero.getNombre());
                    }
                    return repo.save(existingGenero);
                })
                .orElse(null);
    }


    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
