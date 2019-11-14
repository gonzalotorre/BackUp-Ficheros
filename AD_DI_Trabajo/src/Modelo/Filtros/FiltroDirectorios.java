package Modelo.Filtros;

import java.io.File;
import java.io.FileFilter;

/**
 * Esta clase implementa la interfaz FileFilter, en ella sobreescribimos el
 * método accept para comprobar si la ruta introducida corresponde a un
 * directorio o no.
 *
 * @author Gonzalo
 */
public class FiltroDirectorios implements FileFilter {

    /**
     * Método sobreescrito accept para comprobar si un Objeto de tipo File es o
     * n o un direcctorio
     *
     * @param fichero del cula se va a comprobar si es o no un direcctorio.
     * @return true si es directorio, false en caso contrario.
     */
    @Override
    public boolean accept(File fichero) {
        return fichero.isDirectory();
    }

}
