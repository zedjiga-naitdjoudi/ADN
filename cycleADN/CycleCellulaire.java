package cycleADN;

import ihm.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import cycleADN.CycleCellulaire;
import ihm.*;

// Classe représentant la nouvelle page CycleCellulaire
public class CycleCellulaire extends JFrame {
    public CycleCellulaire() {
        // Code pour initialiser la nouvelle page
        setTitle("Cycle Cellulaire");
        setSize(AdnSettings.FRAME_WIDTH, AdnSettings.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme seulement cette fenêtre lorsqu'elle est fermée
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        
        // Chargement de l'image de fond
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Moi\\eclipse-workspace\\adnGLP\\src\\ressources\\wallpaper.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        
        
        JTextPane descriptionPane = new JTextPane();
        descriptionPane.setContentType("text/html"); // Permet d'utiliser du HTML pour le formatage
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false); // Rend le JTextPane transparent
        
        // HTML contenu pour justifier le texte et inclure une image
        String htmlContent = "<html><body style='text-align: justify; font-family: Arial; margin: 10px; font-size: 14px;'>"
                + "<h1 style='font-size:16px;'>Cycle Cellulaire, C'est quoi ?? </h1>\r\n"
                + "<p>&nbsp; Le cycle cellulaire est un processus complexe qui régule la division et la reproduction des cellules. "
                + "Il débute par l'interphase, une période de croissance et de préparation où l'ADN est répliqué. Ensuite, la cellule "
                + "entre dans la phase de division, ou mitose, qui comprend plusieurs étapes telles que la prophase, la métaphase, "
                + "l'anaphase et la télophase, au cours desquelles les chromosomes se séparent et se déplacent vers les pôles opposés "
                + "de la cellule. La mitose est suivie de la cytocinèse, où la cellule se divise physiquement en deux cellules filles "
                + "distinctes, chacune contenant un jeu complet de chromosomes. Ce processus est crucial pour la croissance, le développement "
                + "et la régénération des tissus dans les organismes multicellulaires, et il est rigoureusement régulé pour éviter les "
                + "erreurs génétiques et les dysfonctionnements cellulaires qui pourraient conduire à des maladies telles que le cancer.</p>"
                + "</body></html>";
        descriptionPane.setText(htmlContent);
        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(scrollPane, BorderLayout.CENTER);
        
        
        
        
        // Panel contenant le bouton pour retourner en arrière
        JPanel buttonPanel = new JPanel();
        JButton retourButton = new JButton("Accueil");
        retourButton.setPreferredSize(new Dimension(100, 30)); // Taille du bouton réduite
        retourButton.setBackground(Color.WHITE); // Fond blanc pour le bouton
        retourButton.setForeground(Color.BLACK); // Texte noir pour le bouton
        // Action à effectuer lors du clic sur le bouton Accueil
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer cette fenêtre
                
            }
        });
        buttonPanel.add(retourButton);
        
        // Panel global
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(descriptionPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        backgroundLabel.add(contentPanel, BorderLayout.CENTER);
        
        
        
        
        
        // Création de la barre latérale à gauche de l'interface
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        
        JButton btn1 = new JButton("Phase G1");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame animationFrame = new JFrame("Phase G1 Animation");
                animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                animationFrame.add(new G1PhaseAnimation()); // Ajoutez votre JPanel d'animation
                animationFrame.setSize(550, 550); // Taille de la fenêtre, ajustez selon le besoin
                animationFrame.setLocationRelativeTo(null); // Centre la fenêtre
                animationFrame.setVisible(true);
            }
        });

        JButton btn2 = new JButton("Synthèse (S)");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField(60);
                int result = JOptionPane.showConfirmDialog(null, textField, "Entrez une chaîne d'ADN, RAPPEL : Une sequence correcte contient "
                		+ "les nucleotides A C G T", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String sequence = textField.getText();
                    if (sequence != null && !sequence.isEmpty()) {
                        new ReplicationAnimation(sequence).showDnaAnimation(); // Créez et montrez l'animation
                    }
                }
            }
        });

        JButton btn3 = new JButton("Phase G2");
     // Dans votre classe principale ou gestionnaire d'événements de bouton
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new G2PhaseAnimation(); // Créer et afficher la fenêtre d'animation pour la phase G2
            }
        });

        JButton btn4 = new JButton("Mitose");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SwingUtilities.invokeLater(() -> new Mitose());
            }
        });
        

        JButton btn6 = new JButton("Meiose");
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openMeioseInterface();
            }
            private void openMeioseInterface() {
                SwingUtilities.invokeLater(() -> {
                    Meiose meioseInterface = new Meiose();
                    meioseInterface.setVisible(true);
                });
            }

        });
        

      

        
        JButton btn5 = new JButton("ARN et AcidesA");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Animation de Réplication d'ADN");
                ReplicationAnimationAA replicationAnimation = new ReplicationAnimationAA();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(replicationAnimation);
                
                // Définir la taille de la fenêtre
                int width = 800; // largeur 
                int height = 600; // hauteur 
                frame.setSize(width, height);
                
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });



        
        Dimension btnDimension = new Dimension(180, 60); // Dimension personnalisée pour les boutons

        addSideButton(sidePanel, btn1, btnDimension);
        addSideButton(sidePanel, btn2, btnDimension);
        addSideButton(sidePanel, btn3, btnDimension);
        addSideButton(sidePanel, btn4, btnDimension);
        addSideButton(sidePanel, btn6, btnDimension);
        addSideButton(sidePanel, btn5, btnDimension);
        
     
        
        // Changement du fond des boutons de la barre latérale en blanc
        btn1.setBackground(Color.WHITE);
        btn2.setBackground(Color.WHITE);
        btn3.setBackground(Color.WHITE);
        btn4.setBackground(Color.WHITE);
        btn6.setBackground(Color.WHITE);
        btn5.setBackground(Color.WHITE);
        
        // Changement de la couleur du texte des boutons de la barre latérale en noir
        btn1.setForeground(Color.BLACK);
        btn2.setForeground(Color.BLACK);
        btn3.setForeground(Color.BLACK);
        btn4.setForeground(Color.BLACK);
        btn6.setForeground(Color.BLACK);
        btn5.setForeground(Color.BLACK);
        
       
        
     // Ajout d'un peu d'espace entre les boutons
        sidePanel.add(Box.createVerticalGlue());

        add(sidePanel, BorderLayout.WEST);

        setVisible(true);
        
        // Ajout de la barre latérale à la fenêtre
        backgroundLabel.add(sidePanel, BorderLayout.WEST);
        
        // Ajout de l'image de fond à la fenêtre principale
        add(backgroundLabel);
        
        setVisible(true);
    } 
    
 
    
    
    private void addSideButton(JPanel sidePanel, JButton button, Dimension dimension) {
        // Centrer le bouton dans un JPanel avec FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        button.setPreferredSize(dimension);
        buttonPanel.add(button);
        sidePanel.add(buttonPanel);
    }
    
  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CycleCellulaire();
        });
    }
}

    
   
