package heritage;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainFrame extends JFrame {
    private FamilyTreePanel treePanel;
    private JTextArea descriptionArea;

    public MainFrame() {
        setTitle("Arbre Généalogique");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 600); // Adjust size as needed
        setLayout(new BorderLayout());

        treePanel = new FamilyTreePanel();
        descriptionArea = new JTextArea("Un arbre généalogique est une représentation visuelle de la lignée d'une personne qui permet de retracer les relations en fonction de ses ancêtres communs. Visuellement semblable à un organigramme, ce type de diagramme est généralement présenté sous la forme d'une structure arborescente dont la racine est un individu.");
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground());

        add(treePanel, BorderLayout.CENTER);
        add(descriptionArea, BorderLayout.SOUTH);

        pack(); // Ajustez la taille du JFrame pour s'adapter au contenu
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}