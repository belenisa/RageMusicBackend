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
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RolUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public enum Rol {
        ADMIN, CLIENTE, GERENTE, VENTAS, LOGISTICA, PROVEEDOR;

        @JsonCreator
        public static Rol fromString(String value) {
            if (value == null) return null;

            for (Rol rol : Rol.values()) {
                if (rol.name().equalsIgnoreCase(value)) { // Permite ADMIN y "admin"
                    return rol;
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
    @JsonProperty("rol")
    private Rol rol;

}