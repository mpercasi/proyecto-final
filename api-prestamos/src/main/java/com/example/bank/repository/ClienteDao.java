package com.example.bank.repository;

import com.example.bank.entity.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ClienteDao extends CrudRepository<Cliente, Integer> {

    @Query(value = "select * from clientes where nombre= :nombre and contra=:contra", nativeQuery = true)
    public Optional<Cliente> findClienteValidarUsuario(String nombre, String contra);


    @Query(value = "select contra from clientes where nombre =:nombre ", nativeQuery = true)
    public String finClienteValidarcontra(String nombre);

    @Query(value = "select nombre from clientes where nombre=:nombre ",nativeQuery = true)
    public  String findClienteValidarUsuarioPago(String nombre);


    @Modifying
    @Query(value = "UPDATE clientes SET status =:status WHERE nombre=:nombre ", nativeQuery = true)
    void updateCustomer(String status,String nombre);

    @Query(value = "select status from clientes where nombre =:nombre ", nativeQuery = true)
    public String finClienteValidarstatus(String nombre);






}