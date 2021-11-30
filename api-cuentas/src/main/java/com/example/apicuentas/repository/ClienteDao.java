package com.example.apicuentas.repository;

import com.example.apicuentas.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ClienteDao extends CrudRepository<Cliente,Integer> {

    @Query(value = "select * from clientes where nombre=:nombre and contra=:contra", nativeQuery = true)
    public Optional<Cliente> findClienteValidarUsuario(String nombre, String contra);

    @Query(value = "select nombre from clientes where nombre=:nombre ",nativeQuery = true)
    public  String findClienteValidarUsuarioPago(String nombre);

}
