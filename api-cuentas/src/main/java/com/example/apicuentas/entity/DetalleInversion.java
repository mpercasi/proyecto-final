package com.example.apicuentas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter

public class DetalleInversion {
    private double monto;

    public DetalleInversion()
    {

    }
    public DetalleInversion(double monto) {
        this.monto = monto;
    }
}
