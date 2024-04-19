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
        String nombre = txtNombre.getText();
        String Descripcion = descripcion.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO productos (nombre, Descripcion, precio, cantidaddisponible) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, Descripcion);
                pstmt.setDouble(3, precio);
                pstmt.setInt(4, cantidad);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Producto agregado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgregarProductoForm form = new AgregarProductoForm();
            form.setVisible(true);
        });
    }
}
