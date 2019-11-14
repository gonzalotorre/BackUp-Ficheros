package Vista.DTO;

/**
 * Clase fichero que se va a utilizar para poner los valores de cada fichero
 * (nombre, extensión y peso) en una tabla para la interfaz gráfica y ver que
 * archivos se copiaron.
 *
 * @author Gonzalo
 */
public class Fichero {    

    //Atributos
    private String nombre;
    private String extension;
    private long peso;

    //Constructor
    public Fichero(String nombre, String extension, long peso) {
        this.nombre = nombre;
        this.extension = extension;
        this.peso = peso;
    }

    //GETTER Y SETTER
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

}
