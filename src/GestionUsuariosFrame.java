import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GestionUsuariosFrame extends JFrame {
    private final JTable tablaUsuarios;
    private final JButton btnActualizar;
    private final JButton btnEliminar;

    public GestionUsuariosFrame() {
        super("Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tablaUsuarios = new JTable();
        cargarDatosUsuarios();
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnActualizar = new JButton("Actualizar Usuario");
        btnEliminar = new JButton("Eliminar Usuario");

        btnActualizar.addActionListener(this::accionActualizarUsuario);
        btnEliminar.addActionListener(this::accionEliminarUsuario);

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatosUsuarios() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre de Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo Electrónico");

        String query = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                fila.add(rs.getInt("id"));
                fila.add(rs.getString("nombreUsuario"));
                fila.add(rs.getString("nombre"));
                fila.add(rs.getString("apellido"));
                fila.add(rs.getString("telefono"));
                fila.add(rs.getString("correo"));
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        tablaUsuarios.setModel(modelo);
    }

    private void accionActualizarUsuario(ActionEvent e) {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        } else {
            int idUsuario = (int) tablaUsuarios.getValueAt(filaSeleccionada, 0);
            // Aquí abrirías una ventana para actualizar los datos del usuario con el ID correspondiente
            // Por simplicidad, este código no está incluido. Se asume que la actualización se manejará en otra clase.
        }
    }

    private void accionEliminarUsuario(ActionEvent e) {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int idUsuario = (int) tablaUsuarios.getValueAt(filaSeleccionada, 0);
                eliminarUsuario(idUsuario);
            }
        }
    }

    private void eliminarUsuario(int idUsuario) {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosUsuarios(); // Actualizar la tabla después de la eliminación
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionUsuariosFrame().setVisible(true));
    }
}

