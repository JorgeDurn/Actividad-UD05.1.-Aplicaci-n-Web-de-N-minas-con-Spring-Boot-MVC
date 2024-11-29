package com.empresa.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.spring.model.Empleado;
import com.empresa.spring.repository.EmpleadoRepository;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con empleados.
 */
@Service
public class EmpleadoService {

    /** Repositorio para realizar operaciones CRUD en la base de datos. */
    @Autowired
    private EmpleadoRepository empleadoRepository;

    /**
     * Obtiene todos los empleados.
     * @return una lista de todos los empleados.
     */
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    /**
     * Busca un empleado por su DNI.
     * @param dni el DNI del empleado.
     * @return el empleado si existe, o null si no.
     */
    public Empleado obtenerPorDni(String dni) {
        return empleadoRepository.findById(dni).orElse(null);
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     * @param empleado el empleado a guardar.
     */
    public void guardar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    /**
     * Actualiza un empleado existente.
     * @param empleado el empleado a actualizar.
     */
    public void editar(Empleado empleado) {
        if (empleadoRepository.existsById(empleado.getDni())) {
            empleadoRepository.save(empleado);
        }
    }

    /**
     * Elimina un empleado por su DNI.
     * @param dni el DNI del empleado a eliminar.
     */
    public void eliminar(String dni) {
        empleadoRepository.deleteById(dni);
    }

    /**
     * Elimina un empleado si existe en la base de datos.
     * @param dni el DNI del empleado.
     * @return true si el empleado fue eliminado, false si no existe.
     */
    public boolean eliminarSiExiste(String dni) {
        if (empleadoRepository.existsById(dni)) {
            empleadoRepository.deleteById(dni);
            return true;
        }
        return false;
    }

    /**
     * Verifica si un empleado existe por su DNI.
     * @param dni el DNI del empleado.
     * @return true si el empleado existe, false si no.
     */
    public boolean existePorDni(String dni) {
        return empleadoRepository.findByDni(dni) != null;
    }
}
