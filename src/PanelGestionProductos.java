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

        listaProductos = new ArrayList<>();
        listaProductos.add("Producto 1 - Descripción 1");
        listaProductos.add("Producto 2 - Descripción 2");
        listaProductos.add("Producto 3 - Descripción 3");

        modeloLista = new DefaultListModel<>();
        for (String producto : listaProductos) {
            modeloLista.addElement(producto);
        }
        lista = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(lista);

        botonAgregar = new JButton("Agregar Producto");
        botonEditar = new JButton("Editar Producto");
        botonBorrar = new JButton("Borrar Producto");

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 5, 5));
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonBorrar);
        add(panelBotones, BorderLayout.SOUTH);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoProducto = JOptionPane.showInputDialog(PanelGestionProductos.this,
                        "Ingrese el nombre del nuevo producto:");
                if (nuevoProducto != null && !nuevoProducto.isEmpty()) {
                    listaProductos.add(nuevoProducto);
                    modeloLista.addElement(nuevoProducto);
                }
            }
        });

        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = lista.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    String nuevoNombre = JOptionPane.showInputDialog(PanelGestionProductos.this,
                            "Ingrese el nuevo nombre del producto:");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        listaProductos.set(indiceSeleccionado, nuevoNombre);
                        modeloLista.set(indiceSeleccionado, nuevoNombre);
                    }
                } else {
                    JOptionPane.showMessageDialog(PanelGestionProductos.this,
                            "Seleccione un producto para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = lista.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    listaProductos.remove(indiceSeleccionado);
                    modeloLista.removeElementAt(indiceSeleccionado);
                } else {
                    JOptionPane.showMessageDialog(PanelGestionProductos.this,
                            "Seleccione un producto para borrar", "Error", JOptionPane.ERROR_MESSAGE);
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
