package com.example.ragemusica.controller;

import java.util.List;

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

import com.example.ragemusica.model.Producto;
import com.example.ragemusica.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Mostrar lista Producto", description = "Muestra la lista de Producto")
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    @Operation(summary = "Crear un producto", description = "Agregar un Producto")
    public Producto crear(@RequestBody Producto producto) {
        return service.guardar(producto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los Artistas", description = "Actualizar por Artista")
    public ResponseEntity<Producto> updatedProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updatedProducto = service.guardar(producto);
        if (updatedProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProducto);
    }

    @PatchMapping("/{id}") // actualizar por id
    @Operation(summary = "Actualizar los Artistas", description = "Actualizar un detalle de los Artista")
    public ResponseEntity<Producto> updateParcialUsuario(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updatedProducto = service.partialUpdate(producto);
        if (updatedProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProducto);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();  
    }    
}
