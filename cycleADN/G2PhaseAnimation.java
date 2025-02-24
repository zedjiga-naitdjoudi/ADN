package cycleADN;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class G2PhaseAnimation extends JFrame {
    private AnimationPanel animationPanel = new AnimationPanel();
    private Timer timer;
    private JEditorPane descriptionPane;

    public G2PhaseAnimation() {
        super("Phase G2 de la croissance cellulaire");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); // BorderLayout pour une disposition simple
        
        descriptionPane = new JEditorPane();
        descriptionPane.setContentType("text/html");
        String htmlContent = "<html><body style='font-family:Arial; font-size:14px;'>"
                + "<h1 style='font-size:18px;'>Phase G2 de la Croissance Cellulaire</h1>"
                + "<p>&nbsp; La phase G2 est la dernière étape de l'interphase dans le cycle cellulaire où la cellule achève sa croissance et se prépare à la division. Pendant cette phase, des vérifications sont effectuées pour s'assurer que l'ADN a été correctement répliqué sans erreurs, et la cellule synthétise les protéines nécessaires à la mitose.</p>"
                + "</body></html>";
        descriptionPane.setText(htmlContent);
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false);

        getContentPane().add(descriptionPane, BorderLayout.NORTH);
        getContentPane().add(animationPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setVisible(true);

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animationPanel.updateCentrosomes();
                animationPanel.repaint();
            }
        });
        timer.start();
    }

    class AnimationPanel extends JPanel {
        int cellSize = 300; // Diamètre initial de la cellule
        int centrosomeSize = 20; // Taille des centrosomes
        int centrosomeOffset = 0; // Décalage initial des centrosomes par rapport au centre du noyau

        public AnimationPanel() {
            setPreferredSize(new Dimension(800, 600));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellX = (getWidth() - cellSize) / 2;
            int cellY = (getHeight() - cellSize) / 2;

            g.setColor(Color.GREEN);
            g.fillOval(cellX, cellY, cellSize, cellSize);

            g.setColor(Color.BLUE);
            g.fillOval(cellX + cellSize / 4, cellY + cellSize / 4, cellSize / 2, cellSize / 2);

            // Les centrosomes se déplacent vers le bord du noyau
            g.setColor(Color.RED);
            g.fillRect(cellX + cellSize / 4 - centrosomeSize / 2 + centrosomeOffset, cellY + cellSize / 4 - centrosomeSize, centrosomeSize, centrosomeSize);
            g.fillRect(cellX + 3 * cellSize / 4 - centrosomeSize / 2 - centrosomeOffset, cellY + 3 * cellSize / 4, centrosomeSize, centrosomeSize);

            // Annotations
            g.setColor(Color.BLACK);
            g.drawString("Cellule", cellX + cellSize / 2, cellY - 10);
            g.drawString("Noyau", cellX + cellSize / 2, cellY + cellSize / 2);
            g.drawString("Centrosome", cellX + cellSize / 4 - centrosomeSize / 2 + centrosomeOffset, cellY + cellSize / 4 - centrosomeSize - 10);
            g.drawString("Centrosome", cellX + 3 * cellSize / 4 - centrosomeSize / 2 - centrosomeOffset, cellY + 3 * cellSize / 4 + centrosomeSize + 20);
        }

        void updateCentrosomes() {
            // Simuler le déplacement des centrosomes
            if (centrosomeOffset < cellSize / 4 - centrosomeSize) {
                centrosomeOffset += 2; 
            }
        }
    }
    
    
}
