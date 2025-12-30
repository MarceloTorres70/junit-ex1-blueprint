package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad Cake que representa un pastel en la base de datos.
 * Utiliza Lombok @Data para generar automáticamente getters, setters, toString, etc.
 */
@Entity
@Data
public class Cake {
    
    /**
     * Identificador único del pastel.
     * Se genera automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Título o nombre del pastel.
     */
    private String title;
    
    /**
     * Descripción del pastel.
     */
    private String description;
    
    // Constructores
    public Cake() {
        // Constructor por defecto requerido por JPA
    }
    
    public Cake(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    // Getters y Setters (generados por @Data, pero aquí por si Lombok no funciona)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}

