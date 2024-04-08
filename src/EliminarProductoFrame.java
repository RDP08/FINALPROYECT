import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarProductoFrame extends JFrame {
    private JTextField txtIdProducto;
    private JButton btnEliminar;

    public EliminarProductoFrame() {
        super("Eliminar Producto");
        setLayout(new GridLayout(2, 2));
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("ID del Producto:"));
        txtIdProducto = new JTextField();
        add(txtIdProducto);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::accionEliminarProducto);
        add(btnEliminar);

        setVisible(true);
    }

    private void accionEliminarProducto(ActionEvent e) {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            eliminarProducto();
        }
    }

    private void eliminarProducto() {
        String query = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            int productoId = Integer.parseInt(txtIdProducto.getText().trim());
            ps.setInt(1, productoId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un ID de producto válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto.", "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EliminarProductoFrame(); // Para propósitos de prueba, simplemente mostramos la ventana.
    }
}

