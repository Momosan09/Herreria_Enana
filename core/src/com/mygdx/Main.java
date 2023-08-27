import java.io.File;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese nombre para el archivo:");
        String nombreArchivo = scan.nextLine();
        scan.close();

        String objetivo = "";

        try {
            String rutaActual = Paths.get(".").toAbsolutePath().normalize().toString();
            File file = new File(rutaActual + "/hud/HUD.java");
            Scanner s = new Scanner(file);
            // Construye la ruta para el directorio y el archivo
            File directorio = new File(rutaActual + "/../../../../assets/locale");
            directorio.mkdirs(); // Crea los directorios necesarios
            File locale = new File(directorio, nombreArchivo + ".properties");

            FileWriter writer = new FileWriter(locale);
            while (s.hasNextLine()) {

                String linea = s.nextLine();
                if (linea.contains(".createBundle")) {
                    int indexInicio = linea.indexOf("e");
                    int indexFin = linea.indexOf("=");
                    objetivo = linea.substring(indexInicio + 2, indexFin - 1);
                    System.out.println(objetivo);
                }

                // Buscar la palabra en la línea
                if (linea.contains(objetivo + ".get(")) {
                    int indiceInicio = linea.indexOf("\"") + 1; // Encontrar el índice de inicio de la cadena
                    int indiceFin = linea.indexOf("\"", indiceInicio); // Encontrar el índice de fin de la cadena
                    System.out.println(indiceInicio);
                    String pedazoRecortado = linea.substring(indiceInicio, indiceFin); // Extraer la cadena entre las
                                                                                       // comillas
                    System.out.println(pedazoRecortado);
                    writer.append(pedazoRecortado + "=\n");
                }
            }

            s.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
