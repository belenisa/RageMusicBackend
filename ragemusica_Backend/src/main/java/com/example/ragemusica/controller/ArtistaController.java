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

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.service.ArtistaService;


@RestController
@RequestMapping("/api/artista")
@CrossOrigin(origins = "*")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> getAllArtistas() {
        List<Artista> artistas = artistaService.listar();
        if (artistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable Integer id) {
        Artista artista = artistaService.findById(id);
        if (artista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artista);
    }

    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
        artista.setId(null);
        Artista nuevaArtista = artistaService.guardar(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaArtista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> updateArtista(@PathVariable Integer id, @RequestBody Artista artista) {
        artista.setId(id);
        Artista updatedArtista = artistaService.guardar(artista);
        if (updatedArtista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArtista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable Integer id) {
        artistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
