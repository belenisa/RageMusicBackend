package com.example.ragemusica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.ragemusica.model.Region;
import com.example.ragemusica.service.RegionService;


@RestController
@RequestMapping("/api/region")
@CrossOrigin(origins = "*")
public class RegionController {
    
    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getallRegion() {
        List<Region> region = regionService.findAll();
        if (region.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(region);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        Region region = regionService.findById(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region);
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        region.setId(null);
        Region regionNew = regionService.save(region);
        return ResponseEntity.status(201).body(regionNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Integer id, @RequestBody Region region) {
        region.setId(id);
        Region updatedRegion = regionService.save(region);
        if (updatedRegion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRegion);
    }

    @PatchMapping("/{id}") // actualizar por id
    public ResponseEntity<Region> updateParcialUsuario(@PathVariable Integer id, @RequestBody Region region) {
        region.setId(id);
        Region updatedRegion = regionService.partialUpdate(region);
        if (updatedRegion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        regionService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }    

}
