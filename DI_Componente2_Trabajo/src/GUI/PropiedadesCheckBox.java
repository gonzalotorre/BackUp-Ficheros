package GUI;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

/**
 *
 * @author Gonzalo
 */
public class PropiedadesCheckBox implements Serializable {

    private Color colorTexto;
    private Font tipoFuente;

    public PropiedadesCheckBox(Color colorTexto, Font tipoFuente) {
        this.colorTexto = colorTexto;
        this.tipoFuente = tipoFuente;
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

    public Font getTipoFuente() {
        return tipoFuente;
    }

    public void setTipoFuente(Font tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

}
