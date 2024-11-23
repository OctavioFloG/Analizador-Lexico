public class AnalizadorLexico {
    // Definimos los estados del autómata
    private static final int INICIO = 0;
    private static final int ESTADO_A = 1;
    private static final int ESTADO_B = 2;
    private static final int ACEPTACION = 3;

    // Filas = estados, Columnas = elementos
    private int[][] matrizTransicion = {
        {},     // Estado INICIO
        {},     // Estado ESTADO_A
        {},   // Estado ESTADO_B
        {}  // Estado ACEPTACION (estado de aceptación)
    };

    // Estado actual del autómata
    private int estadoActual;

    // Constructor
    public AnalizadorLexico() {
        estadoActual = INICIO;
    }

    // Método para procesar la entrada
    public boolean procesarCadena(String cadena) {
        estadoActual = INICIO; // Reiniciar el autómata

        for (char simbolo : cadena.toCharArray()) {
            int columna = obtenerColumna(simbolo);
            if (columna == -1) {
                // Símbolo no válido, la cadena es rechazada
                return false;
            }
            estadoActual = matrizTransicion[estadoActual][columna];

            if (estadoActual == -1) {
                // Estado de error, la cadena es rechazada
                return false;
            }
        }
        // La cadena es aceptada si el estado final es el de aceptación
        return estadoActual == ACEPTACION;
    }

    // Método para obtener la columna en la matriz según el símbolo de entrada
    private int obtenerColumna(char simbolo) {
        switch (simbolo) {
            case 'a': return 0;
            case 'b': return 1;
            default: return -1; // Símbolo no reconocido
        }
    }

    public static void main(String[] args) {
        Automata automata = new Automata();

        String cadena = "ab"; // Ejemplo de cadena de entrada
        if (automata.procesarCadena(cadena)) {
            System.out.println("La cadena '" + cadena + "' es aceptada.");
        } else {
            System.out.println("La cadena '" + cadena + "' es rechazada.");
        }
    }
}
