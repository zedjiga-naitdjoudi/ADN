package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Telophase extends JFrame {
    private DetailedAnimationPanel animationPanel = new DetailedAnimationPanel();
    private Timer timer;
    private final int delay = 60;

    public Telophase() {
        setTitle("Cytokinese");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); 

        JEditorPane descriptionPane = new JEditorPane();
        descriptionPane.setContentType("text/html");
        String htmlContent = "<html><body style='font-family:Arial; font-size:14px;'>"
            + "<h1 style='font-size:16px;'>Cytokinese</h1>"
            + "<p> &nbsp; La cytokinèse est l'étape finale de la division cellulaire, où la cellule se divise physiquement en deux cellules filles après la mitose. "
            + "Cette animation illustre la progression de la cytokinèse, montrant la séparation des chromosomes et la formation de deux noyaux distincts.</p>"
            + "</body></html>";
        descriptionPane.setText(htmlContent);
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false);

        getContentPane().add(descriptionPane, BorderLayout.NORTH);
        getContentPane().add(animationPanel, BorderLayout.CENTER);

        setSize(800, 600);
        timer = new Timer(delay, e -> animationPanel.updateAnimation());
        timer.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Telophase frame = new Telophase();
            frame.setVisible(true);
        });
    }

    class DetailedAnimationPanel extends JPanel {
        private double splitProgress = 0.0; // Progression de la cytokinèse de 0.0 à 1.0

        public DetailedAnimationPanel() {
            setPreferredSize(new Dimension(800, 600));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawCellWithChromosomes(g);
        }

        private void drawCellWithChromosomes(Graphics g) {
            // Dessiner la cellule initiale et les chromosomes avant la division
            int cellWidth = getWidth() - 200;
            int cellHeight = getHeight() - 250;
            int cellX = (getWidth() - cellWidth) / 2;
            int cellY = (getHeight() - cellHeight) / 2;

            // Division de la cellule
            int splitWidth = (int) (cellWidth * splitProgress / 2);
            g.setColor(Color.GREEN);
            g.fillOval(cellX - splitWidth, cellY, cellWidth / 2, cellHeight);
            g.fillOval(cellX + cellWidth / 2 + splitWidth, cellY, cellWidth / 2, cellHeight);

            // Dessiner les chromosomes
            g.setColor(Color.BLUE);
            int chromosomeWidth = 10, chromosomeHeight = 40;
            int chromosomeX = cellX + cellWidth / 4 - chromosomeWidth / 2;
            int chromosomeY = cellY + cellHeight / 2 - chromosomeHeight / 2;
            g.fillRect(chromosomeX - splitWidth, chromosomeY, chromosomeWidth, chromosomeHeight);
            g.fillRect(chromosomeX + cellWidth / 2 + splitWidth, chromosomeY, chromosomeWidth, chromosomeHeight);
        }

        public void updateAnimation() {
            if (splitProgress < 1.0) {
                splitProgress += 0.01;
                repaint(); // Redessiner le panneau pour mettre à jour l'animation
            } else {
                timer.stop(); // Arrêter l'animation une fois la cytokinèse complète
            }
        }
    }
}
