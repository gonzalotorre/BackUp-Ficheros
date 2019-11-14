package Modelo.TipoArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Clase Imagenes que contiene los métodos para ordenar las imágenes de la copia
 * de seguridad. Estos son: comprobar si el fichero es o no de dipo imagen,
 * crear las carpetas correspndientes a las extensiones y crear la carpeta
 * Imagenes
 *
 * @author Gonzalo
 */
public class Imagenes {

    /**
     * Método que comprueba si un fichero pasado por parámetro es o no de tipo
     * Imagen.
     *
     * @param fichero que se quiere saber si es o no de tipo imagen.
     * @return true si el fichero es de tipo imagen, false en caso contrario.
     */
    public static boolean comprobarImagen(File fichero) {
        if (fichero.getName().toLowerCase().endsWith("png")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("jpg")) {
            return true;
        } else if (fichero.getName().toLowerCase().endsWith("gif")) {
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
     * @param rutaNueva ruta dentro de la carpeta BackUpOrdenado\Imagenes\ para
     * crear las carpetas referentes a cada extensión.
     */
    public static File crearCarpetas(File fichero, File rutaNueva) {
        if (fichero.getName().toLowerCase().endsWith("png")) {
            File imagenesPNG = new File(rutaNueva.getAbsolutePath() + File.separator + "ImagenesPNG");
            if (!imagenesPNG.exists()) {
                imagenesPNG.mkdir();
            }
            return imagenesPNG;
        } else if (fichero.getName().toLowerCase().endsWith("jpg")) {
            File imagenesJPG = new File(rutaNueva.getAbsolutePath() + File.separator + "ImagenesJPG");
            if (!imagenesJPG.exists()) {
                imagenesJPG.mkdir();
            }
            return imagenesJPG;
        } else if (fichero.getName().toLowerCase().endsWith("gif")) {
            File imagenesGIF = new File(rutaNueva.getAbsolutePath() + File.separator + "ImagenesGIF");
            if (!imagenesGIF.exists()) {
                imagenesGIF.mkdir();
            }
            return imagenesGIF;
        }
        return null;
    }

    /**
     * Método que copia el fichero en su carpeta correspondiente dependiendo del
     * tipo de archivo de imagen que sea.
     *
     * @param fichero que es el que se va a copiar.
     * @param rutaNueva correspondiente a la carpeta del tipo de imagen que se
     * obtiene del método crearCarpetas().
     * @throws IOException
     */
    public static void ordenarImagenes(File fichero, File rutaNueva) throws IOException {
        if (fichero.getName().toLowerCase().endsWith("png".toLowerCase())) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("jpg")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else if (fichero.getName().toLowerCase().endsWith("gif")) {
            File rutaCompleta = new File(rutaNueva.getAbsolutePath() + File.separator + fichero.getName());
            Files.copy(fichero.toPath(), rutaCompleta.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Método para crear la carpeta BackUpOrdenado\Imagenes con todos los
     * correspondientes ficheros imagenes que haya en la copia de seguridad.
     *
     * @param nuevaCarpeta sería la carpeta BackUpOrdenado.
     * @param fichero que se quiere copiar en el BackUpOrdenado.
     * @throws IOException
     */
    public static void backUpImagenes(File nuevaCarpeta, File fichero) throws IOException {
        //Creo un fichero para crear el directorio de las imágenes.
        File imagenes = new File(nuevaCarpeta.getAbsolutePath() + File.separator + "Imagenes");
        //Si no existe se crea el directorio.
        if (!imagenes.exists()) {
            imagenes.mkdir();
        }
        //Creo otro fichero el cual se usa para crear las carpetas correspondientes a cada extensión.
        File rutaCarpetas = Imagenes.crearCarpetas(fichero, imagenes);
        //Ahora llamo al método que copia el fichero, en este caso una imagen en su carpeta correspondiente.
        Imagenes.ordenarImagenes(fichero, rutaCarpetas);
    }

}
