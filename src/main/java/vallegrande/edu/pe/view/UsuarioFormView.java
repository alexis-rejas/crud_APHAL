package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.UsuarioController;
import vallegrande.edu.pe.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class UsuarioFormView extends JDialog {
    private final UsuarioController ctrl = new UsuarioController();
    private final UsuarioListaView  padre;
    private final Usuario           usuarioEditar;

    private JTextField  txtNombre, txtCorreo, txtTelefono;
    private JComboBox<String> cmbAsunto;
    private JTextArea   txtMensaje;
    private JLabel      lblNombreError, lblCorreoError, lblTelefonoError;

    public UsuarioFormView(UsuarioListaView padre, Usuario u) {
        super(padre, u == null ? "Nuevo Usuario" : "Editar Usuario", true);
        this.padre         = padre;
        this.usuarioEditar = u;
        construirUI();
        if (u != null) precargar(u);
    }

    private void construirUI() {
        setSize(480, 520);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(4, 4, 0, 4);

        // Nombre
        g.gridx=0; g.gridy=0; form.add(label("Nombre *"), g);
        txtNombre = campo("Ej: Juan Pérez");
        g.gridy=1; form.add(txtNombre, g);
        lblNombreError = error();
        g.gridy=2; form.add(lblNombreError, g);

        // Correo
        g.gridy=3; form.add(label("Correo electrónico *"), g);
        txtCorreo = campo("ejemplo@correo.com");
        g.gridy=4; form.add(txtCorreo, g);
        lblCorreoError = error();
        g.gridy=5; form.add(lblCorreoError, g);

        // Teléfono
        g.gridy=6; form.add(label("Teléfono (PE: 9XXXXXXXX) *"), g);
        txtTelefono = campo("9XXXXXXXX");
        g.gridy=7; form.add(txtTelefono, g);
        lblTelefonoError = error();
        g.gridy=8; form.add(lblTelefonoError, g);

        // Asunto
        g.gridy=9; form.add(label("Asunto *"), g);
        String[] asuntos = {
                "-- Selecciona un asunto --",
                "Consulta sobre exportación",
                "Información sobre asociación",
                "Consulta sobre producción agrícola",
                "Alianzas estratégicas",
                "Capacitaciones",
                "Otro"
        };
        cmbAsunto = new JComboBox<>(asuntos);
        cmbAsunto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        g.gridy=10; form.add(cmbAsunto, g);

        // Mensaje
        g.gridy=11; form.add(label("Mensaje *"), g);
        txtMensaje = new JTextArea(4, 20);
        txtMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        g.gridy=12; form.add(new JScrollPane(txtMensaje), g);

        add(form, BorderLayout.CENTER);

        // Botones
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBtn.setBackground(Color.WHITE);
        JButton btnGuardar  = new JButton(usuarioEditar == null ? "GUARDAR" : "ACTUALIZAR");
        JButton btnCancelar = new JButton("CANCELAR");

        estilizarBoton(btnGuardar,  new Color(74, 107, 42));
        estilizarBoton(btnCancelar, new Color(120, 40, 40));

        btnGuardar.addActionListener(e  -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        panelBtn.add(btnGuardar);
        panelBtn.add(btnCancelar);
        add(panelBtn, BorderLayout.SOUTH);
    }

    private void guardar() {
        // Limpiar errores
        lblNombreError.setText(""); lblCorreoError.setText(""); lblTelefonoError.setText("");

        String nombre   = txtNombre.getText().trim();
        String correo   = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String asunto   = cmbAsunto.getSelectedIndex() == 0 ? "" : (String) cmbAsunto.getSelectedItem();
        String mensaje  = txtMensaje.getText().trim();

        boolean hayError = false;

        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            lblNombreError.setText("⚠ Solo letras, no puede estar vacío.");
            hayError = true;
        }
        if (!correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            lblCorreoError.setText("⚠ Correo inválido.");
            hayError = true;
        }
        if (!telefono.matches("^9\\d{8}$")) {
            lblTelefonoError.setText("⚠ Teléfono: 9 dígitos, empieza en 9.");
            hayError = true;
        }
        if (asunto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un asunto.", "Error", JOptionPane.WARNING_MESSAGE);
            hayError = true;
        }
        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El mensaje no puede estar vacío.", "Error", JOptionPane.WARNING_MESSAGE);
            hayError = true;
        }
        if (hayError) return;

        String resultado = usuarioEditar == null
                ? ctrl.guardar(nombre, correo, telefono, asunto, mensaje)
                : ctrl.actualizar(usuarioEditar.getId(), nombre, correo, telefono, asunto, mensaje);

        if ("OK".equals(resultado)) {
            JOptionPane.showMessageDialog(this, "✅ Operación exitosa.");
            padre.cargarDatos();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void precargar(Usuario u) {
        txtNombre.setText(u.getNombre());
        txtCorreo.setText(u.getCorreo());
        txtTelefono.setText(u.getTelefono());
        for (int i = 1; i < cmbAsunto.getItemCount(); i++)
            if (cmbAsunto.getItemAt(i).equals(u.getAsunto())) { cmbAsunto.setSelectedIndex(i); break; }
        txtMensaje.setText(u.getMensaje());
    }

    private JTextField campo(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        return f;
    }
    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(new Color(60, 80, 40));
        return l;
    }
    private JLabel error() {
        JLabel l = new JLabel("");
        l.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        l.setForeground(new Color(180, 30, 30));
        return l;
    }
    private void estilizarBoton(JButton b, Color c) {
        b.setBackground(c); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}