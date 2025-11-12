package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ragemusica.model.Direcciones;
import com.example.ragemusica.repository.DireccionesRepositorio;

@Service
public class DireccionesService {

    @Autowired
    private DireccionesRepositorio direccionRepository;

    public List<Direcciones> findAll() {
        return direccionRepository.findAll();
    }

    public Direcciones findById(Integer id) {
        Direcciones direcciones = direccionRepository.findById(id).orElse(null);
        return direcciones;
    }

     public Direcciones save(Direcciones direcciones) {
        return direccionRepository.save(direcciones);
    }

    public Direcciones partialUpdate(Direcciones direcciones){
        Direcciones existingDireciones = direccionRepository.findById(direcciones.getId()).orElse(null);
        if (existingDireciones != null) {
            if (direcciones.getDireccion() != null) {
                existingDireciones.setDireccion(direcciones.getDireccion());
            }

            if(direcciones.getCodigoPostal() != null) {
                existingDireciones.setCodigoPostal(direcciones.getCodigoPostal());
            }

            return direccionRepository.save(existingDireciones);
        }
        return null;
    }

    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }
}