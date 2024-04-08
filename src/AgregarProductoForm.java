import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarProductoForm extends JFrame {
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtCategoria;
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

        panel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panel.add(txtMarca);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad:"));
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
        // Aquí iría la lógica para guardar el nuevo producto en la base de datos
        // Por simplicidad, mostramos un mensaje indicando que la funcionalidad aún no está implementada
        JOptionPane.showMessageDialog(this, "Funcionalidad de agregar producto aún no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Cerramos el formulario después de agregar el producto
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgregarProductoForm form = new AgregarProductoForm();
            form.setVisible(true);
        });
    }
}
