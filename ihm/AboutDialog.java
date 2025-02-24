package ihm;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame parent) {
        super(parent, "À propos", true); // true pour modal
        setupUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void setupUI() {
        // Configuration du layout
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(Color.decode("#C6E8A8")); 

        // Informations sur les créateurs, l'encadreur et l'année
        String htmlText = "<html><body style='text-align: center;'>"
                + "<h2>Créateurs :</h2>"
                + "<p>&nbsp; AMELIA BOUKRI, NAITDJOUDI ZEDJIGA, CHEMALA FATAH  &nbsp;  </p>"
                + "<h2>Encadreur :</h2>"
                + "<p>Tianxiao LIU</p>"
                + "<h2>Année de réalisation :</h2>"
                + "<p>2024</p>"
                + "</body></html>";

        JLabel infoLabel = new JLabel(htmlText);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(infoLabel, BorderLayout.CENTER);

        // Bouton pour fermer le dialogue
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());
        closeButton.setBackground(Color.WHITE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.setBackground(Color.decode("#C6E8A8")); // Assortir au fond
        add(buttonPanel, BorderLayout.PAGE_END);
    }
}
