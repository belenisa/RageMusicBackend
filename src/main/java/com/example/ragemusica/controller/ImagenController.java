package com.example.ragemusica.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Imagenes;
import com.example.ragemusica.service.ImagenService;

@RestController
@RequestMapping("/api/imagen")
@CrossOrigin(origins = "*")
public class ImagenController {

    @Autowired
    private  ImagenService imagenService;

    @GetMapping
    public List<Imagenes> listar() {
        return imagenService.listar();
    }

    @PostMapping
    public Imagenes crear(@RequestBody Imagenes imagen) {
        return imagenService.save(imagen);
    }

    @PatchMapping("/{id}") // actualizar por id, actualizaciones parciales del recurso
    public ResponseEntity<Imagenes> updateParcialUsuario(@PathVariable Integer id, @RequestBody Imagenes imagen) {
        imagen.setId(id);
        Imagenes updatedImagenes = imagenService.partialUpdate(imagen);
        if (updatedImagenes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedImagenes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Integer id) {
        imagenService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    

}
