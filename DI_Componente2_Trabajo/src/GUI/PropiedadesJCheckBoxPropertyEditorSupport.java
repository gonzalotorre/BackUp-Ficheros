package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Gonzalo
 */
public class PropiedadesJCheckBoxPropertyEditorSupport extends PropertyEditorSupport {

    private PropiedadesJCheckBoxJPanel panel = new PropiedadesJCheckBoxJPanel();

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return panel;
    }

    @Override
    public String getJavaInitializationString() {
        Color colorFondo = panel.getPropiedadSeleccionada().getColorTexto();
        Font fuente = panel.getPropiedadSeleccionada().getTipoFuente();
        return "new GUI.PropiedadesCheckBox(new java.awt.Color(" + colorFondo.getRGB() 
                + "), new java.awt.Font(\"" + fuente.getName() + "\"," + Font.BOLD + "," + fuente.getSize() + "))";
    }

    @Override
    public Object getValue() {
        return panel.getPropiedadSeleccionada();
    }

}
