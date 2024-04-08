import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroFrame extends JFrame {
    private JTextField campoNombreUsuario = new JTextField(20);
    private JTextField campoNombre = new JTextField(20);
    private JTextField campoApellido = new JTextField(20);
    private JPasswordField campoContraseña = new JPasswordField(20);
    private JPasswordField campoConfirmarContraseña = new JPasswordField(20);
    private JTextField campoTelefono = new JTextField(20);
    private JTextField campoCorreoElectronico = new JTextField(20);
    private JButton botonRegistrar = new JButton("Registrar");

    public RegistroFrame() {
        super("Registro de Usuario");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Nombre de Usuario:"));
        panel.add(campoNombreUsuario);
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(new JLabel("Confirmar Contraseña:"));
        panel.add(campoConfirmarContraseña);
        panel.add(new JLabel("Teléfono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("Correo Electrónico:"));
        panel.add(campoCorreoElectronico);
        panel.add(new JLabel("")); // Espacio en blanco para el botón
        panel.add(botonRegistrar);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para registrar al usuario
                JOptionPane.showMessageDialog(RegistroFrame.this, "Usuario registrado correctamente");
                // Cerrar la ventana de registro después de registrar al usuario
                dispose();
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroFrame().setVisible(true));
    }
}
