import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarUsuarioFrame extends JFrame {
    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail, txtUsuario, txtContraseña;
    private JButton btnActualizar;
    private int usuarioId;

    public ActualizarUsuarioFrame(int usuarioId) {
        super("Actualizar Usuario");
        this.usuarioId = usuarioId;

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

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this::accionActualizarUsuario);
        add(btnActualizar);

        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    private void accionActualizarUsuario(ActionEvent e) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, correo_electronico=?, nombre_usuario=?, contraseña=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, telefono);
            pstmt.setString(4, email);
            pstmt.setString(5, usuario);
            pstmt.setString(6, contraseña);
            pstmt.setInt(7, usuarioId);

            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

