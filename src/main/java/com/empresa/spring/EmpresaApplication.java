package com.empresa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 */
@SpringBootApplication
public class EmpresaApplication {

    /**
     * Método principal que inicia la aplicación.
     * @param args argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(EmpresaApplication.class, args);
    }
}
