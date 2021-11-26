package com.example.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String estado;
    private double monto;
    private String tipoMoneda;
    private String tipo;
    private double montoSobreGiro;
    private int mesesAhorro;
    private int numCliente;

    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name="Tarjetas")
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name="Inversiones")
    private List<Inversion> inversiones = new ArrayList<>();
}
