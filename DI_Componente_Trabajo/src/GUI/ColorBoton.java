package GUI;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Gonzalo
 */
public class ColorBoton implements Serializable {
    
    private Color colorFondo;

    public ColorBoton(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }
    
}
