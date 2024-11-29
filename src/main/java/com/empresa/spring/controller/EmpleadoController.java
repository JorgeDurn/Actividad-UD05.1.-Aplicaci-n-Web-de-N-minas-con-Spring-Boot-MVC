package com.empresa.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.spring.model.Empleado;
import com.empresa.spring.service.EmpleadoService;

import java.util.List;

/**
 * Controlador para gestionar las solicitudes HTTP relacionadas con empleados.
 */
@Controller
@RequestMapping("/empresa")
public class EmpleadoController {

    /** Servicio para gestionar las operaciones de empleados. */
    @Autowired
    private EmpleadoService empleadoService;

    /**
     * Muestra el formulario para crear un nuevo empleado.
     * @param model el modelo para pasar datos a la vista.
     * @return el nombre de la vista "empleados/formulario".
     */
    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     * Valida si el DNI ya existe antes de guardar.
     * @param empleado el empleado a guardar.
     * @param redirectAttributes para agregar mensajes de éxito o error.
     * @return redirección a la lista de empleados o al formulario si ocurre un error.
     */
    @PostMapping("/crear")
    public String guardarEmpleado(@ModelAttribute Empleado empleado, RedirectAttributes redirectAttributes) {
        if (empleadoService.existePorDni(empleado.getDni())) {
            redirectAttributes.addFlashAttribute("error", "El DNI ya existe.");
            return "redirect:/empresa/crear";
        }

        empleadoService.guardar(empleado);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado creado correctamente.");
        return "redirect:/empresa";
    }

    /**
     * Muestra el formulario para editar un empleado existente.
     * Si el empleado no se encuentra, redirige a la lista con un mensaje de error.
     * @param dni el DNI del empleado a editar.
     * @param model el modelo para pasar datos a la vista.
     * @param redirectAttributes para agregar mensajes de error.
     * @return el nombre de la vista "empleados/formulario".
     */
    @GetMapping("/editar/{dni}")
    public String mostrarFormularioDeEdicion(@PathVariable String dni, Model model, RedirectAttributes redirectAttributes) {
        Empleado empleado = empleadoService.obtenerPorDni(dni);
        if (empleado == null) {
            redirectAttributes.addFlashAttribute("error", "Empleado no encontrado.");
            return "redirect:/empresa";
        }
        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    /**
     * Actualiza un empleado existente en la base de datos.
     * Valida si el DNI existe antes de guardar los cambios.
     * @param empleado el empleado a actualizar.
     * @param redirectAttributes para agregar mensajes de éxito o error.
     * @return redirección a la lista de empleados.
     */
    @PostMapping("/guardar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado, RedirectAttributes redirectAttributes) {
        if (!empleadoService.existePorDni(empleado.getDni())) {
            redirectAttributes.addFlashAttribute("error", "El DNI no existe.");
            return "redirect:/empresa/editar/" + empleado.getDni();
        }

        empleadoService.editar(empleado);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado actualizado correctamente.");
        return "redirect:/empresa";
    }

    /**
     * Lista todos los empleados disponibles.
     * @param model el modelo para pasar la lista de empleados a la vista.
     * @return el nombre de la vista "empleados/lista".
     */
    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.obtenerTodos());
        return "empleados/lista";
    }

    /**
     * Busca un empleado por su DNI y muestra el resultado en la vista de lista.
     * Si no se encuentra el empleado, redirige con un mensaje de error.
     * @param dni el DNI del empleado a buscar.
     * @param model el modelo para pasar datos a la vista.
     * @param redirectAttributes para agregar mensajes de error.
     * @return el nombre de la vista "empleados/lista" o una redirección.
     */
    @GetMapping("/buscar")
    public String buscarEmpleado(@RequestParam String dni, Model model, RedirectAttributes redirectAttributes) {
        Empleado empleado = empleadoService.obtenerPorDni(dni);
        if (empleado == null) {
            redirectAttributes.addFlashAttribute("error", "Empleado no encontrado.");
            return "redirect:/empresa";
        }
        model.addAttribute("empleados", List.of(empleado));
        return "empleados/lista";
    }

    /**
     * Elimina un empleado por su DNI.
     * Valida si el empleado existe antes de eliminar.
     * @param dni el DNI del empleado a eliminar.
     * @param redirectAttributes para agregar mensajes de éxito o error.
     * @return redirección a la lista de empleados.
     */
    @PostMapping("/eliminar/{dni}")
    public String eliminarEmpleado(@PathVariable String dni, RedirectAttributes redirectAttributes) {
        if (empleadoService.eliminarSiExiste(dni)) {
            redirectAttributes.addFlashAttribute("mensaje", "Empleado eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Empleado no encontrado.");
        }
        return "redirect:/empresa";
    }
}
