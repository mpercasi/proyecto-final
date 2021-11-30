package com.example.apicuentas.service;

import com.example.apicuentas.entity.Cuenta;
import com.example.apicuentas.entity.Pago;
import com.example.apicuentas.repository.ClienteDao;
import com.example.apicuentas.repository.CuentasRepository;
import com.example.apicuentas.repository.PagoDao;
import com.example.apicuentas.repository.TarjetaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SystemService {
    @Autowired
    private CuentasRepository cuentasRepository;

    @Autowired
    private PagoDao pagoDao;

    @Autowired
    private TarjetaDao tarjetaDao;
    @Autowired
    private ClienteDao clienteDao;


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

    ///Metodos de pago

    public String getUsuarioPago(String usuario) {
        return clienteDao.findClienteValidarUsuarioPago(usuario);
    }


    public String getTarjetaValidarStatus(String usuario, String numTarjeta) {
        return tarjetaDao.findTarjetasByNombreAndNumValidarstatus(usuario,numTarjeta);
    }

    public String getTarjetaMediodePago(String usuario, String numTarjeta) {
        return  tarjetaDao.findTarjetasByNombreAndNumValidarMedioDePago(usuario,numTarjeta);

    }


    public String getTarjetaDebito(String numTarjeta) {
        return tarjetaDao.findTarjetasValidarTarjeta(numTarjeta);

    }

    public void updateTarjetaStausMedio( String medioPago,String numTarjeta) {
        tarjetaDao.updateTarjetaStatusPago(medioPago,numTarjeta);
    }

    public String validarTarjetaUsuario(String nombreCliente, String numTrajeta) {
        return tarjetaDao.findTarjetasByNombreAndNumValidarTarjeta(nombreCliente,numTrajeta);
    }

    //Metodos de pago


    public void updateStatusPago(Integer numeroPago, String status) {

        pagoDao.updateStatusPago(status,numeroPago);
    }

    public String getTarjetaConsultarStatusPago(Integer numPago){
        return pagoDao.findPagoconsultarstatus(numPago);
    }

    public Integer searchNumPago(Integer numPago) {

        return pagoDao.findPagoValidarNumPago(numPago);

    }


    public List<Pago> getPagosPorMes() {
        return pagoDao.findListarPagoPorultimoMes();
    }

    public List<Pago> getPagosPorFechas(Date fechaDesde, Date fechaHasta) {
        return  pagoDao.findListarPagoPorFechas(fechaDesde,fechaHasta);
    }
}
