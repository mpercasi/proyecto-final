package com.example.tarjetas.repository;

import com.example.tarjetas.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM CUENTAS WHERE num_cliente=:numCliente AND tipo = 'Corriente'", nativeQuery = true)
    List<Cuenta> obtenerCuentasCorrientes(int numCliente);

}
