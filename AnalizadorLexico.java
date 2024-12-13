import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizadorLexico {
    protected static Character[] codigo;
    protected static String[][] matrizTransicion;
    protected static char[] caracteres = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/', '%', '{', '}', '(', ')', '[', ']', '.', ' ', '_', '$', '^', '<', '>', '&', '|', '!', ';', ',', '\'', '"', '=', '\n', '\r' };
    protected static String[] estados = {"q0", "q1010.1", "q1010.2", "q1010.3", "q1010.4", "q1010.5", "q1010.6", "q1010.7", "q1020.1", "q1020.2", "q1020.3", "q1020.4", "q1020.5", "q1030.1", "q1030.2", "q1040.1", "q1040.2", "q1040.3", "q1040.4", "q1040.5", "q1040.6", "q1050.1", "q1050.2", "q1050.3", "q1050.4", "q1060.1", "q1060.2", "q1060.3", "q1060.4", "q1060.5", "q1060.6", "q1060.7", "q1070.1", "q1070.2", "q1070.3", "q1070.4", "q1070.5", "q1080.1", "q1080.2", "q1080.3", "q1090.1", "q1090.2", "q1100.1", "q1100.2", "q1100.3", "q1100.4", "q1100.5", "q1100.6", "q1110.1", "q1110.2", "q1110.3", "q1120.1", "q1120.2", "q1120.3", "q1120.4", "q1130.1", "q1130.2", "q1130.3", "q1140.1", "q1140.2", "q1140.3", "q1140.4", "q1140.5", "q1140.6", "q1140.7", "q1150.1", "q1150.2", "q1150.3", "q1150.4", "q1150.5", "q1150.6", "q1160.1", "q1160.2", "q1160.3", "q1160.4", "q1160.5", "q1160.6", "q1170.1", "q1170.2", "q1170.3", "q1170.4", "q1170.5", "q1170.6", "q1180.1", "q1180.2", "q1180.3", "q1180.4", "q1190.1", "q1190.2", "q1190.3", "q1190.4", "q1200.1", "q1200.2", "q1200.3", "q1200.4", "q1200.5", "q2010.1", "q2020.1", "q2030.1", "q2040.1", "q2050.1", "q2060.1", "q2060.2", "q2070.1", "q2070.2", "q2080.1", "q2080.2", "q2090.1", "q2090.2", "q2100.1", "q2100.2", "q2110.1", "q2110.2", "q2120.1", "q2120.2", "q2130.1", "q2130.2", "q2130.3", "q2140.1", "q2140.2", "q2140.3", "q2150.1", "q2150.2", "q2150.3", "q2150.4", "q2160.1", "q2170.1", "q2180.1", "q2180.2", "q2190.1", "q2190.2", "q2200.1", "q2200.2", "q2210.1", "q2210.2", "q2220.1", "q2230.1", "q2230.2", "q2240.1", "q2240.2", "q2250.1", "q2260.1", "q2260.2", "q2270.1", "q2270.2", "q2280.1", "q3010.1", "q3020.1", "q3030.1", "q4010.1", "q4020.1", "q5010.1", "q5020.1", "q5030.1", "q5040.1", "q6000.1", "q7000.1", "q7000.2", "q8000.1"};
      
    protected static String[] estadosFinales = { "q1010.0", "q1020.0", "q1030.0", "q1040.0", "q1050.0", "q1060.0", "q1070.0", "q1080.0", "q1090.0", "q1100.0", "q1110.0", "q1120.0", "q1130.0", "q1140.0", "q1150.0", "q1160.0", "q1170.0", "q1180.0", "q1190.0", "q1200.0", "q2010.0", "q2020.0", "q2030.0", "q2040.0", "q2050.0", "q2060.0", "q2070.0", "q2080.0", "q2090.0", "q2100.0", "q2110.0", "q2120.0", "q2130.0", "q2140.0", "q2150.0", "q2160.0", "q2170.0", "q2180.0", "q2190.0", "q2200.0", "q2210.0", "q2220.0", "q2230.0", "q2240.0", "q2250.0", "q2260.0", "q2270.0", "q2280.0", "q3010.0", "q3020.0", "q3030.0", "q4010.0", "q4020.0", "q5010.0", "q5020.0", "q5030.0", "q5040.0", "q6000.0", "q7000.0", "q8000.0" };
    protected static int[] tokens = {1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090, 1100, 1110, 1120, 1130, 1140, 1150, 1160, 1170, 1180, 1190, 1200, 2010, 2020, 2030, 2040, 2050, 2060, 2070, 2080, 2090, 2100, 2110, 2120, 2130, 2140, 2150, 2160, 2170, 2180, 2190, 2200, 2210, 2220, 2230, 2240, 2250, 2260, 2270, 2280, 3010, 3020, 3030, 4010, 4020, 5010, 5020, 5030, 5040, 6000, 7000, 8000};

    private String estadoActual;
    private int tokenIdent=6001;
    private int tokenConst=7001;
    private int tokenFloat=8001;

    // Constructor
    public AnalizadorLexico() {
        estadoActual = "q0";
    }

    public static void ExcelToArray() {
        String filePath = "AnalizadorLexico.txt"; // Ruta del archivo .txt
        ArrayList<String[]> dataArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\t");
                dataArray.add(columns);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        matrizTransicion = dataArray.toArray(new String[0][]);
    }

    public static void main(String[] args) {
        AnalizadorLexico automata = new AnalizadorLexico();
        ExcelToArray();
        String nombreArchivo = "codigo.txt";
        automata.procesarArchivo(nombreArchivo);
    }

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

    public void procesarArchivo(String nombreArchivo) {
        try {
            FileReader fr = new FileReader(nombreArchivo);
            int caracter;
            while ((caracter = fr.read()) != -1) {
                // Ignorar saltos de línea y retorno de carro
                if (caracter == '\n' || caracter == '\r') {
                    continue; // Pasar al siguiente caracter
                }
    
                int fila = obtenerFila(estadoActual);
                int columna = obtenerColumna((char) caracter);
    
                // Verificar que las filas y columnas sean válidas
                if (fila == -1 || columna == -1) {
                    System.out.println("Error procesando el archivo: Estado o caracter inválido.");
                    fr.close();
                    return;
                }
    
                String nuevoEstado = matrizTransicion[fila][columna];
                if (nuevoEstado.equals("q9999")){ //CADENA NO RECONOCIDA
                    System.out.println("\nError, cadena no reconocida");
                    fr.close();
                    return;
                }

                estadoActual = nuevoEstado;    
                // Verificar si el nuevo estado es final
                if (esEstadoFinal(estadoActual)) {
                    int token = buscarToken(estadoActual);
                    if (token == 6000) {
                        token = tokenIdent++;
                    } else if (token == 7000) {
                        token = tokenConst++;
                    } else if (token == 8000) {
                        token = tokenFloat++;   
                    }
                    System.out.print(token + " ");
                    estadoActual = "q0";
                }
                //PARA DEBUGEAR
                // System.out.println("caracter leido: "+(char) caracter+", siguiente estado: " + estadoActual);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int buscarToken(String estado) {
        for (int i = 0; i < estadosFinales.length; i++) {
            if (estadosFinales[i].equals(estado)) {
                return tokens[i];
            }
        }
        return -1;
    }

    private boolean esEstadoFinal(String estado) {
        for (String estadoFinal : estadosFinales) {
            if (estadoFinal.equals(estado)) {
                return true;
            }
        }
        return false;
    }
    
}
