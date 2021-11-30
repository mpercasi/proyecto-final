package com.example.apicuentas.repository;

import com.example.apicuentas.entity.Tarjeta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TarjetaDao extends CrudRepository<Tarjeta,Integer> {

    @Query(value = "select numero from tarjetas where numero=:numero", nativeQuery = true)
    public String findTarjetasValidarTarjeta(@Param("numero") String numTarjeta);

    @Query(value = "select status from tarjetas where nombre_cliente=:nombre_cliente and numero=:numero",nativeQuery = true)
    public String findTarjetasByNombreAndNumValidarstatus(@Param("nombre_cliente") String nombreCliente, @Param("numero") String num_tarjeta);

    @Query(value = "select medio_pago from tarjetas where nombre_cliente=:nombre_cliente and numero=:numero",nativeQuery = true)
    public String  findTarjetasByNombreAndNumValidarMedioDePago(@Param("nombre_cliente") String nombreCliente, @Param("numero") String num_tarjeta);


    @Modifying
    @Query(value = "UPDATE tarjetas SET medio_pago=:medio_pago WHERE numero=:numero ", nativeQuery = true)
    void updateTarjetaStatusPago(@Param("medio_pago") String medio_pago, @Param("numero") String num_Tarjeta);

    @Query(value = "select num_tarjeta from tarjetas where nombre_cliente=:nombre_cliente and numero=:numero",nativeQuery = true)
    public String findTarjetasByNombreAndNumValidarTarjeta(@Param("nombre_cliente") String nombreCliente, @Param("numero") String num_tarjeta);



}
