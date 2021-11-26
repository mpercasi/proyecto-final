package com.example.bank.controller;

import com.example.bank.entity.Cuenta;
import com.example.bank.entity.Prestamo;
import com.example.bank.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
@CrossOrigin(origins = {"*"})
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private PrestamoService clienteService;

    @PostMapping("/{numCliente}")
    public ResponseEntity<String> addPrestamo (@RequestBody Prestamo prestamo, @PathVariable("numCliente") int numCliente){
        prestamo.setCuota((prestamo.getMonto() + (prestamo.getMonto() * 0.10)) / prestamo.getPlazoMeses());
        List<Cuenta> cuentas = clienteService.buscarCuenta(numCliente);

        if (prestamoService.verificarSaldo(cuentas) && prestamoService.verificarPrestamosActuales(numCliente)){
            prestamoService.agregarPrestamo(prestamo);
            return ResponseEntity.ok("El Préstamo fue realizado con éxito");
        }
        else {
            return ResponseEntity.status(400).body("Saldo insuficiente");
        }
    }

//    @GetMapping("/Prestamos")
//    public List<Prestamo> allPrestamos() {
//        return (List<Prestamo>) prestamoService.findAll();
//    }

//    @GetMapping("/{numPrestamo}")
//    public Prestamo getPrestamos(@PathVariable("numPrestamo") int numPrestamo) {
//        Optional <Prestamo> prestamo = prestamoService.getPrestamoByNumPrestamo(numPrestamo);
//        if (prestamo.isPresent()){
//            return prestamo.get();
//        }
//        else {
////            meter exception
//            return null;
//        }
//    }
}
