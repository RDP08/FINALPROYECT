import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JFrame {
    public PanelPrincipal() {
        super("Panel Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JButton botonGestionarProductos = new JButton("Gestionar Productos");
        botonGestionarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionProductosFrame gestionProductosFrame = new GestionProductosFrame();
                gestionProductosFrame.setVisible(true);
            }
        });

        JButton botonGestionarUsuarios = new JButton("Gestionar Usuarios");
        botonGestionarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionUsuariosFrame gestionUsuariosFrame = new GestionUsuariosFrame();
                gestionUsuariosFrame.setVisible(true);
            }
        });

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBotones.add(botonGestionarProductos);
        panelBotones.add(botonGestionarUsuarios);
        panelBotones.add(botonCerrarSesion);
        add(panelBotones, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PanelPrincipal().setVisible(true));
    }
}
