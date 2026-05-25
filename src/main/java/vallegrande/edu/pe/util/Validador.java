package vallegrande.edu.pe.util;

public class Validador {

    /** Solo letras y espacios (como soloLetras() en JS) */
    public static boolean soloLetras(String texto) {
        return texto != null && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    /** Formato correo electrónico */
    public static boolean esCorreoValido(String correo) {
        return correo != null &&
                correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");
    }

    /** Teléfono peruano: 9 dígitos, empieza en 9 */
    public static boolean esTelefonoPE(String telefono) {
        return telefono != null && telefono.matches("^9\\d{8}$");
    }

    /** Campo requerido (no vacío) */
    public static boolean requerido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }
}