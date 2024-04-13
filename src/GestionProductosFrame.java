import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class GestionProductosFrame extends JFrame {
    private JTable tablaProductos;
    private JButton btnAgregarProducto, btnVolver;
    private Vector<String> columnas;
    private Vector<Vector<Object>> datos;

    public GestionProductosFrame() {
        super("Gestión de Productos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarComponentes();
        cargarDatosProductos();
    }

    private void inicializarComponentes() {
        columnas = new Vector<>();
        columnas.add("ID");
        columnas.add("Nombre");
        columnas.add("Descripción");
        columnas.add("Precio");
        columnas.add("Cantidad Disponible");

        datos = new Vector<>();
        tablaProductos = new JTable(new DefaultTableModel(datos, columnas));
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregarProducto = new JButton("Agregar Producto");
        JButton btnActualizarProducto = new JButton("Actualizar Producto");
        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        btnVolver = new JButton("Volver");

        btnAgregarProducto.addActionListener(this::accionAgregarProducto);
        btnActualizarProducto.addActionListener(this::accionActualizarProducto);
        btnEliminarProducto.addActionListener(this::accionEliminarProducto);
        btnVolver.addActionListener(this::accionVolver);

        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnActualizarProducto);
        panelBotones.add(btnEliminarProducto);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatosProductos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar datos
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {
            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                fila.add(rs.getInt("id"));
                fila.add(rs.getString("nombre"));
                fila.add(rs.getString("descripcion"));
                fila.add(rs.getDouble("precio"));
                fila.add(rs.getInt("cantidadDisponible"));
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de productos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionAgregarProducto(ActionEvent e) {
        AgregarProductoForm agregarProductoForm = new AgregarProductoForm();
        agregarProductoForm.setVisible(true);
    }

    private void accionActualizarProducto(ActionEvent e) {
        // Obtener el id del producto
        int productoId = obtenerIdProductoDesdeInterfaz(); // Reemplaza esta línea con la forma de obtener el id del producto

        // Crear una instancia de ActualizarProductoFrame con el id del producto
        ActualizarProductoFrame actualizarProductoFrame = new ActualizarProductoFrame(productoId);

        // Hacer visible la ventana de actualización de producto
        actualizarProductoFrame.setVisible(true);
    }

    private void accionEliminarProducto(ActionEvent e) {
        EliminarProductoFrame eliminarProductoFram = new EliminarProductoFrame();
        eliminarProductoFram.setVisible(true);
    }

    private void accionVolver(ActionEvent e) {
        // Cerrar el panel actual (GestionProductosFrame)
        dispose();

        PanelPrincipal PanelPrincipal = new PanelPrincipal();
        PanelPrincipal.setVisible(true);
    }

    private int obtenerIdProductoDesdeInterfaz() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return -1;
        }

        return (int) tablaProductos.getValueAt(filaSeleccionada, 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionProductosFrame().setVisible(true));
    }
}
