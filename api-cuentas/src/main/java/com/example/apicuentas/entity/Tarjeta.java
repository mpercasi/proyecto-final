package com.example.apicuentas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Tarjetas")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo;
    private String numero;
    private String mExpiracion;
    private String aExpiracion;
    private String status;
    private String medioPago;
    private String nombreCliente;


    private int codigoSeguridad;


    private Tarjeta() {

    }

    public Tarjeta(String tipo, String numero, String mExpiracion, String aExpiracion, String status, String medioPago, int codigoSeguridad, String nombreCliente) {
        this.tipo = tipo;
        this.numero = numero;
        this.mExpiracion = mExpiracion;
        this.aExpiracion = aExpiracion;
        this.status = status;
        this.medioPago = medioPago;
        this.codigoSeguridad = codigoSeguridad;
        this.nombreCliente = nombreCliente;
    }


    public Tarjeta(String numero, String nombreCliente) {
        this.numero = numero;
        this.nombreCliente = nombreCliente;
    }
}
