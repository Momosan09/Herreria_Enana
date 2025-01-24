package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Se usa para conseguir todos los archivos de traducción en la carpeta locale.
 */
public abstract class LeerLocale {

    public static void conseguirListaIdiomas() {
        // Ruta explícita a la carpeta 'locale'
        String rutaCarpeta = "locale/";

        // Obtén un FileHandle para la carpeta
        FileHandle carpetaLocale = Gdx.files.internal(rutaCarpeta);

        // Verifica si la carpeta existe
        if (carpetaLocale.exists()) {
            // Obtén todos los archivos dentro de la carpeta
            FileHandle[] archivos = carpetaLocale.list();

            // Verifica si hay archivos en la carpeta
            if (archivos.length > 0) {
                System.out.println("Archivos en la carpeta 'locale':");
                for (FileHandle archivo : archivos) {
                    // Imprime el nombre de cada archivo
                    System.out.println(archivo.name());
                }
            } else {
                System.out.println("La carpeta 'locale' está vacía.");
            }
        } else {
            System.out.println("La carpeta 'locale' no existe o no es un directorio.");
        }
    }
}
