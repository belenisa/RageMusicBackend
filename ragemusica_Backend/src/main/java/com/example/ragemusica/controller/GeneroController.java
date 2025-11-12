package com.example.ragemusica.controller;

import com.example.ragemusica.model.Genero;
import com.example.ragemusica.service.GeneroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Genero> listar() {
        return service.listar();
    }

    @PostMapping
    public Genero crear(@RequestBody Genero genero) {
        return service.guardar(genero);
    }
}
