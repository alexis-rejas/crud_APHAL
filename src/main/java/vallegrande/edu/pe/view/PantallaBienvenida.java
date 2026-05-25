package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class PantallaBienvenida extends JFrame {

    public PantallaBienvenida() {
        setTitle("ValleGrande — Sistema de Gestión");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel central
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(34, 55, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        JLabel sub = new JLabel("ValleGrande Edu Pe", SwingConstants.CENTER);
        sub.setFont(new Font("Georgia", Font.PLAIN, 14));
        sub.setForeground(new Color(180, 210, 150));
        gbc.gridy = 1;
        panel.add(sub, gbc);

        // Botones módulos
        JButton btnUsuarios = crearBoton("👤 Gestión de Usuarios");
        gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(btnUsuarios, gbc);

        JButton btnSalir = crearBoton("🚪 Salir");
        btnSalir.setBackground(new Color(120, 40, 40));
        gbc.gridx = 1;
        panel.add(btnSalir, gbc);

        // Acciones
        btnUsuarios.addActionListener(e -> {
            new UsuarioListaView().setVisible(true);
            dispose();
        });
        btnSalir.addActionListener(e -> System.exit(0));

        add(panel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(74, 107, 42));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}