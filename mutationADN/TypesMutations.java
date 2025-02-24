package mutationADN;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypesMutations extends JFrame implements ActionListener {
    private JPanel cardsPanel; // Panneau qui utilise le CardLayout
    private final String SUBSTITUTION = "Substitution";
    private final String INSERTION = "Insertion";
    private final String DELETION = "Deletion";

    private JPanel substitutionCard;
    private JPanel insertionCard;
    private JPanel deletionCard;

    public TypesMutations() {
    	setTitle("Types de Mutations Génétiques");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modifier cette ligne
        setLocationRelativeTo(null);
        // Initialisation des cartes (panneaux)
        substitutionCard = new Substitution();
        insertionCard = new Insertion();
        deletionCard = new Deletion();

        // Création du panneau de cartes pour les animations
        cardsPanel = new JPanel(new CardLayout());
        cardsPanel.add(substitutionCard, SUBSTITUTION);
        cardsPanel.add(insertionCard, INSERTION);
        cardsPanel.add(deletionCard, DELETION);

        // Boutons pour sélectionner le type de mutation
        JButton btnSubstitution = new JButton(SUBSTITUTION);
        JButton btnInsertion = new JButton(INSERTION);
        JButton btnDeletion = new JButton(DELETION);

        btnSubstitution.addActionListener(this);
        btnInsertion.addActionListener(this);
        btnDeletion.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSubstitution);
        buttonPanel.add(btnInsertion);
        buttonPanel.add(btnDeletion);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(cardsPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (cardsPanel.getLayout());
        cl.show(cardsPanel, e.getActionCommand());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TypesMutations().setVisible(true));
    }
}
