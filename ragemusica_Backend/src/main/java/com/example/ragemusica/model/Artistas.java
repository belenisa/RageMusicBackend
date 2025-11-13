package com.example.ragemusica.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
}