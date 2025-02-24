package heritage;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import heritage.MainFrame;  // Assurez-vous que ceci est correctement importé

public class GenealogieInterface extends JFrame {
    public GenealogieInterface() {
        setTitle("Interface Généalogique");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        JTextArea descriptionArea = new JTextArea("Explication sur l'arbre généalogique...\n\n"
                + "Cliquez sur un bouton pour afficher ou interagir avec l'arbre généalogique.");
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(2, 1, 10, 10));
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));
        sidePanel.setBackground(new Color(240, 240, 240));

        JButton btnExplanation = new JButton("Explications");
        btnExplanation.addActionListener(e -> openMainFrame());

        JButton btnTree = new JButton("Arbre Généalogique");
        btnTree.addActionListener(e -> openFamilyTreeUI());

        sidePanel.add(btnExplanation);
        sidePanel.add(btnTree);
        

        JButton accueilButton = new JButton("Accueil");
        accueilButton.addActionListener(e -> returnToHome());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidePanel, BorderLayout.WEST);
        getContentPane().add(descriptionPanel, BorderLayout.CENTER);
        getContentPane().add(accueilButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openMainFrame() {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    private void openFamilyTreeUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Arbre Généalogique");
            FamilyTree familyTree = new FamilyTree("Grand-père", "Grand-mère");
            FamilyTreeUI ui = new FamilyTreeUI(familyTree);
            frame.add(ui);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void returnToHome() {
        dispose(); // Close this window
    }

    public static void main(String[] args) {
        new GenealogieInterface();
    }
}
