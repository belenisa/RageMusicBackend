package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.MetodoPago;
import com.example.ragemusica.service.MetodoPagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/metodo_pagos")
@Tag(name = "Metodos de  Pago", description = "Operaciones relacionadas con los metodos de pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoService;

    @GetMapping
    @Operation(summary = "Obtener lista los megtodos de pago", description = "Obtiene una lista de todos los metodos de pago")
    public List<MetodoPago> listarMetodoPago() {
        return metodoService.findAll();
    }

    @PostMapping
    @Operation(summary = "Agregar un Metodo de Pago", description = "Agrega un Metodo de Pago")
    public MetodoPago agregarMetodoPago(@RequestBody MetodoPago metodo) {
        return metodoService.save(metodo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un Metodos de Pago", description = "Buscar un Metodos de Pago por ID")
    public MetodoPago buscarMetodoPago(@PathVariable int id) {
        return metodoService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los Metodos de Pago", description = "Actualizar los Metodos de Pago por ID")
    public MetodoPago updateMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        metodoPago.setId(id);
        return metodoService.update(metodoPago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar los Metodos de Pago", description = "Eliminar los Metodos de Pago por ID")
    public ResponseEntity<Void> deleteMetodoPagon(@PathVariable Integer id) {
        metodoService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    

}
