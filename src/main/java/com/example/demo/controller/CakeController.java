package com.example.demo.controller;

import com.example.demo.model.Cake;
import com.example.demo.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar las peticiones relacionadas con pasteles.
 */
@RestController
@SuppressWarnings("java:S4684")
public class CakeController {

    private final CakeRepository cakeRepository;

    @Autowired
    public CakeController(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

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
     * 
     * @return Lista de objetos Cake
     */
    @GetMapping("/cakes")
    public List<Cake> getAllCakes() {
        return cakeRepository.findAll();
    }

    /**
     * Endpoint que retorna un pastel específico por su ID.
     * 
     * @param id El identificador del pastel a buscar
     * @return ResponseEntity con el Cake si existe (200 OK), o 404 Not Found si no existe
     */
    @GetMapping("/cakes/{id}")
    public ResponseEntity<Cake> getCakeById(@PathVariable Long id) {
        Optional<Cake> cake = cakeRepository.findById(id);
        
        if (cake.isPresent()) {
            return ResponseEntity.ok(cake.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para crear un nuevo pastel.
     * 
     * @param cake El objeto Cake a guardar (viene en el body de la petición)
     * @return ResponseEntity con el Cake guardado y código 201 Created
     */
    @PostMapping("/cakes")
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        Cake savedCake = cakeRepository.save(cake);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCake);
    }

    /**
     * Endpoint para actualizar un pastel existente.
     * 
     * @param id El identificador del pastel a actualizar
     * @param cakeDetails Los nuevos datos del pastel (viene en el body de la petición)
     * @return ResponseEntity con el Cake actualizado (200 OK) si existe, o 404 Not Found si no existe
     */
    @PutMapping("/cakes/{id}")
    public ResponseEntity<Cake> updateCake(@PathVariable Long id, @RequestBody Cake cakeDetails) {
        Optional<Cake> optionalCake = cakeRepository.findById(id);
        
        if (optionalCake.isPresent()) {
            Cake cake = optionalCake.get();
            cake.setTitle(cakeDetails.getTitle());
            cake.setDescription(cakeDetails.getDescription());
            Cake updatedCake = cakeRepository.save(cake);
            return ResponseEntity.ok(updatedCake);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar un pastel por su ID.
     * 
     * @param id El identificador del pastel a eliminar
     * @return ResponseEntity con código 204 No Content si existe y se elimina, o 404 Not Found si no existe
     */
    @DeleteMapping("/cakes/{id}")
    public ResponseEntity<Void> deleteCake(@PathVariable Long id) {
        Optional<Cake> optionalCake = cakeRepository.findById(id);
        
        if (optionalCake.isPresent()) {
            cakeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

