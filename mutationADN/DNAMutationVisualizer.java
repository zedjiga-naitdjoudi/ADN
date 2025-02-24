package mutationADN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.GeneralPath;

public class DNAMutationVisualizer extends JPanel {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BASE_PAIR_COUNT = 15;
    private static final int BASE_PAIR_GAP = 30;
    private static final int BASE_PAIR_WIDTH = 20;
    private boolean mutationDetected = false;
    private int mutationAt = 7; // Prédéfinir une mutation à la 7ème paire
    private Color helixColor = new Color(106, 90, 205); // Couleur de l'hélice modifiée

    public DNAMutationVisualizer() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDNA((Graphics2D) g);
        if (mutationDetected) {
            highlightMutation((Graphics2D) g, mutationAt);
        }
    }

    private void drawDNA(Graphics2D g2d) {
        int centerX = WINDOW_WIDTH / 2;
        int startY = 50;
        GeneralPath leftHelix = new GeneralPath();
        GeneralPath rightHelix = new GeneralPath();

        // Setup for drawing the helixes
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(helixColor);

        // Draw left helix
        leftHelix.moveTo(centerX - BASE_PAIR_WIDTH, startY);
        for (int i = 0; i < BASE_PAIR_COUNT; i++) {
            if (i != mutationAt) {
                // Curve for normal segment
                leftHelix.curveTo(
                    centerX - 3 * BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * i + BASE_PAIR_GAP / 2,
                    centerX - 3 * BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * (i + 1) - BASE_PAIR_GAP / 2,
                    centerX - BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * (i + 1)
                );
            } else {
                // Straight line for mutated segment
                leftHelix.lineTo(centerX - BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * (i + 1));
            }
        }

        // Draw right helix
        rightHelix.moveTo(centerX + BASE_PAIR_WIDTH, startY);
        for (int i = 0; i < BASE_PAIR_COUNT; i++) {
            rightHelix.curveTo(
                centerX + 3 * BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * i + BASE_PAIR_GAP / 2,
                centerX + 3 * BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * (i + 1) - BASE_PAIR_GAP / 2,
                centerX + BASE_PAIR_WIDTH, startY + BASE_PAIR_GAP * (i + 1)
            );
        }

        g2d.draw(leftHelix);
        g2d.draw(rightHelix);

        // Draw base pairs
        for (int i = 0; i < BASE_PAIR_COUNT; i++) {
            int yPosition = startY + i * BASE_PAIR_GAP;
            if (i == mutationAt) {
                g2d.setColor(Color.RED);
            } else {
                // Differentiate base pair colors
                g2d.setColor(i % 2 == 0 ? Color.GREEN : Color.BLUE);
            }
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(centerX - BASE_PAIR_WIDTH, yPosition, centerX + BASE_PAIR_WIDTH, yPosition);
            // Draw nucleotide labels
            drawNucleotideLabels(g2d, centerX, yPosition, i);
        }
    }

    private void drawNucleotideLabels(Graphics2D g2d, int centerX, int yPosition, int index) {
        String basePair = index % 2 == 0 ? "A-T" : "C-G";
        if (index == mutationAt) {
            basePair = "Mut";
        }
        g2d.setColor(Color.BLACK);
        g2d.drawString(basePair, centerX - BASE_PAIR_WIDTH - 20, yPosition + 5);
    }

    private void highlightMutation(Graphics2D g2d, int mutationIndex) {
        int centerX = WINDOW_WIDTH / 2;
        int yPosition = 50 + mutationIndex * BASE_PAIR_GAP;

        // Draw a circle to mark the mutation
        g2d.setColor(Color.RED);
        g2d.fillOval(centerX - 5, yPosition - 5, 10, 10);

        // Draw an arrow pointing to the mutation
        drawArrow(g2d, centerX, yPosition - 20, centerX, yPosition - 10);

        // Draw the text "Mutation Detected"
        g2d.drawString("Mutation détectée", centerX - BASE_PAIR_WIDTH - 100, yPosition);
    }

    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(Color.RED);
        g2d.drawLine(x1, y1, x2, y2);

        // Arrow head
        int[] xPoints = {x2, x2 - 5, x2 + 5};
        int[] yPoints = {y2, y2 - 5, y2 - 5};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    public void detectMutation() {
        mutationDetected = true;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DNA Mutation Visualizer");
        DNAMutationVisualizer visualizer = new DNAMutationVisualizer();

        JButton detectButton = new JButton("Detect Mutation");
        detectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizer.detectMutation();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(visualizer, BorderLayout.CENTER);
        frame.add(detectButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}