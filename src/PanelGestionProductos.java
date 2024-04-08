import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelGestionProductos extends JPanel {
    private final List<String> listaProductos;
    private final DefaultListModel<String> modeloLista;
    private final JList<String> lista;
    private final JButton botonAgregar;
    private final JButton botonEditar;
    private final JButton botonBorrar;

    public PanelGestionProductos() {
        setLayout(new BorderLayout());

        // Lista de productos (simulada)
        listaProductos = new ArrayList<>();
        listaProductos.add("Producto 1 - Descripción 1");
        listaProductos.add("Producto 2 - Descripción 2");
        listaProductos.add("Producto 3 - Descripción 3");

        // Modelo para la lista de productos
        modeloLista = new DefaultListModel<>();
        for (String producto : listaProductos) {
            modeloLista.addElement(producto);
        }
        lista = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(lista);

        // Botones
        botonAgregar = new JButton("Agregar Producto");
        botonEditar = new JButton("Editar Producto");
        botonBorrar = new JButton("Borrar Producto");

        // Agregar componentes al panel
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 5, 5));
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonBorrar);
        add(panelBotones, BorderLayout.SOUTH);

        // Manejadores de eventos para los botones
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para agregar un nuevo producto
                // Por ahora, simplemente añadiremos un producto ficticio a la lista
                String nuevoProducto = "Nuevo Producto - Descripción";
                listaProductos.add(nuevoProducto);
                modeloLista.addElement(nuevoProducto);
            }
        });

        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para editar el producto seleccionado en la lista
                // Por ahora, simplemente mostraremos un mensaje de demostración
                int indiceSeleccionado = lista.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    String productoSeleccionado = listaProductos.get(indiceSeleccionado);
                    JOptionPane.showMessageDialog(PanelGestionProductos.this,
                            "Editando el producto: " + productoSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(PanelGestionProductos.this,
                            "Seleccione un producto para editar");
                }
            }
        });

        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para borrar el producto seleccionado de la lista
                // Por ahora, simplemente lo eliminaremos de la lista y del modelo
                int indiceSeleccionado = lista.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    listaProductos.remove(indiceSeleccionado);
                    modeloLista.removeElementAt(indiceSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(PanelGestionProductos.this,
                            "Seleccione un producto para borrar");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel de Gestión de Productos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null); // Centrar en pantalla
            frame.add(new PanelGestionProductos());
            frame.setVisible(true);
        });
    }
}
