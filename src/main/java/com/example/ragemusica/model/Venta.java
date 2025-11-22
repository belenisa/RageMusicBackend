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
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int cantidad;

    @Column(name = "precio_total", nullable = false)
    private double precioTotal;


    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoVenta estadoVenta;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private MetodoPago pago;

    @ManyToOne
    @JoinColumn(name = "id_envio", nullable = false)
    private MetodoEnvio envio;
}
