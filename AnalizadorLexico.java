import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizadorLexico {
    private static final String q0 = "q0";

    protected static Character[] codigo;

    protected static String[][] matrizTransicion;

    protected static char[] caracteres = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '%', '{', '}', '(', ')', '[', ']', '.', ' ', '_', '$', '^', '<', '>', '&', '|', '!', ';', ',', '\'', '"', '=' };

    protected static String[] estados = { "q0", "q1010.1", "q1010.2", "q1010.3", "q1010.4", "q1010.5", "q1010.6", "q1010.7", "q1020.1", "q1020.2","q1020.3" };

    protected static String[] estadosFinales = { "q1010.7", "q1020.5", "q1030.2", "q1040.6", "q1050.4", "q1060.5", 
    "q1070.3", "q1080.2", "q1090.6", "q1100.3", "q1110.4", "q1120.3", "q1130.7", "q1140.7", "q1150.6", "q1160.6", 
    "q1170.6", "q1180.4", "q1190.4", "q1200.5", "q2010.1", "q2020.1", "q2030.1", "q2040.1", "q2050.1", 
    "q2060.2", "q2070.2", "q2080.2", "q2090.2", "q2100.2", "q2110.2", "q2120.2", "q2130.3", "q2140.3", 
    "q2150.4", "q2160.1", "q2170.1", "q2180.2", "q2190.2", "q2200.2", "q2210.2", "q2220.1", "q2230.2", 
    "q2240.2", "q2250.2", "q2260.2", "q2270.2", "q3010.1", "q3020.1", "q3030.1", "q4010.1", "q4020.1", 
    "q5010.1", "q5020.1", "q5030.1", "q5040.1", "q6000.1", "q7000.1", "q8000.1" };

    private String estadoActual;

    // Constructor
    public AnalizadorLexico() {
        estadoActual = q0;
    }

    public static void ExcelToArray() {
        String filePath = "AnalizadorLexico.txt"; // Ruta del archivo .txt
        ArrayList<String[]> dataArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Dividir la línea en columnas usando el delimitador (tabulación en este caso)
                String[] columns = line.split("\t");
                dataArray.add(columns);
            }

            // Mostrar los datos para verificar
            // for (String[] row : dataArray) {
            // for (String col : row) {
            // System.out.print(col + " ");
            // }
            // System.out.println();
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertir el ArrayList a un array si es necesario
        matrizTransicion = dataArray.toArray(new String[0][]);
    }

    public static void main(String[] args) {
        AnalizadorLexico automata = new AnalizadorLexico();
        ExcelToArray();
        String nombreArchivo = "codigo.txt";
        automata.procesarArchivo(nombreArchivo);
        // for (int i = 0; i < estadosFinales.length; i++) {
        //     while (matrizTransicion[obtenerFila("q0")][obtenerColumna('a')] != estadosFinales[i]) {

        //     }
        // }
        
        // try {
        // leerArchivoCaracterPorCaracter(nombreArchivo);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    // public static void leerArchivoCaracterPorCaracter(String filePath) throws
    // IOException {
    // try (FileReader fr = new FileReader(filePath)) {
    // int caracter;

    // // Leer carácter por carácter
    // while ((caracter = fr.read()) != -1) {
    // char c = (char) caracter;
    // if(){

    // }
    // System.out.print(c); // Imprime cada carácter
    // }
    // }
    // }

    private static int obtenerColumna(char simbolo) {
        for (int i = 0; i < caracteres.length; i++) {
            if (caracteres[i] == simbolo) {
                return i;
            }
        }
        System.out.println("Error, no se encontró el caracter: "+simbolo);
        return -1;
    }

    private static int obtenerFila(String estado) {
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado)) {
                return i;
            }
        }
        System.out.println("Error, no se encontró el estado: "+estado);
        return -1;
    }

    // Método para leer y procesar cadenas desde un archivo
    public void procesarArchivo(String nombreArchivo) {
        try {
            // Leer el archivo carácter por carácter
            FileReader fr = new FileReader(nombreArchivo);
            int caracter;

            while ((caracter = fr.read()) != -1) {
                System.out.println(estadoActual);
                int fila = obtenerFila(estadoActual);
                int columna = obtenerColumna((char) caracter);
                String nuevoEstado = matrizTransicion[fila][columna];
                if (nuevoEstado == null) {
                    System.out.println("Error: Transición no válida para el carácter '" + (char) caracter + "'");
                    return;
                }
                estadoActual = nuevoEstado;
                System.out.println("Carácter leído: " + (char) caracter + ", Nuevo estado: " + estadoActual);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
