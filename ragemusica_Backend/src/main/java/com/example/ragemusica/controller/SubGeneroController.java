package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.SubGenero;
import com.example.ragemusica.service.SubGeneroService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoProducto(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();  
    }  
}
