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

import com.example.ragemusica.model.Comuna;
import com.example.ragemusica.service.ComunaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comuna")
@CrossOrigin(origins = "*")
@Tag(name = "Comuna", description = "Operaciones relacionadas con las Comuna") //Permite agrupar los endpoints bajo un nombre y descripción 
public class ComunaController {

    
@Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> getAllComunas() {
        List<Comuna> comunas = comunaService.findAll();
        if (comunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getComunaById(@PathVariable Integer id) {
        Comuna comuna = comunaService.findById(id);
        if (comuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comuna);
    }

    @PostMapping
    public ResponseEntity<Comuna> createComuna(@RequestBody Comuna comuna) {
        comuna.setId(null);
        Comuna nuevaComuna = comunaService.save(comuna);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaComuna);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateComuna(@PathVariable Integer id, @RequestBody Comuna comuna) {
        comuna.setId(id);
        Comuna updatedComuna = comunaService.save(comuna);
        if (updatedComuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedComuna);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComuna(@PathVariable Integer id) {
        comunaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
