package com.example.apicuentas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Table(name = "Pagos")
public class Pago {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;

    private Integer numPago;
    private String status;
    private LocalDate fechaPago;


    public Pago() {

    }

    public Pago(Integer numPago, String status, LocalDate fechaPago) {
        this.numPago = numPago;
        this.status = status;
        this.fechaPago = fechaPago;
    }

    public Pago(Integer numTarjeta, String status) {
        this.numPago = numTarjeta;
        this.status = status;
    }
}
