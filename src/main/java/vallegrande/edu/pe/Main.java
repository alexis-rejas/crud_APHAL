package vallegrande.edu.pe;

import vallegrande.edu.pe.view.PantallaBienvenida;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new PantallaBienvenida().setVisible(true)
        );
    }
}