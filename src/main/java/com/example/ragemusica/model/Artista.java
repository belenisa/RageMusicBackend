package com.example.ragemusica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getters y setters para todos los campos.
@AllArgsConstructor //Genera un constructor con todos los argumentos
@NoArgsConstructor //Genera un constructor sin argumentos 
@Entity //Marca la clase como una entidad
@Table(name = "artista") //Indica el nombre de la tabla
public class Artista {

    @Id //Marca el campo como clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que el valor del id se genera automáticamente por la base de datos / IDENTITY = Usa la estrategia nativa del motor
    private Integer id;

    
    @Column(nullable = false) //Configura la columna en la base de datos
    private String nombre;
}
