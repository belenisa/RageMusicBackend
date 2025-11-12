package com.example.ragemusica.controller;

import com.example.ragemusica.model.TipoProducto;
import com.example.ragemusica.service.TipoProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-producto")
@CrossOrigin(origins = "*")
public class TipoProductoController {

    private final TipoProductoService service;

    public TipoProductoController(TipoProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoProducto> listar() {
        return service.listar();
    }

    @PostMapping
    public TipoProducto crear(@RequestBody TipoProducto tipo) {
        return service.guardar(tipo);
    }
}
