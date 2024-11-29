package com.empresa.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Clase que representa un empleado en la empresa.
 * Está mapeada a la tabla "empleados" en la base de datos.
 */
@Entity
@Table(name = "empleados")
public class Empleado {

    /** DNI del empleado (clave primaria). */
    @Id
    @Column(name = "dni")
    private String dni;

    /** Nombre del empleado. */
    @Column(name = "nombre")
    private String nombre;

    /** Sexo del empleado (H: Hombre, M: Mujer). */
    @Column(name = "sexo")
    private char sexo;

    /** Categoría del empleado (1-10). */
    @Min(1)
    @Max(10)
    @Column(name = "categoria")
    private int categoria;

    /** Años trabajados por el empleado. */
    @Min(0)
    @Column(name = "anyostrabajados")
    private int anyosTrabajados;

    /** Sueldo del empleado (no actualizable). */
    @Column(name = "sueldo", updatable = false)
    private double sueldo;


    /** @return el DNI del empleado. */
    public String getDni() { return dni; }

    /** @param dni el DNI a establecer para el empleado. */
    public void setDni(String dni) { this.dni = dni; }

    /** @return el nombre del empleado. */
    public String getNombre() { return nombre; }

    /** @param nombre el nombre a establecer para el empleado. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return el sexo del empleado. */
    public char getSexo() { return sexo; }

    /** @param sexo el sexo a establecer para el empleado. */
    public void setSexo(char sexo) { this.sexo = sexo; }

    /** @return la categoría del empleado. */
    public int getCategoria() { return categoria; }

    /** @param categoria la categoría a establecer para el empleado. */
    public void setCategoria(int categoria) { this.categoria = categoria; }

    /** @return los años trabajados por el empleado. */
    public int getAnyosTrabajados() { return anyosTrabajados; }

    /** @param anyosTrabajados los años trabajados a establecer. */
    public void setAnyosTrabajados(int anyosTrabajados) { this.anyosTrabajados = anyosTrabajados; }

    /** @return el sueldo del empleado. */
    public double getSueldo() { return sueldo; }
}
