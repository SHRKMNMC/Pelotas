package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataPanel extends JTable {

    private DefaultTableModel tableModel;

    public DataPanel() {
        // Definir columnas: "Dato" y "Valor"
        String[] columnNames = {"Dato", "Valor"};

        // Datos iniciales con valores por defecto
        Object[][] data = {
                {"FPS", 0},
                {"Pelotas", 0}
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        // Configurar el modelo de la tabla
        setModel(tableModel);
        setFillsViewportHeight(true);

        // Ajustar ancho de columnas
        getColumnModel().getColumn(0).setPreferredWidth(100);
        getColumnModel().getColumn(1).setPreferredWidth(50);
    }

    public void setFPS(int fps) {
        tableModel.setValueAt(fps, 0, 1); // fila 0, columna 1
    }

    public void setCantidadPelotas(int cantidad) {
        tableModel.setValueAt(cantidad, 1, 1); // fila 1, columna 1
    }
}
