package GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author Gonzalo
 */
public class JButtonColorFondo extends JButton implements Serializable {

    private ColorBoton colorBoton;

    public JButtonColorFondo() {
        super();
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorBoton.getColorFondo());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new JButton().getBackground());
            }

        });
    }

    public ColorBoton getColorBoton() {
        return colorBoton;
    }

    public void setColorBoton(ColorBoton colorBoton) {
        this.colorBoton = colorBoton;
    }

}
