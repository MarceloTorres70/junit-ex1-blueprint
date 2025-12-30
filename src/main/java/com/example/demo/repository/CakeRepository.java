package com.example.demo.repository;

import com.example.demo.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Cake.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas.
 */
@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    // JpaRepository proporciona métodos como:
    // findAll(), findById(), save(), delete(), etc.
}

