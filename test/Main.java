package test;
import javax.swing.SwingUtilities;

import ihm.*;

public class Main {
    public static void main(String[] args) {
        // Appel de la classe WindowGUI pour lancer l'interface
        GuiWindow window = new GuiWindow();
        //window.setVisible(true);
        SwingUtilities.invokeLater(() -> new DNAAnimation());
    }
}
