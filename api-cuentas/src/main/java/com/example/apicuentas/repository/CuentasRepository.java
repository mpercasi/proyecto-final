package com.example.apicuentas.repository;

import com.example.apicuentas.entity.Cuenta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CuentasRepository extends CrudRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM CUENTAS c WHERE c.num_cliente=:numCliente", nativeQuery = true)
    List<Cuenta> obtenerCuentas(int numCliente);

    @Modifying
    @Query(value = "INSERT INTO CLIENTES_CUENTAS (cliente_id, cuentas_id) VALUES (:cliente, :cuenta)", nativeQuery = true)
    void asignarCuenta(@Param("cliente") int cliente, @Param("cuenta")int cuenta);

    @Query(value = "SELECT c.id FROM CUENTAS c WHERE c.num_cliente=:numCliente AND c.tipo=:tipo", nativeQuery = true)
    int obtenerIdCuenta(int numCliente, String tipo);

    @Query(value = "SELECT c.id FROM CLIENTES c WHERE c.num_cliente=:numCliente", nativeQuery = true)
    int obtenerIdCliente(int numCliente);


}
