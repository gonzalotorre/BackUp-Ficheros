package Vista.Logica;

import Vista.DTO.Fichero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class LogicaFichero {

    private static List<Fichero> listaFicheros = new ArrayList<>();

    public static void add(Fichero fichero) {
        listaFicheros.add(fichero);
    }

    public static List<Fichero> getListaFicheros() {
        return listaFicheros;
    }
}
