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
        JButton botonGestionUsuarios = new JButton("Gestionar Usuarios");
        JButton botonGestionProductos = new JButton("Gestionar Productos");

        botonGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir el panel de gestión de usuarios
                JOptionPane.showMessageDialog(PanelPrincipal.this, "Abriendo panel de gestión de usuarios");
            }
        });

        botonGestionProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir el panel de gestión de productos
                JOptionPane.showMessageDialog(PanelPrincipal.this, "Abriendo panel de gestión de productos");
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(botonGestionUsuarios);
        panel.add(botonGestionProductos);

        add(panel);
    }
}

