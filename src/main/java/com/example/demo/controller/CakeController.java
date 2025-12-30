package com.example.demo.controller;

import com.example.demo.model.Cake;
import com.example.demo.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para manejar las peticiones relacionadas con pasteles.
 */
@RestController
public class CakeController {

    @Autowired
    private CakeRepository cakeRepository;

    /**
     * Endpoint raíz que retorna un mensaje de bienvenida.
     * 
     * @return Mensaje de bienvenida
     */
    @GetMapping("/")
    public String hello() {
        return "Hola Mundo Spring Boot";
    }

    /**
     * Endpoint que retorna la lista de todos los pasteles.
     * Por ahora retorna una lista vacía o puede usar el repositorio para obtener datos.
     * 
     * @return Lista de objetos Cake
     */
    @GetMapping("/cakes")
    public List<Cake> getAllCakes() {
        // Opción 1: Retornar lista vacía
        // return new ArrayList<>();
        
        // Opción 2: Usar el repositorio para obtener todos los pasteles
        return cakeRepository.findAll();
    }
}

