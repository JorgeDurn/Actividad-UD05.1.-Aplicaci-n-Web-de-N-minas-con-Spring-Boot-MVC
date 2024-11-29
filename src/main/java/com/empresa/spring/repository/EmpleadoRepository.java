package com.empresa.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.spring.model.Empleado;

/**
 * Repositorio para realizar operaciones CRUD sobre la entidad Empleado.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    /**
     * Busca un empleado por su DNI.
     * @param dni el DNI del empleado.
     * @return el empleado si existe, o null si no.
     */
    Empleado findByDni(String dni);
}
