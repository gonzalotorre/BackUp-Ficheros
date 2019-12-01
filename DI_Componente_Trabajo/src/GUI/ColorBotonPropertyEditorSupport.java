package GUI;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Gonzalo
 */
public class ColorBotonPropertyEditorSupport extends PropertyEditorSupport {
    
    private ColorFondoBotonJPanel jpanelColor = new ColorFondoBotonJPanel();
    
    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return jpanelColor;
    }

    @Override
    public String getJavaInitializationString() {
        Color colorFondo = jpanelColor.getPropiedadSeleccionada().getColorFondo();
        return "new GUI.ColorBoton(new java.awt.Color(" + colorFondo.getRGB() + "))";
    }

    @Override
    public Object getValue() {
        return jpanelColor.getPropiedadSeleccionada();
    }
    
}
