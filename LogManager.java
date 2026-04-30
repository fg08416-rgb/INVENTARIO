package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogManager {

    public static void registrarError(String nombreArchivo, String mensajeError) {
        try (FileWriter fw = new FileWriter(nombreArchivo + ".txt", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("[" + LocalDateTime.now() + "] " + mensajeError);

        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo de log: " + e.getMessage());
        }
    }
}
