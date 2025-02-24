package ihm;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import cycleADN.*;
import mutationADN.*;
import heritage.*;

public class GuiWindow {
	private JFrame frame;
    private JLabel imageLabel;
    private JButton btnCycleCellulaire;
    
    private JButton btnHeritage;
    private JButton btnGenomutabilite;
    private static GuiWindow instance;
	public GuiWindow() {
		
		
	    // Initialisation de la fenêtre
	    frame = new JFrame("Projet de Génie Logiciel");
	    frame.setSize(AdnSettings.FRAME_WIDTH, AdnSettings.FRAME_HEIGHT);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Chargement de l'image de fond
	    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Moi\\eclipse-workspace\\adnGLP\\src\\ressources\\wallpaper.jpg");
	    imageLabel = new JLabel(imageIcon);
	    imageLabel.setLayout(new GridBagLayout());

	    // Initialisation des composants
	    initComponents();

	    // Ajout de imageLabel à un JPanel (si nécessaire) et ensuite au cadre
	    // Dans ce cas, imageLabel contient déjà tout, donc on peut juste l'ajouter directement au frame
	    frame.add(imageLabel);

	    // Configuration finale du cadre
	    frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
	    frame.setVisible(true); // Rendre la fenêtre visible
	    
	}
	    
	   

	    
	    
	    private void initComponents() {
	    	btnCycleCellulaire = new JButton("Cycle Cellulaire");
	    	
	        btnHeritage = new JButton("Héritage");
	        btnGenomutabilite = new JButton ("Genomutabilite");
	        JButton btnAPropos = new JButton("À propos");
	        
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = GridBagConstraints.REMAINDER;
	        gbc.fill = GridBagConstraints.HORIZONTAL; // Si vous voulez que les boutons s'étendent horizontalement
	        gbc.insets = new Insets(10, 10, 10, 10); // Marges autour des composants
	        
	        btnHeritage.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Open the GenealogieInterface
	                new GenealogieInterface().setVisible(true);
	            }
	        });
	        btnAPropos.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                AboutDialog aboutDialog = new AboutDialog(frame);
	                aboutDialog.setVisible(true);
	            }
	        });
	        
	        btnCycleCellulaire.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Création d'une nouvelle instance de la nouvelle page CycleCellulaire
	                CycleCellulaire cycleCellulaire = new CycleCellulaire();
	            }
	            
	            
	            
	            
	        });
	        
	        btnGenomutabilite.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Création d'une nouvelle instance de la nouvelle page CycleCellulaire
	                Genomutabilite genomutabilite = new Genomutabilite();
	            }
	            
	            
	            
	            
	        
	            
	            
	            
	        });
	        
	        
	        Font buttonFont = new Font("Arial", Font.BOLD, 32); // Taille des boutons
	        btnCycleCellulaire.setFont(buttonFont);
	        
	        btnHeritage.setFont(buttonFont);
	        btnGenomutabilite.setFont(buttonFont);
	        btnAPropos.setFont(new Font("Arial", Font.BOLD, 16)); // Taille du bouton À propos
	        
	        // Changement de la couleur de fond des boutons
	        Color roseColor = new Color(255, 192, 203); // Rose
	        btnCycleCellulaire.setBackground(Color.decode("#E8A8E3"));
	        
	        btnHeritage.setBackground(Color.decode("#E8A8E3"));
	        btnGenomutabilite.setBackground(Color.decode("#E8A8E3"));
	        btnAPropos.setBackground(Color.WHITE); // Fond blanc pour le bouton À propos
	        
	        

	        // Changement de la couleur du texte "Bienvenue"
	        JLabel welcomeLabel = new JLabel("BIENVENUE");
	        welcomeLabel.setForeground(Color.WHITE); // Texte en blanc
	        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Taille du texte
	        
	        GridBagConstraints gbc1 = new GridBagConstraints();
	        gbc1.gridx = GridBagConstraints.REMAINDER; // Étendre sur toute la largeur
	        gbc1.gridy = 0;
	        gbc1.insets = new Insets(20, 20, 20, 20);
	        imageLabel.add(welcomeLabel, gbc1);
	        gbc1.gridy++;
	        imageLabel.add(btnCycleCellulaire, gbc1);
	        gbc1.gridy++;
	        imageLabel.add(btnHeritage, gbc1);
	        gbc1.gridy++;
	        imageLabel.add(btnGenomutabilite, gbc1);
	        gbc1.gridy++;
	        gbc1.anchor = GridBagConstraints.SOUTHEAST; // Aligner au coin inférieur droit
	        imageLabel.add(btnAPropos, gbc1); // Ajout du bouton "À propos"
	        
	        
	        
	        
	    

          /*  Création des boutons
        JButton btnCycleCellulaire = new JButton("Cycle Cellulaire");
        JButton btnSynthese = new JButton("Synthèse");
        JButton btnHeritage = new JButton("Héritage");
        JButton btnAPropos = new JButton("À propos");
        // Personnalisation des boutons
        Font buttonFont = new Font("Arial", Font.BOLD, 32); // Taille des boutons
        btnCycleCellulaire.setFont(buttonFont);
        btnSynthese.setFont(buttonFont);
        btnHeritage.setFont(buttonFont);
        btnAPropos.setFont(new Font("Arial", Font.BOLD, 16)); // Taille du bouton À propos

        // Changement de la couleur de fond des boutons
        Color roseColor = new Color(255, 192, 203); // Rose
        btnCycleCellulaire.setBackground(roseColor);
        btnSynthese.setBackground(roseColor);
        btnHeritage.setBackground(roseColor);
        btnAPropos.setBackground(Color.WHITE); // Fond blanc pour le bouton À propos

        // Changement de la couleur du texte "Bienvenue"
        JLabel welcomeLabel = new JLabel("BIENVENUE");
        welcomeLabel.setForeground(Color.WHITE); // Texte en blanc
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Taille du texte

        // Ajout des composants à l'imageLabel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.REMAINDER; // Étendre sur toute la largeur
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        imageLabel.add(welcomeLabel, gbc);
        gbc.gridy++;
        imageLabel.add(btnCycleCellulaire, gbc);
        gbc.gridy++;
        imageLabel.add(btnSynthese, gbc);
        gbc.gridy++;
        imageLabel.add(btnHeritage, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Aligner au coin inférieur droit
        imageLabel.add(btnAPropos, gbc); // Ajout du bouton "À propos"
        
        
     // Action lors du clic sur le bouton "Cycle Cellulaire"
        btnCycleCellulaire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Création d'une nouvelle instance de la nouvelle page CycleCellulaire
                CycleCellulaire cycleCellulaire = new CycleCellulaire();
            }
        });
        
     // Action lors du clic sur le bouton "À propos"
        btnAPropos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Affichage des créateurs dans une boîte de dialogue
                JOptionPane.showMessageDialog(frame, "Créateurs : AMELIA BOUKRI, NAITDJOUDI ZEDJIGA, CHEMALA FATAH");
            }
        });
        

        // Création d'un panneau pour contenir l'image et les boutons
        
        contentPanel.add(imageLabel, BorderLayout.CENTER);

        // Ajout du panneau à la fenêtre
        frame.add(contentPanel);

        // Centrer la fenêtre
        frame.setLocationRelativeTo(null);

        // Affichage de la fenêtre
        frame.setVisible(true);
	}
	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}*/
}
	    
	    public static GuiWindow getInstance() {
	        if (instance == null) {
	            instance = new GuiWindow();
	        }
	        return instance;
	    }

	    public void showWindow() {
	        if (frame != null) {
	            frame.setVisible(true);
	            int x = frame.getLocationOnScreen().x + frame.getWidth();
	            int y = frame.getLocationOnScreen().y;
	        }
	    }
	    
}
