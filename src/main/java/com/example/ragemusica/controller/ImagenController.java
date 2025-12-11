package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/imagen")
@CrossOrigin(origins = "*")
@Tag(name = "Imagen", description = "Operaciones relacionadas con las imágenes")
@RequiredArgsConstructor
public class ImagenController {

    private final ImagenService imagenService;

    @Operation(summary = "Listar todas las imágenes")
    @GetMapping
    public ResponseEntity<List<Imagenes>> listar() {
        List<Imagenes> lista = imagenService.listar();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener una imagen por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Imagenes> obtener(@PathVariable int id) {
        try {
            Imagenes imagen = imagenService.findById(id); 
            return ResponseEntity.ok(imagen);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener la imagen principal por ID de Producto")
    @GetMapping("/producto/{productoId}") // <--- ESTA RUTA COINCIDE CON TU RETROFIT
    public ResponseEntity<Imagenes> obtenerImagenPorProductoId(@PathVariable int productoId) {
        // Usamos la función optimizada del Servicio
        Imagenes imagen = imagenService.findByProductoId(productoId);
        
        if (imagen != null) {
            return ResponseEntity.ok(imagen); // Devuelve 200 OK con la imagen
        } else {
            // Devuelve 404 NOT FOUND si el producto no tiene una imagen asociada
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }

    @Operation(summary = "Crear una imagen")
    @PostMapping
    public ResponseEntity<Imagenes> crear(@RequestBody Imagenes imagen) {
        Imagenes creada = imagenService.save(imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Actualizar parcialmente una imagen por ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Imagenes> updateParcial(@PathVariable int id, @RequestBody Imagenes imagen) {
        imagen.setId(id);
        Imagenes updated = imagenService.partialUpdate(imagen);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar una imagen por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int id) {
        imagenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}