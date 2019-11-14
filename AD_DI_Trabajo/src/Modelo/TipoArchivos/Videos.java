package Modelo.TipoArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase Videos que contiene los métodos para ordenar los videos de la copia de
 * seguridad. Estos son: comprobar si el fichero es o no de dipo video, crear
 * las carpetas correspndientes a las extensiones y crear la carpeta Videos
 *
 * @author Gonzalo
 */
public class Videos {

    /**
     * Método que comprueba si un fichero pasado por parámetro es o no de tipo
     * Video.
     *
     * @param fichero que se quiere saber si es o no de tipo video.
     * @return true si el fichero es de tipo video, false en caso contrario.
     */
    public static boolean comprobarVideo(File fichero) {
        if (fichero.getName().toLowerCase().endsWith("avi")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("mp4")) {
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
     * @param rutaNueva ruta dentro de la carpeta BackUpOrdenado\Videos\ para
     * crear las carpetas referentes a cada extensión.
     */
    public static File crearCarpetas(File fichero, File rutaNueva) {
        if (fichero.getName().toLowerCase().endsWith("avi")) {
            File videosAVI = new File(rutaNueva.getAbsolutePath() + File.separator + "VideosAVI");
            if (!videosAVI.exists()) {
                videosAVI.mkdir();
            }
            return videosAVI;
        } else if (fichero.getName().toLowerCase().endsWith("mp4")) {
            File videosMP4 = new File(rutaNueva.getAbsolutePath() + File.separator + "VideosMP4");
            if (!videosMP4.exists()) {
                videosMP4.mkdir();
            }
            return videosMP4;
        }
        return null;
    }

    /**
     * Método que copia el fichero en su carpeta correspondiente dependiendo del
     * tipo de archivo de video que sea.
     *
     * @param fichero que es el que se va a copiar.
     * @param rutaNueva correspondiente a la carpeta del tipo de video que se
     * obtiene del método crearCarpetas().
     * @throws IOException
     */
    public static void ordenarVideos(File fichero, File rutaNueva) throws IOException {
        if (fichero.getName().toLowerCase().endsWith("avi".toLowerCase())) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("mp4")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Método para crear la carpeta BackUpOrdenado\Videos con todos los
     * correspondientes ficheros videos que haya en la copia de seguridad.
     *
     * @param nuevaCarpeta sería la carpeta BackUpOrdenado.
     * @param fichero que se quiere copiar en el BackUpOrdenado.
     * @throws IOException
     */
    public static void backupVideos(File nuevaCarpeta, File fichero) throws IOException {
        File videos = new File(nuevaCarpeta.getAbsolutePath() + File.separator + "Videos");
        if (!videos.exists()) {
            videos.mkdir();
        }
        File rutaCarpetas = Videos.crearCarpetas(fichero, videos);
        Videos.ordenarVideos(fichero, rutaCarpetas);
    }

}
