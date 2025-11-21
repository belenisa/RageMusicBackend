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

    // --- MÉTODO LOGIN MODIFICADO PARA DETECTAR ERRORES ---
    public Usuario login(Usuario usuario) {
        System.out.println("------------------------------------------------");
        System.out.println("🔍 INTENTO DE LOGIN");
        System.out.println("📩 Correo recibido del Frontend: '" + usuario.getCorreo() + "'");
        System.out.println("🔑 Contraseña recibida: '" + usuario.getContrasena() + "'");

        // 1. Buscar usuario por correo
        Usuario foundUsuario = usurep.findByCorreo(usuario.getCorreo());

        if (foundUsuario == null) {
            System.out.println("ERROR: No se encontró ningún usuario con el correo: " + usuario.getCorreo());
            System.out.println("Verifica si en la base de datos el correo tiene espacios extra o mayúsculas.");
            return null;
        }

        System.out.println("Usuario encontrado en BD: " + foundUsuario.getNombre());
        System.out.println("Hash guardado en BD: " + foundUsuario.getContrasena());

        // 2. Verificar contraseña
        boolean coinciden = passwordEncoder.matches(usuario.getContrasena(), foundUsuario.getContrasena());

        if (coinciden) {
            System.out.println("¡CONTRASEÑA CORRECTA! Login exitoso.");
            return foundUsuario;
        } else {
            System.out.println("ERROR: La contraseña no coincide con el hash.");
            return null;
        }
    }
    // -------------------------------------------------------

    public Usuario updateUsuario(Usuario usuario) {
        return save(usuario);
    }

    public Usuario save(Usuario usuario) {
        // Aseguramos que la contraseña se encripte al crear
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
