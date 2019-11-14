package Modelo.TipoArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase Audio que contiene los métodos para comprobar si un fichero es de tipo
 * audio, para crear la carpeta correspondiente a los videos y las carpetas
 * correspondientes a cada tipo de archivo de audio. En resumen ordena los
 * archivos de tipo video
 *
 * @author Gonzalo
 */
public class Audio {

    /**
     * Método que comprueba si un archivo pasado por parámetro es un archivo de
     * tipo audio.
     *
     * @param fichero el cual queremos saber si es un archivo de audio.
     * @return true en caso de que sea mp3 o wav, false en caso contrario.
     */
    public static boolean comprobarAudio(File fichero) {
        if (fichero.getName().toLowerCase().endsWith("mp3")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("wav")) {
            return true;
        }
        return false;
    }

    /**
     * Método para crear las correspondientes carpetas para cada tipo de audio.
     *
     * @param fichero para comprobar que tipo es y crear su porpia carpeta
     * @param rutaNueva ruta dentro de la carpeta BackUpOrdenado\Audio\ para
     * crear las carpetas referentes a cada extensión.
     * @return retorna un objeto de tipo File con la ruta del directorio a
     * crear.
     */
    public static File crearCarpetas(File fichero, File rutaNueva) {
        if (fichero.getName().toLowerCase().endsWith("mp3")) {
            File textoTXT = new File(rutaNueva.getAbsolutePath() + File.separator + "AudioMP3");
            if (!textoTXT.exists()) {
                textoTXT.mkdir();
            }
            return textoTXT;
        } else if (fichero.getName().toLowerCase().endsWith("wav")) {
            File textoPDF = new File(rutaNueva.getAbsolutePath() + File.separator + "\\AudioWAV");
            if (!textoPDF.exists()) {
                textoPDF.mkdir();
            }
            return textoPDF;
        }
        return null;
    }

    /**
     * Método que copia el fichero en su carpeta correspondiente dependiendo del
     * tipo de archivo de audio que sea.
     *
     * @param fichero que es el que se va a copiar.
     * @param rutaNueva correspondiente a la carpeta del tipo de audio que se
     * obtiene del método crearCarpetas().
     * @throws IOException
     */
    public static void ordenarAudio(File fichero, File rutaNueva) throws IOException {
        if (fichero.getName().toLowerCase().endsWith("mp3".toLowerCase())) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("wav")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Método para crear la carpeta BackUpOrdenado\Audio con todos los
     * correspondientes ficheros de audio que haya en la copia de seguridad.
     *
     * @param nuevaCarpeta sería la carpeta BackUpOrdenado.
     * @param fichero que se quiere copiar en el BackUpOrdenado.
     * @throws IOException
     */
    public static void backupAudios(File nuevaCarpeta, File fichero) throws IOException {
        File audios = new File(nuevaCarpeta.getAbsolutePath() + File.separator + "Audios");
        if (!audios.exists()) {
            audios.mkdir();
        }
        File rutaCarpetas = Audio.crearCarpetas(fichero, audios);
        Audio.ordenarAudio(fichero, rutaCarpetas);
    }

}
