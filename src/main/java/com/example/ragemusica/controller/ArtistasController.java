package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Artistas;
import com.example.ragemusica.service.ArtistasService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
@Tag(name = "Artistas", description = "Operaciones relacionadas con los Artistas") //Permite agrupar los endpoints bajo un nombre y descripción 
public class ArtistasController {

    @Autowired
    private ArtistasService artistasService;

    @GetMapping
    public ResponseEntity<List<Artistas>> getAllArtistas() {
        List<Artistas> artistas = artistasService.findAll();
        if (artistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artistas> getArtistasById(@PathVariable Integer id) {
        Artistas artistas = artistasService.findById(id);
        if (artistas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistas);
    }

    @PostMapping
    public ResponseEntity<Artistas> createArtistas(@RequestBody Artistas artistas) {
        artistas.setId(null);
        Artistas nuevaArtistas = artistasService.save(artistas);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaArtistas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artistas> updateArtistas(@PathVariable Integer id, @RequestBody Artistas artistas) {
        artistas.setId(id);
        Artistas updatedArtistas = artistasService.save(artistas);
        if (updatedArtistas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArtistas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistas(@PathVariable Integer id) {
        artistasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}