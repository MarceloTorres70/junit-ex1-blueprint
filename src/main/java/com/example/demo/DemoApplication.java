package com.example.demo;

import com.example.demo.model.Cake;
import com.example.demo.repository.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Clase principal de la aplicación Spring Boot.
 * Esta clase inicia el contexto de Spring y levanta el servidor embebido.
 */
@SpringBootApplication
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Bean que se ejecuta al iniciar la aplicación para cargar datos de prueba.
     * Inserta pasteles de ejemplo en la base de datos.
     */
    @Bean
    CommandLineRunner initDatabase(CakeRepository cakeRepository) {
        return args -> {
            // Verificar si ya existen datos para evitar duplicados
            if (cakeRepository.count() == 0) {
                // Crear y guardar el primer pastel
                Cake cake1 = new Cake();
                cake1.setTitle("Banana cake");
                cake1.setDescription("Donkey kongs favourite");
                cakeRepository.save(cake1);

                // Crear y guardar el segundo pastel
                Cake cake2 = new Cake();
                cake2.setTitle("Birthday cake");
                cake2.setDescription("A yearly treat");
                cakeRepository.save(cake2);

                logger.info("Datos de prueba cargados exitosamente.");
            }
        };
    }
}

