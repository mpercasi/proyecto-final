package com.example.tarjetas.controller;

import com.example.tarjetas.entity.Cuenta;
import com.example.tarjetas.entity.DetalleTarjeta;
import com.example.tarjetas.entity.Tarjeta;
import com.example.tarjetas.exceptions.DuplicatedException;
import com.example.tarjetas.exceptions.NoValidAccountException;
import com.example.tarjetas.exceptions.NonExistentException;
import com.example.tarjetas.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
@CrossOrigin(origins = {"x"})
public class TarjetasController {

    @Autowired
    private SystemService system;

    @GetMapping("/consultar/{numCliente}")
    public List<Tarjeta> getTarjetas(@PathVariable int numCliente) throws NonExistentException{
        List<Tarjeta> tarjetas = system.obtenerTarjetas(numCliente);
        if (tarjetas.isEmpty()){
            throw new NonExistentException();
        }
        return tarjetas;
    }

    @PostMapping("/crear-tarjeta/{numCliente}")
    public ResponseEntity<String> createTarjetas(@PathVariable int numCliente, @RequestBody Tarjeta tarjeta) throws DuplicatedException, NoValidAccountException {
        System.out.println("Llegó peticion");
        List<Tarjeta> tarjetas = system.obtenerTarjetas(numCliente);
        List<Cuenta> cuentasCorrientes = system.obtenerCuentasCorrientes(numCliente);
        if (!tarjetas.isEmpty()){
            throw new DuplicatedException();
        }

        if (cuentasCorrientes.isEmpty()){
            throw new NoValidAccountException();
        }
        return ResponseEntity.ok(system.asociarTarjeta(tarjeta, cuentasCorrientes.get(0)));
    }
}
