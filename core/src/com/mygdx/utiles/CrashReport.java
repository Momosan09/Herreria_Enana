package com.mygdx.utiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrashReport {
    public static void report(Throwable e) {
        try {
            // Aquí, podrías escribir la excepción en un archivo o enviarla a un servicio de informes.
            // Por ejemplo, escribir a un archivo:
            File file = new File("informe-de-fallos.txt");
            FileWriter writer = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            printWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
