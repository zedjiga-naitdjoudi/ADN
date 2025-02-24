package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellDivisionAnimation extends JFrame {
    private final int INITIAL_CELL_DISTANCE = 120;  // Distance initiale augmentée
    private final int CELL_RADIUS = 90;            // Rayon des cellules
    private final int NUCLEUS_RADIUS = 30;         // Rayon des noyaux

    private JButton startButton;
    private Timer animationTimer;
    private boolean isAnimating = false;
    private int offset = 0;

    public CellDivisionAnimation() {
        setTitle("Animation de division cellulaire");
        setSize(600, 400);  // Fenêtre agrandie
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startButton = new JButton("Animer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAnimating) {
                    isAnimating = true;
                    animationTimer.start();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(buttonPanel, BorderLayout.SOUTH);

        animationTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offset < INITIAL_CELL_DISTANCE) {
                    offset += 3;  // Vitesse de séparation augmentée
                    repaint();
                } else {
                    animationTimer.stop();
                    isAnimating = false;
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Position des cellules ajustée pour l'espacement
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int cell1X = centerX - CELL_RADIUS * 2 - 10;
        int cell2X = centerX + CELL_RADIUS * 2 - 10;

        drawCell(g, cell1X, centerY - CELL_RADIUS / 2, offset, "Cellule mère 1");
        drawCell(g, cell2X, centerY - CELL_RADIUS / 2, -offset, "Cellule mère 2");
    }

    private void drawCell(Graphics g, int x, int y, int movementOffset, String label) {
        g.setColor(Color.GREEN);
        // Deux cellules filles pour chaque cellule mère
        g.fillOval(x + movementOffset, y, CELL_RADIUS, CELL_RADIUS);
        g.fillOval(x - movementOffset, y, CELL_RADIUS, CELL_RADIUS);

        // Dessin des noyaux dans chaque cellule
        g.setColor(Color.BLUE);
        g.fillOval(x + movementOffset + 20, y + 20, NUCLEUS_RADIUS, NUCLEUS_RADIUS);
        g.fillOval(x - movementOffset + 20, y + 20, NUCLEUS_RADIUS, NUCLEUS_RADIUS);

        // Chromosomes
        g.setColor(Color.RED);
        g.fillRect(x + movementOffset + 25, y + 25, 20, 3);
        g.fillRect(x - movementOffset + 25, y + 25, 20, 3);

        // Étiquettes pour les cellules mères
        g.setColor(Color.BLACK);
        g.drawString(label, x, y - 10);

        // Dessin des flèches pour indiquer la direction de la division
        g.drawLine(x + CELL_RADIUS / 2, y + CELL_RADIUS / 2, x + CELL_RADIUS / 2 + movementOffset, y + CELL_RADIUS / 2);
        g.drawLine(x - CELL_RADIUS / 2, y + CELL_RADIUS / 2, x - CELL_RADIUS / 2 - movementOffset, y + CELL_RADIUS / 2);
        drawArrow(g, x + CELL_RADIUS / 2 + movementOffset, y + CELL_RADIUS / 2);
        drawArrow(g, x - CELL_RADIUS / 2 - movementOffset, y + CELL_RADIUS / 2);
    }

    // Méthode pour dessiner des flèches
    private void drawArrow(Graphics g, int x, int y) {
        g.drawLine(x, y, x - 5, y - 5);
        g.drawLine(x, y, x - 5, y + 5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CellDivisionAnimation::new);
    }
}