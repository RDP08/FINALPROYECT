import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarProductoFrame extends JFrame {
    public final JTextField txtDescripcion;
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtCantidad;
    private final JButton btnActualizar;
    private final int productoId;

    public ActualizarProductoFrame() {
        this(-1); // Usar un valor predeterminado para el productoId
    }

    public ActualizarProductoFrame(int productoId) {
        super("Actualizar Producto");
        this.productoId = productoId;

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField(20);
        add(txtDescripcion);

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField(20);
        add(txtPrecio);

        add(new JLabel("Cantidad Disponible:"));
        txtCantidad = new JTextField(20);
        add(txtCantidad);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this::accionActualizarProducto);
        add(btnActualizar);

        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarProductoFrame frame = new ActualizarProductoFrame();
            frame.setVisible(true);
        });
    }

    private void accionActualizarProducto(ActionEvent e) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidaddisponible = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setDouble(3, precio);
                ps.setInt(4, cantidad);
                ps.setInt(5, productoId);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Producto actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Cerrar la ventana después de la actualización
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
