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

import com.example.ragemusica.model.EstadoVenta;
import com.example.ragemusica.service.EstadoVentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController 
@RequestMapping("/api/estado_ventas")
@CrossOrigin(origins = "*")
@Tag(name = "Estado de Venta", description = "Operaciones relacionadas con los Estados de Venta") //Permite agrupar los endpoints bajo un nombre y descripción  
public class EstadoVentasController {

    @Autowired
    private EstadoVentasService estadosService;

    @GetMapping
    @Operation(summary = "Obtener lista de los Estados de Ventas", description = "Obtiene una lista de todos los Estados de Ventas")
    public ResponseEntity<List<EstadoVenta>> getAllRol() {
        List<EstadoVenta> estado = estadosService.findAll();
        if (estado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener el Estado de Venta", description = "Obtienes el Estado de Venta por ID")
    public ResponseEntity<EstadoVenta> getRolRolUsuariosById(@PathVariable Integer id) {
        EstadoVenta estado = estadosService.findById(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    @Operation(summary = "Agregar Detalle", description = "Agregar un estado a una venta")
    public ResponseEntity<EstadoVenta> createRolUsuarios(@RequestBody EstadoVenta estado) {
        EstadoVenta createdestado = estadosService.save(estado);
        return ResponseEntity.status(201).body(createdestado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un estado", description = "Eliminar un estado por ID")
    public ResponseEntity<Void> deleteEstadoVenta(@PathVariable Integer id) {
        estadosService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    

}



    

    

    
