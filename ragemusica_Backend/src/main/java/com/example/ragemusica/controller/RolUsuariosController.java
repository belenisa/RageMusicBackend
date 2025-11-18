package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ragemusica.model.RolUsuarios;
import com.example.ragemusica.service.RolUsuarioService;

public class RolUsuariosController {

    @Autowired
    private RolUsuarioService rolService;

    @GetMapping
    public ResponseEntity<List<RolUsuarios>> getAllRol() {
        List<RolUsuarios> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolUsuarios> getRolRolUsuariosById(@PathVariable Integer id) {
        RolUsuarios rol = rolService.findById(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<RolUsuarios> createRolUsuarios(@RequestBody RolUsuarios rol) {
        RolUsuarios createdRol = rolService.save(rol);
        return ResponseEntity.status(201).body(createdRol);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolUsuarios(@PathVariable Integer id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }     

}
