package Controlador;

import Modelo.OperacionesFicheros;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File origen = new File("C:\\Users\\gonza\\Downloads");
        File destino = new File("C:\\Users\\gonza\\Documents\\BackUp");
        String nuevaRuta = "C:\\Users\\gonza\\Documents\\BackUp";
        /*ArrayList<String> extensiones = new ArrayList<>();

        extensiones.add("jpg");
        extensiones.add("png");*/
        //System.out.println(origen.getAbsolutePath() + " --- " + File.separator);

        //OperacionesFicheros.borrarCarpetasVacías(destino);
        //Algo.eliminaCarpetasVacias(destino);
        try {
            //OperacionesFicheros.copiarConFiltro(origen, destino, extensiones);
            //OperacionesFicheros.borrarCarpetasVacías(destino);
            //OperacionesFicheros.eliminarCopia(destino);
            //OperacionesFicheros.oredenarBackUp(destino, nuevaRuta);
            ArrayList<File> ficheros = OperacionesFicheros.buscarDuplicados(destino);
            for (File fichero : ficheros) {
                System.out.println(fichero);
            }
            OperacionesFicheros.borrarDuplicados(destino);
        } catch (IOException ex) {
            Logger.getLogger(Ejecutable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
