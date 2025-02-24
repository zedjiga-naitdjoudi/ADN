package cycleADN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class G1PhaseAnimation extends JPanel implements ActionListener {
    private Timer timer;
    private int cellSize = 50; 
    private final int MAX_SIZE = 200; 
    private final int DELAY = 50; 
    

    public G1PhaseAnimation() {
        setLayout(new BorderLayout()); // BorderLayout pour ce JPanel
        timer = new Timer(DELAY, this);
        timer.start();
        
        
     // conteneur principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY); 


        

        // panneau de texte descriptif
        JTextPane descriptionPane = new JTextPane();
        descriptionPane.setContentType("text/html");  
        String content = "<html><body style='padding-top: 10px; font-family: Arial; margin: 10px;font-size:14px;'>" + 
                         "<h1 style='font-size:16px;'> &nbsp; Description de la Phase G1:</h1>\r\n" + // Utiliser <b> pour le texte en gras
                         "La phase G1 est caractérisée par la croissance cellulaire et la préparation à la réplication de l'ADN. " +
                         "Cette phase est cruciale pour le bon déroulement du cycle cellulaire." +
                         "</body></html>";
        descriptionPane.setText(content);
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false);


        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setPreferredSize(new Dimension(400, 100)); 
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Cellule mère
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(centerX - cellSize, centerY - cellSize, cellSize * 2, cellSize * 2);

        // Cellule fille
        g.setColor(Color.GREEN);
        int daughterSize = cellSize / 2;
        g.fillOval(centerX - daughterSize + 10, centerY - daughterSize + 10, daughterSize * 2, daughterSize * 2);

        // Chromosomes (Bleus)
        g.setColor(Color.BLUE);
        int chromosomeSize = daughterSize / 4;
        g.fillOval(centerX - chromosomeSize + 15, centerY - chromosomeSize + 15, chromosomeSize * 2, chromosomeSize * 2);

        // Chromosomes supplémentaires (Jaunes) 
        g.setColor(Color.YELLOW);
        int offset = 20; // Décalage par rapport au centre pour la disposition des chromosomes
        int numChromosomes = 4; // Nombre de chromosomes à représenter
        int chromosomeWidth = 5, chromosomeHeight = 20; // Dimensions des chromosomes
        for (int i = 0; i < numChromosomes; i++) {
            // Calculer la position de chaque chromosome pour les disperser autour du centre
            int x = centerX + (int) (offset * Math.cos(2 * Math.PI * i / numChromosomes)) - chromosomeWidth / 2;
            int y = centerY + (int) (offset * Math.sin(2 * Math.PI * i / numChromosomes)) - chromosomeHeight / 2;
            
            // Dessiner chaque chromosome
            g.fillOval(x, y, chromosomeWidth, chromosomeHeight);
        }

        //petits points pour représenter les nucléotides ou autres détails
        g.setColor(Color.RED);
        for(int i = 0; i < 5; i++) {
            g.fillOval(centerX - cellSize/2 + i*20, centerY, 5, 5);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (cellSize < MAX_SIZE) {
            cellSize++;
            repaint();
        } else {
            timer.stop(); // Arrêtez le timer une fois la taille maximale atteinte
        }
    }
    
    
}
