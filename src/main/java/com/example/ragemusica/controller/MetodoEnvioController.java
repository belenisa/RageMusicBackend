package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.MetodoEnvio;
import com.example.ragemusica.service.MetodoEnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/metodo_envio")
@CrossOrigin(origins = "*") 
@Tag(name = "Metodos de  Envio", description = "Operaciones relacionadas con los metodos de envio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener lista los metodos de envio", description = "Obtiene una lista de todos los metodos de envio")
    public List<MetodoEnvio> listarMetodoPago() {
        return envioService.findAll();
    }

    @PostMapping
    @Operation(summary = "Agregar un Metodo de Envio", description = "Agrega un Metodo de Envio")
    public MetodoEnvio agregarMetodoEnvio(@RequestBody MetodoEnvio envio) {
        return envioService.save(envio);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un Metodos de Envio", description = "Buscar un Metodos de Envio por ID")
    public MetodoEnvio buscarMetodoEnvio(@PathVariable Integer id) {
        return envioService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los Metodos de Envio", description = "Actualizar los Metodos de Envio por ID")
    public MetodoEnvio updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio envio) {
        envio.setId(id);
        return envioService.update(envio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar los Metodos de Pago", description = "Eliminar los Metodos de Pago por ID")
    public ResponseEntity<Void> deleteMetodoPagon(@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    

}
