package com.example.ragemusica.controller;

import com.example.ragemusica.model.SubGenero;
import com.example.ragemusica.service.SubGeneroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subgeneros")
@CrossOrigin(origins = "*")
public class SubGeneroController {

    private final SubGeneroService service;

    public SubGeneroController(SubGeneroService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubGenero> listar() {
        return service.listar();
    }

    @PostMapping
    public SubGenero crear(@RequestBody SubGenero subGenero) {
        return service.guardar(subGenero);
    }
}
