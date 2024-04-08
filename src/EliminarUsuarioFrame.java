import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarUsuarioFrame extends JFrame {
    private JTextField txtIdUsuario;
    private JButton btnEliminar;

    public EliminarUsuarioFrame() {
        super("Eliminar Usuario");

        setLayout(new GridLayout(2, 2));

        add(new JLabel("ID Usuario:"));
        txtIdUsuario = new JTextField(20);
        add(txtIdUsuario);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::accionEliminarUsuario);
        add(btnEliminar);

        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void accionEliminarUsuario(ActionEvent e) {
        int idUsuario = Integer.parseInt(txtIdUsuario.getText());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); // Cerrar la ventana después de eliminar el usuario
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

