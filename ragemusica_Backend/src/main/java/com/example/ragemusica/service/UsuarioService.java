package com.example.ragemusica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ragemusica.model.Usuario;
import com.example.ragemusica.repository.UsuarioRepositorio;

import org.springframework.security.crypto.password.PasswordEncoder;

@Transactional
@Service
@SuppressWarnings("null")
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usurep;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public List<Usuario> findAll() {
        List<Usuario> usuarios = usurep.findAll();
        return usuarios;
    }

    public Usuario findById(Integer id) {
        Usuario usuario = usurep.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setContrasena(null);
        }
        return usuario;
    }

    public Usuario login(Usuario usuario) {
        Usuario foundUsuario = usurep.findByCorreo(usuario.getCorreo());
 
        if (foundUsuario != null &&  passwordEncoder.matches(usuario.getContrasena(), foundUsuario.getContrasena())) {
            return foundUsuario;
        }

        return null;
    }

    public Usuario updateUsuario(Usuario usuario) {
        return save(usuario);
    }

    public Usuario save(Usuario usuario) {
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordHasheada);
        return usurep.save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario){
        Usuario existingUsuario = usurep.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getNombre() != null) {
                existingUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getCorreo() != null) {
                existingUsuario.setCorreo(usuario.getCorreo());
            }

            if(usuario.getContrasena() != null) {
                existingUsuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            }

            if(usuario.getRol() != null) {
                existingUsuario.setRol(usuario.getRol());
            }

            return usurep.save(existingUsuario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        usurep.deleteById(id);
    }
}
