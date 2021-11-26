package com.example.bank.repository;

import com.example.bank.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CuentasRepository extends CrudRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM CUENTAS WHERE num_cliente=:numCliente", nativeQuery = true)
    List<Cuenta> buscarCuenta(int numCliente);
}