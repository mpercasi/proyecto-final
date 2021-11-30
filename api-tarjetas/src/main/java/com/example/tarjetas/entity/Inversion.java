package com.example.tarjetas.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
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

    public Inversion(String tipo, PlazoFijo plazo, String fechaVencimiento) {
        this.tipo = tipo;
        this.plazo = plazo;
        this.fechaVencimiento = fechaVencimiento;
    }
}
