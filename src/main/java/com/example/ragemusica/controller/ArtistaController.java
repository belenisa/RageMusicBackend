package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ragemusica.model.Artista;
import com.example.ragemusica.service.ArtistaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController //indica a Spring que esta clase es un controlador web
@RequestMapping("/api/artista") //Define el prefijo de ruta
@CrossOrigin(origins = "*") //permitiendo solicitudes desde otros dominios (orígenes). Con origins = "*"
@Tag(name = "Artista", description = "Operaciones relacionadas con los Artistas") //Permite agrupar los endpoints bajo un nombre y descripción 
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping//Mapea un método del controlador a una solicitud HTTP GET, normalmente para consultar recursos.
    @Operation(summary = "Obtener lista de los Artistas", description = "Obtiene una lista de todos los artistas") //documentar el endpoint.
    public ResponseEntity<List<Artista>> getAllArtistas() {
        List<Artista> artistas = artistaService.listar();
        if (artistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable Integer id)//Vincula una variable del path de la URL a un parámetro del método.
     {
        Artista artista = artistaService.findById(id);
        if (artista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artista);
    }

    @PostMapping //Mapea a HTTP POST, usado para crear recursos.
    public ResponseEntity<Artista> createArtista(@RequestBody Artista artista)// //Indica que el parámetro del método se debe deserializar desde el cuerpo de la solicitud
     {
        artista.setId(null);
        Artista nuevaArtista = artistaService.guardar(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaArtista);
    }

    @PutMapping("/{id}") //usado para reemplazar/actualizar un recurso completo
    public ResponseEntity<Artista> updateArtista(@PathVariable Integer id, @RequestBody Artista artista){
        artista.setId(id);
        Artista updatedArtista = artistaService.guardar(artista);
        if (updatedArtista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArtista);
    }

    @DeleteMapping("/{id}") //usado para eliminar un recurso.
    public ResponseEntity<Void> deleteArtista(@PathVariable Integer id) {
        artistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
