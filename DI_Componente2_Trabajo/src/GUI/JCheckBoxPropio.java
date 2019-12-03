package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JCheckBox;

/**
 *
 * @author Gonzalo
 */
public class JCheckBoxPropio extends JCheckBox implements Serializable {

    private PropiedadesCheckBox propiedades;

    public JCheckBoxPropio() {
        super();
        super.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected()) {
                    setForeground(propiedades.getColorTexto());
                    setFont(propiedades.getTipoFuente());
                } else {
                    setForeground(Color.BLACK);
                    Font fuente = new Font("Tahoma", Font.PLAIN, 11);
                    setFont(fuente);
                }

            }
        });
    }

    public PropiedadesCheckBox getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(PropiedadesCheckBox propiedades) {
        this.propiedades = propiedades;
    }

}
