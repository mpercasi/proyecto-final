package com.example.apiInversiones.repository;

import com.example.apiInversiones.entity.Inversion;
import com.example.apiInversiones.entity.PlazoFijo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InversionRepository extends CrudRepository<Inversion, Integer> {

    @Query(value = "SELECT c.id FROM CUENTAS c WHERE c.num_cliente=:numCliente AND c.tipo='Corriente'", nativeQuery = true)
    int obtenerIdCuenta(int numCliente);

    @Modifying
    @Query(value = "INSERT INTO CUENTAS_INVERSIONES (cuenta_id, inversiones_id) VALUES (:cuentaId, :inversionId)", nativeQuery = true)
    void asignarInversionACuentaCliente(int cuentaId, int inversionId);
}
