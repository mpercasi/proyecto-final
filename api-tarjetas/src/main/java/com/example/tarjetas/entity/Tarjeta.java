package com.example.tarjetas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "Tarjetas")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String tipo;
    private String numero;
    private String mExpiracion;
    private String aExpiracion;
    private int codigoSeguridad;
    private String estado;
}
