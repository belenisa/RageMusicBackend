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

import com.example.ragemusica.model.TipoProducto;
import com.example.ragemusica.service.TipoProductoService;

@RestController
@RequestMapping("/api/tipos-producto")
@CrossOrigin(origins = "*")
public class TipoProductoController {

    @Autowired
    private final TipoProductoService service;

    public TipoProductoController(TipoProductoService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<TipoProducto>> getallTipoProducto() {
        List<TipoProducto> tipoProductos = service.findAll();
        if (tipoProductos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoProductos);
    }


    @PostMapping
    public TipoProducto crear(@RequestBody TipoProducto tipo) {
        return service.guardar(tipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProducto> getTipoProductoById(@PathVariable Integer id) {
        TipoProducto tipoProducto = service.findById(id);
        if (tipoProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoProducto(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();  
    }  
}
