package com.example.tarjetas.repository;

import com.example.tarjetas.entity.Tarjeta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer> {
    @Query(value = "SELECT t.* FROM CUENTAS c INNER JOIN CUENTAS_TARJETAS ct ON c.id = ct.cuenta_id \n" +
            "INNER JOIN TARJETAS t ON ct.tarjetas_id = t.id WHERE c.num_cliente=:numCliente AND c.tipo = 'Corriente'", nativeQuery = true)
    List<Tarjeta> obtenerTarjetas(int numCliente);

    @Modifying
    @Query(value = "INSERT INTO CUENTAS_TARJETAS (cuenta_id, tarjetas_id) VALUES (:id_cuenta, :id_tarjeta)", nativeQuery = true)
    void asignarTarjetaACuenta(Integer id_tarjeta, Integer id_cuenta);
}
