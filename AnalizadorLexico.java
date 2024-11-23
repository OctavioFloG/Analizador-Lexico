import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AnalizadorLexico{
    private static final int INICIO = 0;
    private static final int ESTADO_A = 1;
    private static final int ESTADO_B = 2;
    private static final int ACEPTACION = 3;

    // Filas = estados, Columnas = elementos
    private int[][] matrizTransicion = {
        {},//q0
        {},//q1010.1
        {},//1010.2
        {},//q1010.3
        {},//q1010.4
        {},//q1010.5
        {},//q1010.6
        {},//q1010.7
        {},//q1020.1
        {},//q1020.2
        {},//q1020.3
        {},//q1020.4
        {},//q1020.5 
        {},//q2010.1 +
        {},//q2020.1 -
        {},//q2030.1 *
        {},//q2040.1 /
        {},//q2050.1 %        
        {},//q4010.1 {
        {},//q4020.1 }
        {},//q5010.1 (
        {},//q5020.1 )
        {},//q6000.1 Identificadores
        {},//q7000.1 Constantes
        {},//q7000.2
        {} //q8000.1 Flotantes
    };

    private int estadoActual;

    public AnalizadorLexico() {
        estadoActual = INICIO;
    }

    // Método para procesar la entrada
    public boolean procesarCadena(String cadena) {
        estadoActual = INICIO; // Regresar al estado inicial

        for (char simbolo : cadena.toCharArray()) {
            int columna = obtenerColumna(simbolo);
            if (columna == -1) {
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

    private int obtenerColumna(char simbolo) {
        switch (simbolo) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'q': return 16;
            case 'r': return 17;
            case 's': return 18;
            case 't': return 19;
            case 'u': return 20;
            case 'v': return 21;
            case 'w': return 22;
            case 'x': return 23;
            case 'y': return 24;
            case 'z': return 25;
            case '0': return 26;
            case '1': return 27;
            case '2': return 28;
            case '3': return 29;
            case '4': return 30;
            case '5': return 31;
            case '6': return 32;
            case '7': return 33;
            case '8': return 34;
            case '9': return 35;
            case '+': return 36;
            case '-': return 37;
            case '/': return 38;
            case '%': return 39;
            case '{': return 40;
            case '}': return 41;
            case '(': return 42;
            case ')': return 43;

            default: return -1; // Símbolo no reconocido
        }
    }

    // Método para leer y procesar cadenas desde un archivo
    public void procesarArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (procesarCadena(linea)) {
                    System.out.println("La cadena '" + linea + "' es aceptada.");
                } else {
                    System.out.println("La cadena '" + linea + "' es rechazada.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AnalizadorLexico automata = new AnalizadorLexico();
        String nombreArchivo = "codigo.txt";
        automata.procesarArchivo(nombreArchivo);
    }
}
