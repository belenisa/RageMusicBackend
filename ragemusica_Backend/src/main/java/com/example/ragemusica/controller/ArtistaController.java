package com.example.ragemusica.controller;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.service.ArtistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaController {

    private final ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Artista> listar() {
        return service.listar();
    }

    @PostMapping
    public Artista crear(@RequestBody Artista artista) {
        return service.guardar(artista);
    }
}
