package ec.edu.epn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculadoraTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializar la instancia de Calculator antes de cada prueba
        calculator = new Calculator();
    }

    // ========================================================================
    // SECCIÓN: PRUEBAS PARAMETRIZADAS
    // ========================================================================
    // Esta sección contiene todas las pruebas parametrizadas que utilizan
    // diferentes valores de entrada para validar el comportamiento de los
    // métodos de la clase Calculator.
    // ========================================================================

    /**
     * Prueba parametrizada para el método add().
     * Valida la suma de dos números enteros con múltiples casos de prueba.
     * 
     * Casos de prueba incluidos:
     * - Números positivos
     * - Números negativos
     * - Ceros
     * - Combinaciones de positivos y negativos
     */
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",           // Caso básico: números positivos
        "0, 0, 0",           // Caso límite: ambos operandos son cero
        "-5, 10, 5",         // Caso: número negativo + positivo
        "100, 200, 300",     // Caso: números grandes positivos
        "-10, -20, -30"      // Caso: ambos números negativos
    })
    void add_dosNumerosEnteros_devuelveSuma(int a, int b, int expected) {
        // Arrange: Los valores de entrada y resultado esperado vienen de los parámetros
        
        // Act: Ejecutar la operación de suma
        int result = calculator.add(a, b);
        
        // Assert: Verificar que el resultado sea el esperado
        assertEquals(expected, result);
    }

    /**
     * Prueba parametrizada para el método subtract().
     * Valida la resta de dos números enteros con múltiples casos de prueba.
     * 
     * Casos de prueba incluidos:
     * - Números positivos
     * - Números negativos
     * - Ceros
     * - Combinaciones de positivos y negativos
     */
    @ParameterizedTest
    @CsvSource({
        "10, 5, 5",          // Caso básico: números positivos
        "0, 0, 0",           // Caso límite: ambos operandos son cero
        "15, 10, 5",         // Caso: números positivos diferentes
        "-5, -10, 5",        // Caso: ambos números negativos
        "100, 50, 50"        // Caso: números grandes positivos
    })
    void subtract_dosNumerosEnteros_devuelveDiferencia(int a, int b, int expected) {
        // Arrange: Los valores de entrada y resultado esperado vienen de los parámetros
        
        // Act: Ejecutar la operación de resta
        int result = calculator.subtract(a, b);
        
        // Assert: Verificar que el resultado sea el esperado
        assertEquals(expected, result);
    }

    /**
     * Prueba parametrizada para el método multiply().
     * Valida la multiplicación de dos números enteros con múltiples casos de prueba.
     * 
     * Casos de prueba incluidos:
     * - Números positivos
     * - Números negativos
     * - Ceros (multiplicación por cero)
     * - Combinaciones de positivos y negativos
     */
    @ParameterizedTest
    @CsvSource({
        "2, 3, 6",           // Caso básico: números positivos
        "0, 5, 0",           // Caso límite: multiplicación por cero
        "-2, 3, -6",         // Caso: número negativo × positivo
        "10, 10, 100",       // Caso: números iguales positivos
        "-5, -5, 25"         // Caso: ambos números negativos
    })
    void multiply_dosNumerosEnteros_devuelveProducto(int a, int b, int expected) {
        // Arrange: Los valores de entrada y resultado esperado vienen de los parámetros
        
        // Act: Ejecutar la operación de multiplicación
        int result = calculator.multiply(a, b);
        
        // Assert: Verificar que el resultado sea el esperado
        assertEquals(expected, result);
    }

    /**
     * Prueba parametrizada para el método divide().
     * Valida la división de dos números enteros con múltiples casos de prueba.
     * Utiliza delta para comparaciones de punto flotante.
     * 
     * Casos de prueba incluidos:
     * - Números positivos
     * - Números negativos
     * - Divisiones que resultan en decimales
     * - Combinaciones de positivos y negativos
     * 
     * Nota: El delta de 0.0001 se utiliza para comparaciones de punto flotante
     * debido a posibles errores de precisión en operaciones con decimales.
     */
    @ParameterizedTest
    @CsvSource({
        "10, 2, 5.0",        // Caso básico: división exacta
        "15, 3, 5.0",        // Caso: división exacta diferente
        "9, 2, 4.5",         // Caso: división con resultado decimal
        "-10, 2, -5.0",      // Caso: número negativo ÷ positivo
        "100, 4, 25.0"       // Caso: números grandes
    })
    void divide_dosNumerosEnteros_devuelveCociente(int a, int b, double expected) {
        // Arrange: Los valores de entrada y resultado esperado vienen de los parámetros
        double delta = 0.0001; // Delta para comparaciones de punto flotante
        
        // Act: Ejecutar la operación de división
        double result = calculator.divide(a, b);
        
        // Assert: Verificar que el resultado sea el esperado (con tolerancia delta)
        assertEquals(expected, result, delta);
    }
    
    @Test
    void divide_byZero_throwsIllegalArgumentException() {
        // Arrange: Preparar los valores para la división por cero
        int a = 10;
        int b = 0;
        
        // Act & Assert: Verificar que se lance IllegalArgumentException al dividir por cero
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(a, b);
        });
    }

    /**
     * Prueba parametrizada para el método isEven() con números pares.
     * Valida que el método retorne true para números pares.
     * 
     * Casos de prueba incluidos:
     * - Cero (considerado par)
     * - Números pares positivos
     * - Números pares negativos
     * - Números pares grandes
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10, -2, -4, 100, 200})
    void isEven_numeroPar_devuelveTrue(int number) {
        // Arrange: El número par viene del parámetro
        
        // Act: Ejecutar la verificación de número par
        boolean result = calculator.isEven(number);
        
        // Assert: Verificar que el resultado sea true para números pares
        assertTrue(result);
    }

    /**
     * Prueba parametrizada para el método isEven() con números impares.
     * Valida que el método retorne false para números impares.
     * 
     * Casos de prueba incluidos:
     * - Números impares positivos
     * - Números impares negativos
     * - Números impares grandes
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3, -5, 99, 101})
    void isEven_numeroImpar_devuelveFalse(int number) {
        // Arrange: El número impar viene del parámetro
        
        // Act: Ejecutar la verificación de número par
        boolean result = calculator.isEven(number);
        
        // Assert: Verificar que el resultado sea false para números impares
        assertFalse(result);
    }
}
