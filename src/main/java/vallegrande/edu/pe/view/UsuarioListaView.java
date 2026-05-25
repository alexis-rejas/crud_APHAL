package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.UsuarioController;
import vallegrande.edu.pe.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioListaView extends JFrame {
    private final UsuarioController ctrl = new UsuarioController();
    private DefaultTableModel modelo;
    private JTable tabla;

    public UsuarioListaView() {
        setTitle("Usuarios — Listado");
        setSize(850, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Tabla
        String[] cols = {"ID", "Nombre", "Correo", "Teléfono", "Asunto", "Mensaje"};
        modelo = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(74, 107, 42));
        tabla.getTableHeader().setForeground(Color.WHITE);

        cargarDatos();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Panel botones
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelBtns.setBackground(new Color(245, 248, 240));

        JButton btnNuevo   = btn("➕ Nuevo",   new Color(74, 107, 42));
        JButton btnEditar  = btn("✏️ Editar",  new Color(52, 90, 160));
        JButton btnEliminar= btn("🗑️ Eliminar",new Color(160, 40, 40));
        JButton btnVolver  = btn("← Volver",   new Color(100, 100, 100));

        panelBtns.add(btnNuevo);
        panelBtns.add(btnEditar);
        panelBtns.add(btnEliminar);
        panelBtns.add(btnVolver);
        add(panelBtns, BorderLayout.SOUTH);

        // Acciones
        btnNuevo.addActionListener(e -> {
            new UsuarioFormView(this, null).setVisible(true);
            cargarDatos();
        });

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un registro."); return; }
            Usuario u = getUsuarioDeFila(fila);
            new UsuarioFormView(this, u).setVisible(true);
            cargarDatos();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un registro."); return; }
            int id = (int) modelo.getValueAt(fila, 0);
            int conf = JOptionPane.showConfirmDialog(this, "¿Eliminar registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                ctrl.eliminar(id);
                cargarDatos();
            }
        });

        btnVolver.addActionListener(e -> {
            new PantallaBienvenida().setVisible(true);
            dispose();
        });
    }

    public void cargarDatos() {
        modelo.setRowCount(0);
        List<Usuario> lista = ctrl.listar();
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                    u.getId(), u.getNombre(), u.getCorreo(),
                    u.getTelefono(), u.getAsunto(), u.getMensaje()
            });
        }
    }

    private Usuario getUsuarioDeFila(int fila) {
        return new Usuario(
                (int)    modelo.getValueAt(fila, 0),
                (String) modelo.getValueAt(fila, 1),
                (String) modelo.getValueAt(fila, 2),
                (String) modelo.getValueAt(fila, 3),
                (String) modelo.getValueAt(fila, 4),
                (String) modelo.getValueAt(fila, 5)
        );
    }

    private JButton btn(String t, Color c) {
        JButton b = new JButton(t);
        b.setBackground(c); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }
}