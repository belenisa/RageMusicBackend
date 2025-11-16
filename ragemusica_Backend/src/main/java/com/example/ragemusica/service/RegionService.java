package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Region;
import com.example.ragemusica.repository.RegionRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@SuppressWarnings("null")
public class RegionService {

    @Autowired
    private RegionRepositorio regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        Region region = regionRepository.findById(id).orElse(null);
        return region;
    }

     public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Region partialUpdate(Region region){
        Region existingRegion = regionRepository.findById(region.getId()).orElse(null);
        if (existingRegion != null) {
            if (region.getNombre() != null) {
                existingRegion.setNombre(region.getNombre());
            }
            return regionRepository.save(existingRegion);
        }
        return null;
    }

    public void deleteById(Integer id) {
        regionRepository.deleteById(id);
    }

}
