/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
