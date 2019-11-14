package Modelo;

import Modelo.TipoArchivos.Videos;
import Modelo.TipoArchivos.Imagenes;
import Modelo.TipoArchivos.Audio;
import Modelo.TipoArchivos.Texto;
import Modelo.Filtros.FiltroDirectorios;
import Modelo.Filtros.FiltroExtensiones;
import Vista.GUI.JFrameVentanaPrincipal;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Clase OperacionesFicheros, en ella se gestiona el funcionamiento del
 * programa. Contiene los métodos necesarios para las funcionalidades del
 * programa, entre ellas está hcer la copia de seguridad, eliminarla, comprobar
 * el espacio en el disco, buscar archivos duplicados y eliminarlos, ordenar el
 * backup.
 *
 * @author Gonzalo
 */
public class OperacionesFicheros {

    //Atributos
    public static ArrayList<File> listaFicheros = new ArrayList<>();
    JFrameVentanaPrincipal ventanaPrincipal;

    //Constructor
    public OperacionesFicheros(JFrameVentanaPrincipal ventanaProgresBar) {
        this.ventanaPrincipal = ventanaProgresBar;
    }

    /**
     * Método para copiar los fichero usando un método de la clase Files, es un
     * método recursivo, de forma que cuando encuentra una ruta correspondiente
     * a un directorio se vuelve a hacer la llamada al método para que compruebe
     * si hay ficheros dentro de este que haya que copiar.
     *
     * @param rutaOrigen ruta con los archivos que se quiere hacer la copia de
     * seguridad.
     * @param rutaDestino ruta donde se quiere hacer la copia de seguridad de la
     * rutaOrigen
     * @param progreso correspondiente a la barra de progreso de la interfaz
     * gráfica para poder actualizar el estado.
     * @return el número de ficheros que se copiaron.
     * @throws IOException
     */
    public int copiarFicheros(File rutaOrigen, File rutaDestino, int progreso) throws IOException {
        int contador = 0;
        if (rutaOrigen.isDirectory()) {
            //Para crear el directorio si no existe.
            if (!rutaDestino.exists()) {
                rutaDestino.mkdir();
            }
            String[] ficheros = rutaOrigen.list();
            for (String fichero : ficheros) {
                File ficheroOrigen = new File(rutaOrigen, fichero);
                File ficheroDestino = new File(rutaDestino, fichero);
                progreso += copiarFicheros(ficheroOrigen, ficheroDestino, progreso);
                contador = progreso;
            }
        } else {
            Files.copy(rutaOrigen.toPath(), rutaDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            contador++;
            updateProgress(progreso, rutaOrigen.getAbsolutePath());
            progreso++;
        }
        return contador;
    }

    /**
     * Método para copiar los fichero usando un método de la clase Files, es un
     * método recursivo, de forma que cuando encuentra una ruta correspondiente
     * a un directorio se vuelve a hacer la llamada al método para que compruebe
     * si hay ficheros dentro de este que haya que copiar. En este caso solo
     * copiaria los ficheros que tengan una extensión que esté contenida en la
     * lista.
     *
     * @param origen ruta con los archivos que se quiere hacer la copia de
     * seguridad.
     * @param destino ruta donde se quiere hacer la copia de seguridad de la
     * rutaOrigen
     * @param extensiones lista con las extensiones de tipos de archivos que se
     * quieren copiar.
     * @param progreso correspondiente a la barra de progreso de la interfaz
     * gráfica para poder actualizar el estado.
     * @return el número de ficheros copiados.
     * @throws java.io.IOException
     */
    public int copiarConFiltro(File origen, File destino, ArrayList<String> extensiones, int progreso) throws IOException {
        int contador = 0;
        if (extensiones.isEmpty()) {
            copiarFicheros(origen, destino, 1);
        } else {
            if (origen.isDirectory()) {
                if (!destino.exists()) {
                    destino.mkdir();
                }
                String[] ficheros = origen.list();
                for (String fichero : ficheros) {
                    File origen2 = new File(origen, fichero);
                    File destino2 = new File(destino, fichero);
                    progreso += copiarConFiltro(origen2, destino2, extensiones, progreso);
                    contador = progreso;
                }
            } else {
                for (String extension : extensiones) {
                    FiltroExtensiones filtro = new FiltroExtensiones(extension);
                    if (filtro.accept(origen)) {
                        Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        contador++;
                        updateProgress(progreso, origen.getAbsolutePath());
                        progreso++;
                    }
                }
            }
        }
        return contador;
    }

    /**
     * Método correspondiente a la interfaz gráfica para actualizar el estado de
     * la barra de progreso se utiliza el método invokeLater que se se procesa
     * después de que se procesen todos los eventos GUI anteriores.
     *
     * @param valorBarraProgreso un entero que sería para darle valor a la barra
     * de progreso
     * @param fichero para mostrar el fichero que se está copiando.
     */
    public void updateProgress(int valorBarraProgreso, String fichero) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ventanaPrincipal.actualizarInterfazProgreso(fichero, valorBarraProgreso);
                ventanaPrincipal.repaint();
            }
        });
    }

    /**
     * Método que busca en una ruta si hay carpetas vacías para después
     * borrarlas en caso de que el usuario decida hacer una copia de seguridad
     * solo imágenes y no se creen las carpetas vacías, si no encuntra ninguna
     * devuelve una lista vacía.
     *
     * @param carpetaRaiz ruta en la que se buscan las carpetas vacías.
     * @return una lista vacía en caso de que no encuentre ninguna carpeta vacía
     * y en caso contrario una lista con las carpetas vacías.
     */
    public static List<File> buscaCarpetasVacias(File carpetaRaiz) {
        //siempre retornamos al menos una lista vacía
        List<File> resultado = new ArrayList<>();
        if (carpetaRaiz.isDirectory()) {
            File[] carpetas = carpetaRaiz.listFiles(File::isDirectory);
            for (File carpeta : carpetas) {
                if (carpeta.listFiles().length == 0) {
                    resultado.add(carpeta);
                } else {
                    resultado.addAll(buscaCarpetasVacias(carpeta));
                }
            }
        }
        return resultado;
    }

    /**
     * Método que borra las carpetas vacías encontradas en una ruta especificada
     * usando el método buscaCarpetasVacias.
     *
     * @param carpetaRaiz ruta en la que se van a buscar y eliminar las carpetas
     * vacías.
     */
    public static void eliminaCarpetasVacias(File carpetaRaiz) {
        List<File> listaCarpetasVacias = buscaCarpetasVacias(carpetaRaiz);
        //El método buscar retornará una lista vacía por lo menos, por lo que
        //no necesitamos hacer una validación en caso de nulos
        for (Iterator<File> it = listaCarpetasVacias.iterator(); it.hasNext();) {
            File carpeta = it.next();
            carpeta.delete();
        }
        /*for (File carpeta : listaCarpetasVacias) {
        carpeta.delete();
        }*/
    }

    /**
     * Método que comprueba el espacio libre de la unidad en la que se hace la
     * copia de seguridad.
     *
     * @param disco que es el disco del que se quiere saber el espacio libre.
     * @return el espacio libre en gigas.
     */
    public static long espacioLibre(File disco) {
        long espacioLibre = disco.getFreeSpace();
        long espacioGigas = espacioLibre / 1024 / 1024 / 1024;
        return espacioGigas;
    }

    /**
     * Método para eliminar la copia de seguiridad, para ello se le pasa la ruta
     * por parámetro y se listan todos los archivos que hay en ella, es un
     * método recursivo para cuando encuentra un directorio.
     *
     * @param ruta de la copia de seguirad para borrarla.
     * @return el número de archivos borrados.
     * @throws IOException
     */
    public static int eliminarCopia(File ruta) throws IOException {
        int contador = 0;
        File[] ficheros = ruta.listFiles();
        for (int i = 0; i < ficheros.length; i++) {
            if (ficheros[i].isDirectory()) {
                contador += eliminarCopia(ficheros[i]);
            }
            ficheros[i].delete();
            contador++;
        }
        return contador;
    }

    /**
     * Método que busca duplicados en la copia de seguridad y devuelve una lista
     * con los que son duplicados. Solo se guarda en la lista uno de los
     * archivos.
     *
     * @param ruta que sería la copia de seguridad en la que se van a buscar los
     * archivos duplicados.
     * @return una lista con los archivos duplicados.
     * @throws java.io.IOException
     */
    public static ArrayList<File> buscarDuplicados(File ruta) throws IOException {
        ArrayList<File> lista = listarFicheros(ruta);
        ArrayList<File> listaDuplicados = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (!(i == j)) {
                    if (lista.get(i).getName().equalsIgnoreCase(lista.get(j).getName()) && lista.get(i).length() == lista.get(j).length()) {
                        listaDuplicados.add(lista.get(i));
                        lista.remove(lista.get(j));
                    }
                }
            }
        }
        return listaDuplicados;
    }

    /**
     * Método que borra los archivos duplicados encontrados usando el método
     * buscarDuplicados. Solo se borra uno de los archivos.
     *
     * @param ruta en la que se buscan y se van a borrar los archivos
     * duplicados.
     * @throws IOException
     */
    public static void borrarDuplicados(File ruta) throws IOException {
        ArrayList<File> duplicados = buscarDuplicados(ruta);
        for (int i = 0; i < duplicados.size(); i++) {
            if (duplicados.get(i).isDirectory()) {
                borrarDuplicados(duplicados.get(i));
            }
            duplicados.get(i).delete();
        }
    }

    /**
     * Método para listar los ficheros que hay en una ruta que se pasa como
     * parámetro y se guardan en una lista. Método recursivo para cuando
     * encuentra una ruta correspondiente a un directorio.
     *
     * @param ruta de donde se van a listar los ficheros.
     * @return una lista con todo los archivos que se encontraron en la ruta
     * introducida por parámetro.
     * @throws IOException
     */
    public static ArrayList<File> listarFicheros(File ruta) throws IOException {
        File[] listaInicial = ruta.listFiles();
        ArrayList<File> lista = new ArrayList<>();
        for (File file : listaInicial) {
            if (file.isDirectory()) {
                lista.add(file);
                ArrayList<File> ficheroRecursivo = listarFicheros(file);
                for (File file1 : ficheroRecursivo) {
                    lista.add(file1);
                }
            } else {
                lista.add(file);
            }
        }
        return lista;
    }

    /**
     * Método para listar los ficheros determinados que tengan una extensión que
     * haya en la lista que se pasa por parámetro, estos se buscan en un ruta
     * que se pasa como parámetro y se guardan en una lista. Método recursivo
     * para cuando encuentra una ruta correspondiente a un directorio.
     *
     * @param ruta de la cual se van a listar los ficheros que contiene.
     * @param extensiones con las extensiones de los tipos de ficheros que se
     * quieren listar.
     * @return una lista con los ficheros filtrados por extensiones.
     * @throws IOException
     */
    public static ArrayList<File> listarFicherosExtension(File ruta, ArrayList<String> extensiones) throws IOException {
        if (!extensiones.isEmpty()) {
            FiltroExtensiones filtro = new FiltroExtensiones(extensiones);
            FiltroDirectorios filtroDir = new FiltroDirectorios();

            File[] ficherosExtensiones = ruta.listFiles(filtro);
            File[] ficheroDirectorios = ruta.listFiles(filtroDir);

            listaFicheros.addAll(Arrays.asList(ficherosExtensiones));
            for (File file : ficheroDirectorios) {
                listarFicherosExtension(file, extensiones);
            }
            return listaFicheros;
        } else {
            return listaFicheros = listarFicheros(ruta);
        }
    }

    /**
     * Método que ordena el backup por tipo de archivos, para ello creo una
     * nueva carpeta BacUpOrdenado y dentro para cada archivo su correspondiente
     * carpeta por categoria de archivo
     *
     * @param ruta --> sería la ruta del backup hecho anteriormente.
     * @param nuevaRuta -->
     * @throws IOException
     */
    public static void oredenarBackUp(File ruta, String nuevaRuta) throws IOException {
        File nuevaCarpeta = new File(nuevaRuta + "Ordenado");
        if (!nuevaCarpeta.exists()) {
            nuevaCarpeta.mkdir();
        }
        File[] listFicherosBackUp = ruta.listFiles();
        for (File fichero : listFicherosBackUp) {
            if (fichero.isDirectory()) {
                oredenarBackUp(fichero, nuevaRuta);
            } else {
                if (Imagenes.comprobarImagen(fichero)) {
                    Imagenes.backUpImagenes(nuevaCarpeta, fichero);
                } else if (Videos.comprobarVideo(fichero)) {
                    Videos.backupVideos(nuevaCarpeta, fichero);
                } else if (Texto.comprobarTexto(fichero)) {
                    Texto.backupTextos(nuevaCarpeta, fichero);
                } else if (Audio.comprobarAudio(fichero)) {
                    Audio.backupAudios(nuevaCarpeta, fichero);
                } else {
                    File otros = new File(nuevaCarpeta.getAbsolutePath() + File.separator + "Otros");
                    if (!otros.exists()) {
                        otros.mkdir();
                    }
                    File ficheroCompleto = new File(otros.getAbsolutePath() + File.separator + fichero.getName());
                    Files.copy(fichero.toPath(), ficheroCompleto.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

}
