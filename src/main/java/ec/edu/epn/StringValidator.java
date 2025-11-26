package ec.edu.epn;

/**
 * Clase utilitaria para validar cadenas de texto.
 * Proporciona métodos para verificar que las cadenas cumplan con ciertos criterios.
 */
public class StringValidator {

    /**
     * Valida que una cadena no sea null, vacía o contenga solo espacios en blanco.
     * 
     * Este método verifica tres condiciones:
     * - Si la cadena es null
     * - Si la cadena está vacía (length == 0)
     * - Si la cadena contiene solo espacios en blanco (después de trim())
     * 
     * Si alguna de estas condiciones se cumple, se lanza una IllegalArgumentException
     * con un mensaje descriptivo.
     * 
     * @param input La cadena de texto a validar
     * @throws IllegalArgumentException Si la cadena es null, vacía o contiene solo espacios
     * 
     * @example
     * <pre>
     * StringValidator validator = new StringValidator();
     * validator.validateNotEmpty("texto válido");  // No lanza excepción
     * validator.validateNotEmpty(null);            // Lanza IllegalArgumentException
     * validator.validateNotEmpty("");              // Lanza IllegalArgumentException
     * validator.validateNotEmpty("   ");          // Lanza IllegalArgumentException
     * </pre>
     */
    public void validateNotEmpty(String input) {
        // Verificar si la cadena es null
        if (input == null) {
            throw new IllegalArgumentException("The string cannot be null or empty.");
        }
        
        // Verificar si la cadena está vacía o contiene solo espacios en blanco
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("The string cannot be null or empty.");
        }
    }
}
