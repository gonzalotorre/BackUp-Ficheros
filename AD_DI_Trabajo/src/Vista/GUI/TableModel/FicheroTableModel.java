package Vista.GUI.TableModel;

import Vista.DTO.Fichero;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Clase TableModel que hereda de AbstractTableModel para crear la tabla
 *
 * @author Gonzalo
 */
public class FicheroTableModel extends AbstractTableModel {

    //Atributos.
    private List<Fichero> ficheros;
    private String[] cabecera = {"Nombre", "Extension", "Peso"};

    public FicheroTableModel(List<Fichero> ficheros) {
        this.ficheros = ficheros;
    }

    /**
     * @return el total de filas que tiene la tabla.
     */
    @Override
    public int getRowCount() {
        return ficheros.size();
    }

    /**
     * @return el número de columnas de la tabla.
     */
    @Override
    public int getColumnCount() {
        return cabecera.length;
    }

    /**
     * 
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ficheros.get(rowIndex).getNombre();
            case 1:
                return ficheros.get(rowIndex).getExtension();
            case 2:
                return ficheros.get(rowIndex).getPeso();
        }
        return null;
    }

    @Override
    public String getColumnName(int i) {
        return cabecera[i];
    }

    public void deleteRow(int row) {
        if (!(ficheros.size() == 0)) {
            ficheros.remove(row);
        }
        fireTableDataChanged();
    }

    /*public void refrescar() {
    fireTableDataChanged();
    }*/

}
