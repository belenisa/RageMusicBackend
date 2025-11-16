package com.example.ragemusica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "direccion")
public class Direcciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Integer codigoPostal;

    @ManyToOne
    @JoinColumn(name = "ID_Comuna")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

}
