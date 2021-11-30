package com.example.apicuentas.repository;

import com.example.apicuentas.entity.Pago;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository

public interface PagoDao extends CrudRepository<Pago,Integer> {

    @Query(value ="select status from pagos where num_pago=:num_pago", nativeQuery = true)
    public String findPagoconsultarstatus(@Param("num_pago") Integer numpago);

    @Query(value = "select num_pago from pagos where num_pago=:num_pago", nativeQuery = true)
    public Integer findPagoValidarNumPago(@Param("num_pago")  Integer numpago);

    @Modifying
    @Query(value = "UPDATE pagos SET status =:status WHERE num_pago=:num_pago ", nativeQuery = true)
    void updateStatusPago(@Param("status") String status, @Param("num_pago") Integer numpago);


    @Query(value = "select * from pagos where fecha_pago=(select Max(fecha_pago)  from pagos)",nativeQuery = true)
    public List<Pago> findListarPagoPorultimoMes();

    @Query(value = "select * from pagos where fecha_pago=:fecha_pago AND fecha_pago=:fecha_pago",nativeQuery = true)
    public List<Pago> findListarPagoPorFechas(@Param("fecha_pago") Date fechaDesde, @Param("fecha_pago") Date fechaHasta);

}
