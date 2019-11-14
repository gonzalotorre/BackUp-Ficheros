package Modelo.TipoArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase Texto que contiene los métodos para ordenar los archivos de texto de la
 * copia de seguridad. Estos son: comprobar si el fichero es o no de dipo texto,
 * crear las carpetas correspndientes a las extensiones y crear la carpeta
 * Texto
 *
 * @author Gonzalo
 */
public class Texto {

    /**
     * Método que comprueba si un fichero pasado por parámetro es o no de tipo
     * Texto.
     *
     * @param fichero que se quiere saber si es o no de tipo texto.
     * @return true si el fichero es de tipo texto, false en caso contrario.
     */
    public static boolean comprobarTexto(File fichero) {
        if (fichero.getName().toLowerCase().endsWith("txt")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("pdf")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("doc")) {
            return true;
        }
        return false;
    }

    /**
     * Método que sirve para crear las carpetas correspondietes a cada
     * extensión.
     *
     * @param fichero que sirve para saber de que tipo es y crear su
     * correspondiente carpeta.
     * @param rutaNueva ruta dentro de la carpeta BackUpOrdenado\Texto\ para
     * crear las carpetas referentes a cada extensión.
     */
    public static File crearCarpetas(File fichero, File rutaNueva) {
        if (fichero.getName().toLowerCase().endsWith("txt")) {
            File textoTXT = new File(rutaNueva.getAbsolutePath() + File.separator + "TextoTXT");
            if (!textoTXT.exists()) {
                textoTXT.mkdir();
            }
            return textoTXT;
        } else if (fichero.getName().toLowerCase().endsWith("pdf")) {
            File textoPDF = new File(rutaNueva.getAbsolutePath() + File.separator + "TextoPDF");
            if (!textoPDF.exists()) {
                textoPDF.mkdir();
            }
            return textoPDF;
        } else if (fichero.getName().toLowerCase().endsWith("doc")) {
            File textoDOC = new File(rutaNueva.getAbsolutePath() + File.separator + "TextoDOC");
            if (!textoDOC.exists()) {
                textoDOC.mkdir();
            }
            return textoDOC;
        }
        return null;
    }

    /**
     * Método que copia el fichero en su carpeta correspondiente dependiendo del
     * tipo de archivo de texto que sea.
     *
     * @param fichero que es el que se va a copiar.
     * @param rutaNueva correspondiente a la carpeta del tipo de texto que se
     * obtiene del método crearCarpetas().
     * @throws IOException
     */
    public static void ordenarTexto(File fichero, File rutaNueva) throws IOException {
        if (fichero.getName().toLowerCase().endsWith("txt".toLowerCase())) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("pdf")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("doc")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Método para crear la carpeta BackUpOrdenado\Texto con todos los
     * correspondientes ficheros texto que haya en la copia de seguridad.
     *
     * @param nuevaCarpeta sería la carpeta BackUpOrdenado.
     * @param fichero que se quiere copiar en el BackUpOrdenado.
     * @throws IOException
     */
    public static void backupTextos(File nuevaCarpeta, File fichero) throws IOException {
        File textos = new File(nuevaCarpeta.getAbsolutePath() + File.separator + "Textos");
        if (!textos.exists()) {
            textos.mkdir();
        }
        File rutaCarpetas = Texto.crearCarpetas(fichero, textos);
        Texto.ordenarTexto(fichero, rutaCarpetas);
    }

}
