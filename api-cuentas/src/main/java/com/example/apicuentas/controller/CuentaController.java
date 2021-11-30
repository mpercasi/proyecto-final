package com.example.apicuentas.controller;

import com.example.apicuentas.entity.Cuenta;
import com.example.apicuentas.entity.Pago;
import com.example.apicuentas.entity.Tarjeta;
import com.example.apicuentas.exceptions.DuplicatedException;
import com.example.apicuentas.exceptions.NonExistentCustomerPasswordException;
import com.example.apicuentas.exceptions.NonExistentException;
import com.example.apicuentas.exceptions.NonExisteteTarjetaDebito;
import com.example.apicuentas.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/pagos")
//@RequestMapping("/cuentas")
@CrossOrigin(origins = {"*"})
public class CuentaController {
    @Autowired
    private SystemService system;

    @GetMapping("/{numCliente}")
    public List<Cuenta> getCuentas(@PathVariable int numCliente) throws NonExistentException {
        List<Cuenta> cuentas = system.obtenerCuentas(numCliente);
        if (cuentas.isEmpty()){
            throw new NonExistentException();
        }
        return cuentas;
    }

    @PostMapping
    public ResponseEntity crearCuenta(@RequestBody Cuenta cuenta) throws DuplicatedException {
        List<Cuenta> cuentas = system.obtenerCuentas(cuenta.getNumCliente());
        if(system.validarEstatus(cuentas, cuenta.getTipo())) {
            throw new DuplicatedException();
        }
        return ResponseEntity.ok(system.agregarCuenta(cuenta, cuenta.getNumCliente()));
    }


    //METODOS DE PAGO

    @GetMapping("/clientePago/{usuario}/{numTarjeta}/{numPago}")
    public ResponseEntity<String> getUsuarioPago(@PathVariable("usuario") String usuario, @PathVariable("numTarjeta") String numTarjeta, @PathVariable("numPago") Integer numeroPago) throws NonExistentException, NonExisteteTarjetaDebito {
        //Metodos para validar si existe  usuario
        String cliente = system.getUsuarioPago(usuario);
        String validarUsuario = String.valueOf(cliente);

        //Metodos para validar Si existe NUMPAGO
        Integer validarNumPago = system.searchNumPago(numeroPago);

        //Metodos para pagos
        String estadoTrajeta = system.getTarjetaValidarStatus(usuario, numTarjeta);
        String mediodePago = system.getTarjetaMediodePago(usuario, numTarjeta);
        String validarTarjeta = system.getTarjetaDebito(numTarjeta);

        String estadoPagado = "PAGADO";
        String estadoFallido = "FALLIDO";


        if (usuario.isEmpty() || numTarjeta == null || numeroPago == null) {

            throw new NonExistentException();
        }


        if (validarUsuario.equals(usuario) /*&& validarNumPago.equals(numeroPago)*/) {


            if (estadoTrajeta.equalsIgnoreCase("vigente") && mediodePago.equalsIgnoreCase("mediodepago")) {

                String consultaStatusPago = system.getTarjetaConsultarStatusPago(numeroPago);

                if (consultaStatusPago.equalsIgnoreCase("Pagado")) {
                    return ResponseEntity.ok("Ya se realizo el pago a esta referencia ");
                } else
                    system.updateStatusPago(numeroPago, estadoPagado);
                return ResponseEntity.ok("Estado de transaccion:'PAGADO' " + "Tu pago fue aceptado");

            } else
                system.updateStatusPago(numeroPago, estadoFallido);
            return ResponseEntity.ok("Estado de Transaccion :'FALLIDO' "+
                    "Tu pago fue Rechazado");
        }


        throw new NonExisteteTarjetaDebito();
    }

    @PutMapping("/CambiarEstadoDePago")
    public ResponseEntity updateCustomer(@RequestBody Tarjeta tarjeta) throws NonExistentException {

        String cliente = system.getUsuarioPago(tarjeta.getNombreCliente());
        String validarUsuario = String.valueOf(cliente);

        String numeroTrajeta = system.validarTarjetaUsuario(tarjeta.getNombreCliente(), tarjeta.getNumero());

        if (validarUsuario.equals(tarjeta.getNombreCliente())) {

        }

        if (tarjeta.getNombreCliente().isEmpty() && tarjeta.getMedioPago().isEmpty()) {
            throw new NonExistentException();
        }

        if (numeroTrajeta.equals(tarjeta.getNumero())) {
            system.updateTarjetaStausMedio(tarjeta.getMedioPago(), tarjeta.getNumero());
            return ResponseEntity.ok("Pagos updated successfully!");
        }
        return ResponseEntity.ok("No cuentas con esta tarjeta");
    }

    @GetMapping("/ListadoDePagos")
    public List<Pago> getListaDePagosUltimoMes() throws NonExistentException, NonExistentCustomerPasswordException {
        List<Pago> ListadoPagosPorUltimoMes = system.getPagosPorMes();
        return ListadoPagosPorUltimoMes;

    }

    @GetMapping("/ListadoDePagosPorFechas/{FechaDesde}/{FechaHasta}")
    public List<Pago> getListaDePagosPorFecha(@PathVariable("FechaDesde") Date fechaDesde, @PathVariable("FechaHasta") Date fechaHasta) throws NonExistentException, NonExistentCustomerPasswordException {

        //  String FechadesdePagos = fechaDesde;

        List<Pago> ListadoPagosPorUltimoMes = system.getPagosPorFechas(fechaDesde, fechaHasta);
        return ListadoPagosPorUltimoMes;

    }

}
