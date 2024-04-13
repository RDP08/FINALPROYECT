import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private final JTextField campoUsuario = new JTextField(20);
    private final JPasswordField campoContraseña = new JPasswordField(20);
    private final JButton botonIniciarSesion = new JButton("Iniciar Sesión");
    private final JButton botonRegistrarse = new JButton("Registrarse");
    private final InicioSesion inicioSesion = new InicioSesion();

    public LoginFrame() {
        super("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelInicioSesion = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInicioSesion.add(new JLabel("Usuario:"));
        panelInicioSesion.add(campoUsuario);
        panelInicioSesion.add(new JLabel("Contraseña:"));
        panelInicioSesion.add(campoContraseña);

        JPanel panelBotonesInicioSesion = new JPanel();
        panelBotonesInicioSesion.add(botonIniciarSesion);
        panelBotonesInicioSesion.add(botonRegistrarse);

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contraseña = new String(campoContraseña.getPassword());

                // Lógica para iniciar sesión
                boolean autenticado = inicioSesion.verificarCredenciales(usuario, contraseña);

                if (autenticado) {
                    // Si las credenciales son válidas, muestra el panel principal
                    mostrarPanelPrincipal();
                } else {
                    // Si las credenciales son inválidas, muestra un mensaje de error
                    JOptionPane.showMessageDialog(LoginFrame.this, "Usuario o contraseña incorrectos");
                }
            }
        });

        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroFrame registroFrame = new RegistroFrame();
                registroFrame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        panel.add(campoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContraseña);
        panel.add(botonIniciarSesion);
        panel.add(botonRegistrarse);

        add(panel, BorderLayout.CENTER);
    }

    private void mostrarPanelPrincipal() {
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        panelPrincipal.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
