package com.example.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Inversiones")
public class Inversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo;
    @OneToOne(cascade = {CascadeType.ALL})
    //@Column(name="Plazos")
    private PlazoFijo plazo;
    private String fechaVencimiento;

    public Inversion() {
    }

    public Inversion(String tipo, PlazoFijo plazo, String fechaVencimiento) {
        this.tipo = tipo;
        this.plazo = plazo;
        this.fechaVencimiento = fechaVencimiento;
    }
}
