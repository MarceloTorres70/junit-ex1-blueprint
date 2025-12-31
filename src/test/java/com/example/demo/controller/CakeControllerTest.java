package com.example.demo.controller;

import com.example.demo.model.Cake;
import com.example.demo.repository.CakeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Clase de pruebas de integración para CakeController.
 * Usa @WebMvcTest para cargar solo el contexto web del controlador.
 */
@WebMvcTest(CakeController.class)
public class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeRepository cakeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Prueba 1: GET /cakes - Retorna lista vacía cuando no hay pasteles
     */
    @Test
    public void getCakes_shouldReturnEmptyList() throws Exception {
        // Arrange: Simular que el repositorio retorna una lista vacía
        when(cakeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert: Realizar petición GET y verificar respuesta
        mockMvc.perform(get("/cakes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        // Verificar que el método del repositorio fue llamado
        verify(cakeRepository, times(1)).findAll();
    }

    /**
     * Prueba 2: GET /cakes/{id} - Retorna un pastel cuando existe
     */
    @Test
    public void getCakeById_shouldReturnCake() throws Exception {
        // Arrange: Crear un pastel de prueba
        Cake cake = new Cake("Chocolate Cake", "Delicious chocolate cake");
        cake.setId(1L);

        // Simular que el repositorio encuentra el pastel
        when(cakeRepository.findById(1L)).thenReturn(Optional.of(cake));

        // Act & Assert: Realizar petición GET y verificar respuesta
        mockMvc.perform(get("/cakes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Chocolate Cake"))
                .andExpect(jsonPath("$.description").value("Delicious chocolate cake"));

        // Verificar que el método del repositorio fue llamado
        verify(cakeRepository, times(1)).findById(1L);
    }

    /**
     * Prueba 3: GET /cakes/{id} - Retorna 404 cuando el pastel no existe
     */
    @Test
    public void getCakeById_shouldReturnNotFound() throws Exception {
        // Arrange: Simular que el repositorio no encuentra el pastel
        when(cakeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Realizar petición GET y verificar respuesta 404
        mockMvc.perform(get("/cakes/99"))
                .andExpect(status().isNotFound());

        // Verificar que el método del repositorio fue llamado
        verify(cakeRepository, times(1)).findById(99L);
    }

    /**
     * Prueba 4: PUT /cakes/{id} - Actualiza un pastel existente
     */
    @Test
    public void updateCake_shouldUpdateCake() throws Exception {
        // Arrange: Crear un pastel existente
        Cake existingCake = new Cake("Old Title", "Old Description");
        existingCake.setId(1L);

        // Crear los nuevos datos del pastel
        Cake updatedCakeDetails = new Cake("New Title", "New Description");

        // Crear el pastel actualizado que será guardado
        Cake savedCake = new Cake("New Title", "New Description");
        savedCake.setId(1L);

        // Simular que el repositorio encuentra el pastel
        when(cakeRepository.findById(1L)).thenReturn(Optional.of(existingCake));
        // Simular que el repositorio guarda el pastel actualizado
        when(cakeRepository.save(any(Cake.class))).thenReturn(savedCake);

        // Act & Assert: Realizar petición PUT y verificar respuesta
        mockMvc.perform(put("/cakes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCakeDetails)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Title"))
                .andExpect(jsonPath("$.description").value("New Description"));

        // Verificar que los métodos del repositorio fueron llamados
        verify(cakeRepository, times(1)).findById(1L);
        verify(cakeRepository, times(1)).save(any(Cake.class));
    }

    /**
     * Prueba 5: PUT /cakes/{id} - Retorna 404 cuando el pastel no existe
     */
    @Test
    public void updateCake_shouldReturnNotFound() throws Exception {
        // Arrange: Crear los datos del pastel a actualizar
        Cake updatedCakeDetails = new Cake("New Title", "New Description");

        // Simular que el repositorio no encuentra el pastel
        when(cakeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Realizar petición PUT y verificar respuesta 404
        mockMvc.perform(put("/cakes/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCakeDetails)))
                .andExpect(status().isNotFound());

        // Verificar que findById fue llamado pero save no
        verify(cakeRepository, times(1)).findById(99L);
        verify(cakeRepository, never()).save(any(Cake.class));
    }

    /**
     * Prueba 6: DELETE /cakes/{id} - Elimina un pastel existente
     */
    @Test
    public void deleteCake_shouldDeleteCake() throws Exception {
        // Arrange: Crear un pastel existente
        Cake existingCake = new Cake("Cake to Delete", "This cake will be deleted");
        existingCake.setId(1L);

        // Simular que el repositorio encuentra el pastel
        when(cakeRepository.findById(1L)).thenReturn(Optional.of(existingCake));
        // No necesitamos simular deleteById porque retorna void

        // Act & Assert: Realizar petición DELETE y verificar respuesta 204 No Content
        mockMvc.perform(delete("/cakes/1"))
                .andExpect(status().isNoContent());

        // Verificar que los métodos del repositorio fueron llamados
        verify(cakeRepository, times(1)).findById(1L);
        verify(cakeRepository, times(1)).deleteById(1L);
    }
}

