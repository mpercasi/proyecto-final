package com.example.tarjetas.service;

import com.example.tarjetas.entity.Cuenta;
import com.example.tarjetas.entity.DetalleTarjeta;
import com.example.tarjetas.entity.Tarjeta;
import com.example.tarjetas.repository.CuentaRepository;
import com.example.tarjetas.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Tarjeta> obtenerTarjetas(int numCliente) {
        return tarjetaRepository.obtenerTarjetas(numCliente);
    }

    public List<Cuenta> obtenerCuentasCorrientes(int numCliente) {
        return cuentaRepository.obtenerCuentasCorrientes(numCliente);
    }

    public String asociarTarjeta(Tarjeta tarjeta, Cuenta cuenta) {
        tarjetaRepository.save(tarjeta);
        tarjetaRepository.asignarTarjetaACuenta(tarjeta.getId(), cuenta.getId());
        return "Se agregó la tarjeta exitosamente";
    }
}
