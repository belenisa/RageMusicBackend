package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.repository.ArtistaRepository;

@Transactional //Indica que los métodos del servicio se ejecutan dentro de una transacción de base de datos.
@Service //permite que Spring la detecte y la inyecte donde se necesite
@SuppressWarnings("null") //compilador que ignore advertencias específicas (en este caso, relacionadas con posibles valores null).
public class ArtistaService {

    private final ArtistaRepository repo;

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public Artista findById(Integer id) {
        Artista artista = repo.findById(id).orElse(null);
        return artista;
    }

    public List<Artista> listar() {
        return repo.findAll();
    }

    public Artista guardar(Artista artista) {
        return repo.save(artista);
    }

    public Artista updateUsuario(Artista artista) {
        return repo.save(artista);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
