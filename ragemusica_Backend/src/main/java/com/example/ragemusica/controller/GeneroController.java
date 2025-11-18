package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Genero;
import com.example.ragemusica.service.GeneroService;


@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "*")
public class GeneroController {

    @Autowired
    private GeneroService service;

    
    @GetMapping
    public ResponseEntity<List<Genero>> listar() {
        List<Genero> generos = service.listar();
        if (generos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(generos);
    }

    @PostMapping
    public ResponseEntity<Genero> crear(@RequestBody Genero genero) {
        Genero nuevo = service.guardar(genero);
        return ResponseEntity.status(201).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();  
    }   


}
