import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AgregarProductoForm extends JFrame {
    private JTextField txtNombre;
    private JTextField descripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;

    public AgregarProductoForm() {
        super("Agregar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Descripcion:"));
        descripcion = new JTextField(); // Inicialización correcta
        panel.add(descripcion);


        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad Disponible:")); // Cambiado a "Cantidad Disponible"
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        panel.add(btnAgregar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnCancelar);

        add(panel);
    }

    private void agregarProducto() {
        // Obtener los datos del formulario
        String nombre = txtNombre.getText();
        String Descripcion = descripcion.getText(); // Modificado para obtener la descripción
        double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText()); // Cambiado a "cantidaddisponible"

        // Crear la conexión a la base de datos
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Crear la consulta SQL para insertar el nuevo producto
            String sql = "INSERT INTO productos (nombre, Descripcion, precio, cantidaddisponible) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecer los parámetros de la consulta
                pstmt.setString(1, nombre);
                pstmt.setString(2, Descripcion);
                pstmt.setDouble(3, precio);
                pstmt.setInt(4, cantidad);

                // Ejecutar la consulta
                pstmt.executeUpdate();

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Producto agregado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            // Mostrar mensaje de error en caso de fallo
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Cerrar el formulario después de agregar el producto
        dispose();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgregarProductoForm form = new AgregarProductoForm();
            form.setVisible(true);
        });
    }
}
