package com.example.ragemusica.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Direcciones;
import com.example.ragemusica.repository.DireccionesRepositorio;



@Transactional
@Service
@SuppressWarnings("null")
public class DireccionesService {

    private final DireccionesRepositorio direccionRepository;

    public DireccionesService(DireccionesRepositorio direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<Direcciones> findAll() {
        return direccionRepository.findAll();
    }

    public Direcciones findById(Integer id) {
        return direccionRepository.findById(id).orElse(null);
    }

    public Direcciones save(Direcciones direcciones) {
        return direccionRepository.save(direcciones);
    }

    public Direcciones updateDirecciones(Direcciones direcciones) {
        return save(direcciones);
    }

    public Direcciones partialUpdate(Direcciones direcciones) {
        return direccionRepository.findById(direcciones.getId())
                .map(existingDirecciones -> {
                    if (direcciones.getDireccion() != null) {
                        existingDirecciones.setDireccion(direcciones.getDireccion());
                    }
                    if (direcciones.getCodigoPostal() != null) {
                        existingDirecciones.setCodigoPostal(direcciones.getCodigoPostal());
                    }
                    if (direcciones.getComuna() != null) {
                        existingDirecciones.setComuna(direcciones.getComuna());
                    }
                    if (direcciones.getUsuario() != null) {
                        existingDirecciones.setUsuario(direcciones.getUsuario());
                    }
                    return direccionRepository.save(existingDirecciones);
                })
                .orElse(null);
    }

    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }
}
