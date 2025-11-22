package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Venta;
import com.example.ragemusica.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "Obtener lista de Ventas", description = "Obtiene una lista de todas las ventas")
    public List<Venta> listarventa() {
        return ventaService.getVenta();
    }

    @PostMapping
    @Operation(summary = "Agregar una Venta", description = "Agregar una Venta")
    public Venta agregarVenta(@RequestBody Venta venta) {
        return ventaService.saveVenta(venta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una Venta", description = "Buscar una venta por ID")
    public Venta buscarVenta(@PathVariable int id) {
        return ventaService.getVentaId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar las ventas", description = "Actualizar las ventas por ID")
    public Venta actualizarVenta(@PathVariable int id, @RequestBody Venta venta) {
        venta.setId(id);
        return ventaService.updateVenta(venta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar las ventas", description = "Eliminar las ventas por ID")
    public ResponseEntity<String> eliminarVenta(@PathVariable int id) {
        boolean eliminado = ventaService.deleteVenta(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado.");
        }

        return ResponseEntity.ok("Detalle eliminado correctamente.");
    }

}

    

    

    

    

    