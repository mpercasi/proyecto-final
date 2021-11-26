package com.example.bank.service;

import com.example.bank.repository.ClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteDao clienteDao;

    public void updateStatus(String estado, String nombre) {
        clienteDao.updateCustomer(estado, nombre);
    }

    public String validarStatus(String usuario) {
        return clienteDao.finClienteValidarstatus(usuario);
    }
}

