package com.example.bank.repository;

import com.example.bank.entity.Cuenta;
import com.example.bank.entity.Prestamo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PrestamoRepository extends CrudRepository<Prestamo, Integer> {
//    List<Prestamo> getPrestamo();

//    void addPrestamo(Prestamo prestamo);


    @Query(value = "SELECT t.monto from Prestamo t WHERE t.id=:id")
    Optional<Prestamo> getPrestamoById (Integer id);

    @Query(value = "SELECT * FROM PRESTAMOS p WHERE p.num_cliente=:numCliente", nativeQuery = true)
    List<Prestamo> obtenerTodosLosPrestamos(int numCliente);

    @Query(value = "SELECT p.id FROM PRESTAMOS p WHERE p.num_cliente=:numCliente", nativeQuery = true)
    int obtenerIdPrestamo(int numCliente);

    @Query(value = "SELECT c.id FROM CUENTAS c WHERE c.num_cliente=:numCliente", nativeQuery = true)
    int obtenerIdCuenta(int numCliente);

    @Modifying
    @Query(value = "INSERT INTO CUENTAS_PRESTAMOS (cuenta_id, prestamos_id) VALUES (:idCuenta, :idPrestamo)", nativeQuery = true)
    void asignarPrestamo(int idCuenta, int idPrestamo);
}
