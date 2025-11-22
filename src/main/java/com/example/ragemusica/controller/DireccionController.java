package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Direcciones;
import com.example.ragemusica.service.DireccionesService;



@RestController
@RequestMapping("/api/direccion")
@CrossOrigin(origins = "*")
public class DireccionController {

    
    @Autowired
    private DireccionesService dircService;

    @GetMapping
    public ResponseEntity<List<Direcciones>> getAllDirecciones() {
        List<Direcciones> direcciones = dircService.findAll();
        if (direcciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direcciones> getDireccionById(@PathVariable Integer id) {
        Direcciones direccion = dircService.findById(id);
        if (direccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(direccion);
    }

    @PostMapping
    public ResponseEntity<Direcciones> createDireccion(@RequestBody Direcciones direccion) {
        direccion.setId(null);
        Direcciones nuevaDireccion = dircService.save(direccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDireccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direcciones> updateDireccion(@PathVariable Integer id, @RequestBody Direcciones direccion) {
        direccion.setId(id);
        Direcciones updatedDireccion = dircService.save(direccion);
        if (updatedDireccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDireccion);
    }

    @PatchMapping("/{id}")//actualizaciones parciales del recurso
    public ResponseEntity<Direcciones> updateParcialDireccion(@PathVariable Integer id, @RequestBody Direcciones direccion) {
        direccion.setId(id);
        Direcciones updatedDireccion = dircService.partialUpdate(direccion);
        if (updatedDireccion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDireccion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDireccion(@PathVariable Integer id) {
        dircService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
  
}
