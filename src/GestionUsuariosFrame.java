
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class GestionUsuariosFrame extends JFrame {
    private JTable tablaUsuarios;
    private JButton btnAgregarUsuario, btnActualizarUsuario, btnEliminarUsuario, btnVolver;
    private Vector<String> columnas;
    private Vector<Vector<Object>> datos;

    public GestionUsuariosFrame() {
        super("Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarComponentes();
        cargarDatosUsuarios();
    }

    private void inicializarComponentes() {
        columnas = new Vector<>();
        columnas.add("ID");
        columnas.add("Nombre");
        columnas.add("Apellido");
        columnas.add("Email");
        columnas.add("Usuario");
        columnas.add("Contraseña");

        datos = new Vector<>();
        tablaUsuarios = new JTable(new DefaultTableModel(datos, columnas));
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnActualizarUsuario = new JButton("Actualizar Usuario");
        btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnVolver = new JButton("Volver");

        btnAgregarUsuario.addActionListener(this::accionAgregarUsuario);
        btnActualizarUsuario.addActionListener(this::accionActualizarUsuario);
        btnEliminarUsuario.addActionListener(this::accionEliminarUsuario);
        btnVolver.addActionListener(this::accionVolver);

        panelBotones.add(btnAgregarUsuario);
        panelBotones.add(btnActualizarUsuario);
        panelBotones.add(btnEliminarUsuario);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatosUsuarios() {
        DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar datos
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                fila.add(rs.getInt("id"));
                fila.add(rs.getString("nombre"));
                fila.add(rs.getString("apellido"));
                fila.add(rs.getString("correo_electronico"));
                fila.add(rs.getString("nombre_usuario"));
                fila.add(rs.getString("contraseña"));
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionAgregarUsuario(ActionEvent e) {
        AgregarUsuarioFrame agregarUsuarioFrame = new AgregarUsuarioFrame();
        agregarUsuarioFrame.setVisible(true);
    }


    private void accionActualizarUsuario(ActionEvent e) {
        int usuarioId = obtenerIdUsuarioDesdeInterfaz(); // Reemplaza esta línea con la forma de obtener el id del usuario
        if (usuarioId != -1) {
            ActualizarUsuarioFrame actualizarUsuarioFrame = new ActualizarUsuarioFrame(usuarioId);
            actualizarUsuarioFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdUsuarioDesdeInterfaz() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
            return (int) modelo.getValueAt(filaSeleccionada, 0); // Suponiendo que el ID del usuario está en la primera columna
        } else {
            return -1; // Si no se ha seleccionado ninguna fila, devolver -1
        }
    }

    private void accionEliminarUsuario(ActionEvent e) {
        int usuarioId = obtenerIdUsuarioDesdeInterfaz(); // Reemplaza esta línea con la forma de obtener el ID del usuario seleccionado

        if (usuarioId != -1) {
            EliminarUsuarioFrame eliminarUsuarioFrame = new EliminarUsuarioFrame();
            eliminarUsuarioFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void accionVolver(ActionEvent e) {
        // Cerrar el panel actual (GestionUsuariosFrame)
        dispose();

        // Mostrar el panel principal (PanelPrincipal)
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        panelPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionUsuariosFrame().setVisible(true));
    }
}
