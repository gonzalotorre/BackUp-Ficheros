package Modelo.Filtros;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * Clase FiltroExtensiones, contiene un atributo ArrayList de String, con dos
 * constructores, a uno se le pasa un string extensión para guardarlo en la
 * lista y el otro se le pasa una lista. Se sobreescribe el método accept para
 * comprobar si un determinado fichero se corresponde con alguna extensión de la
 * lista.
 *
 * @author Gonzalo
 */
public class FiltroExtensiones implements FileFilter {

    //Atributo lista de String con las extensiones
    private ArrayList<String> extensiones;

    //Constructores
    public FiltroExtensiones(String extension) {
        extensiones = new ArrayList<>();
        if (!"".equals(extension)) {
            extensiones.add(extension);
        }
    }

    public FiltroExtensiones(ArrayList<String> extensiones) {
        this.extensiones = extensiones;
    }

    /**
     * Método accept sobreescrito, en el se saca la extensión del fichero, para
     * ello se usan los métodos indexOf y substring, una vez hecho se comprueba
     * si la extensión obtenida está en la lista.
     *
     * @param fichero del que se quiere saber su extensión y si dicha extensión
     * está contenida en la lita
     * @return true si la extensión del fichero se corresponde con alguna de la
     * lista, false en caso contrario
     */
    @Override
    public boolean accept(File fichero) {
        int indice = fichero.getName().indexOf(".");
        String extension = fichero.getName().toLowerCase().substring(indice + 1);
        if (extensiones.contains(extension.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
