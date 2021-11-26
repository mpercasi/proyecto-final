package com.example.apicuentas.service;

import com.example.apicuentas.entity.Cuenta;
import com.example.apicuentas.repository.CuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {
    @Autowired
    private CuentasRepository cuentasRepository;

    public List<Cuenta> obtenerCuentas(int numCliente) {
        return cuentasRepository.obtenerCuentas(numCliente);
    }

    public boolean validarEstatus(List<Cuenta> cuentas, String tipoCuentaSolicitada) {
        for (Cuenta c : cuentas) {
            System.out.println(c.getTipo() == tipoCuentaSolicitada);
            if (tipoCuentaSolicitada.equals(c.getTipo())){
                return true;
            }
        }
        return false;
    }

    public Cuenta agregarCuenta(Cuenta cuenta, int numCliente) {
        cuentasRepository.save(cuenta);
        int idCuenta = cuentasRepository.obtenerIdCuenta(numCliente, cuenta.getTipo());
        int idCliente = cuentasRepository.obtenerIdCliente(numCliente);

        cuentasRepository.asignarCuenta(idCliente, idCuenta);

        return cuenta;
    }
}
