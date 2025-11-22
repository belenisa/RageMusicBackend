package com.example.ragemusica.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;              // <-- Import correcto
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Estado")
public class EstadoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public enum Estado {
        Cancelado, Entregado, Pendiente;
        

        @JsonCreator
        public static Estado fromString(String value) {
            if (value == null) return null;
            for (Estado estado : Estado.values()) {
                if (estado.name().equalsIgnoreCase(value)) { // Permite ADMIN y "admin"
                    return estado;
                }
            }
            return null; // Evita que Spring falle antes de validar
        }

        @JsonValue
        public String toJson() {
            return name();
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("estado")
    private Estado estado;

}




   

    

       


    
