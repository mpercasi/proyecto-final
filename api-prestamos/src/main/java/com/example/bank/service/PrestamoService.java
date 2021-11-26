package com.example.bank.service;

import com.example.bank.entity.Cuenta;
import com.example.bank.entity.Prestamo;
import com.example.bank.repository.CuentasRepository;
import com.example.bank.repository.PrestamoRepository;
import com.example.bank.repository.PrestamoRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {


    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private CuentasRepository cuentasRepository;


//    public List<Prestamo> findAll() {
//        return prestamoRepository.getPrestamo();
//    }

//    public void addPrestamo(Prestamo prestamo) {
//        prestamoRepository.addPrestamo(prestamo);
//    }


    public List<Cuenta> buscarCuenta(int numCliente) {
        return cuentasRepository.buscarCuenta(numCliente);
    }

    public boolean verificarSaldo(List<Cuenta> cuentas) {
        for (Cuenta c : cuentas) {
//            System.out.println(c.getMonto() == tipoCuentaSolicitada);
            if ((int)c.getMonto() >= 20000){
                return true;
            }
        }
        return false;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
        int idPrestamo = prestamoRepository.obtenerIdPrestamo(prestamo.getNumCliente());
        int idCuenta = prestamoRepository.obtenerIdCuenta(prestamo.getNumCliente());

        prestamoRepository.asignarPrestamo(idCuenta, idPrestamo);
    }

    public boolean verificarPrestamosActuales(int numCliente) {
        if (prestamoRepository.obtenerTodosLosPrestamos(numCliente).size() != 0){
            return false;
        }

        return true;
    }
}
