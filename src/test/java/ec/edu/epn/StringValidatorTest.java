package ec.edu.epn;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para StringValidator.
 * Valida el comportamiento del método validateNotEmpty() con diferentes escenarios.
 */
public class StringValidatorTest {

    private StringValidator validator;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializar la instancia de StringValidator antes de cada prueba
        validator = new StringValidator();
    }

    /**
     * Prueba que validateNotEmpty() no lance excepción cuando se proporciona una cadena válida.
     * Una cadena válida es aquella que no es null, no está vacía y contiene al menos un carácter
     * que no sea espacio en blanco.
     */
    @Test
    void validateNotEmpty_cadenaValida_noLanzaExcepcion() {
        // Arrange: Preparar una cadena válida
        String validString = "texto válido";
        
        // Act & Assert: Verificar que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            validator.validateNotEmpty(validString);
        });
    }

    /**
     * Prueba que validateNotEmpty() lance IllegalArgumentException cuando se proporciona
     * una cadena vacía ("").
     * También verifica que el mensaje de la excepción sea el esperado.
     */
    @Test
    void validateNotEmpty_cadenaVacia_lanzaIllegalArgumentException() {
        // Arrange: Preparar una cadena vacía
        String emptyString = "";
        
        // Act: Ejecutar validateNotEmpty y capturar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validateNotEmpty(emptyString);
        });
        
        // Assert: Verificar que el mensaje de la excepción sea el esperado
        assertEquals("The string cannot be null or empty.", exception.getMessage());
    }

    /**
     * Prueba que validateNotEmpty() lance IllegalArgumentException cuando se proporciona
     * una cadena que contiene solo espacios en blanco.
     * También verifica que el mensaje de la excepción sea el esperado.
     */
    @Test
    void validateNotEmpty_cadenaConEspacios_lanzaIllegalArgumentException() {
        // Arrange: Preparar una cadena con solo espacios en blanco
        String whitespaceString = "   ";
        
        // Act: Ejecutar validateNotEmpty y capturar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validateNotEmpty(whitespaceString);
        });
        
        // Assert: Verificar que el mensaje de la excepción sea el esperado
        assertEquals("The string cannot be null or empty.", exception.getMessage());
    }

    /**
     * Prueba que validateNotEmpty() lance IllegalArgumentException cuando se proporciona
     * un valor null.
     * También verifica que el mensaje de la excepción sea el esperado.
     */
    @Test
    void validateNotEmpty_null_lanzaIllegalArgumentException() {
        // Arrange: Preparar un valor null
        String nullString = null;
        
        // Act: Ejecutar validateNotEmpty y capturar la excepción
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validateNotEmpty(nullString);
        });
        
        // Assert: Verificar que el mensaje de la excepción sea el esperado
        assertEquals("The string cannot be null or empty.", exception.getMessage());
    }
}

