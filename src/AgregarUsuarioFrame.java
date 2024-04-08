import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgregarUsuarioFrame extends JFrame {
    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail, txtUsuario, txtContraseña;
    private JButton btnAgregar;

    public AgregarUsuarioFrame() {
        super("Agregar Usuario");

        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        add(txtNombre);

        add(new JLabel("Apellido:"));
        txtApellido = new JTextField(20);
        add(txtApellido);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(20);
        add(txtTelefono);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(20);
        add(txtEmail);

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(20);
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtContraseña = new JTextField(20);
        add(txtContraseña);

        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this::accionAgregarUsuario);
        add(btnAgregar);

        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    private void accionAgregarUsuario(ActionEvent e) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO usuarios (nombre, apellido, telefono, correo_electronico, nombre_usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, telefono);
                ps.setString(4, email);
                ps.setString(5, usuario);
                ps.setString(6, contraseña);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Usuario agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); // Cerrar la ventana después de agregar el usuario
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

