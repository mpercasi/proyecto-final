package com.example.tarjetas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DetalleTarjeta {
    private int id;
//    private String tipo_cuenta;
    private String tipo_moneda;
    private String tipo;
    private String numero;
    private String mExpiracion;
    private String aExpiracion;
    private int codigoSeguridad;
}
