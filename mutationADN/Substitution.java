package mutationADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Substitution extends JPanel {
    private final Timer timer = new Timer(250, null);
    private final List<Character> originalSequence = new ArrayList<>();
    private final List<Character> mutatedSequence = new ArrayList<>();
    private final List<Character> complementSequence = new ArrayList<>();
    private final List<Character> mutatedComplementSequence = new ArrayList<>();
    private final Map<Character, Character> complementMap = new HashMap<>();
    private final Map<Character, Color> nucleotideColors = new HashMap<>();
    private boolean animationStarted = false;
    private int animationStep = 0; // 0: avant substitution, 1: après substitution
    private final JButton startButton = new JButton("Démarrer l'animation");

    public Substitution() {
        nucleotideColors.put('A', Color.RED);
        nucleotideColors.put('T', Color.BLUE);
        nucleotideColors.put('G', Color.GREEN);
        nucleotideColors.put('C', Color.YELLOW);

        complementMap.put('A', 'T');
        complementMap.put('T', 'A');
        complementMap.put('C', 'G');
        complementMap.put('G', 'C');

        // Initialisation de la séquence d'ADN
        String exampleDNA = "ATGCAT";
        for (char nucleotide : exampleDNA.toCharArray()) {
            originalSequence.add(nucleotide);
            complementSequence.add(complementMap.get(nucleotide));
        }

        // Substitution d'un nucléotide dans la séquence mutée
        mutatedSequence.addAll(originalSequence);
        mutatedSequence.set(3, 'G'); // Substitution de 'G' à la position 3
        for (char nucleotide : mutatedSequence) {
            mutatedComplementSequence.add(complementMap.get(nucleotide));
        }

        setLayout(new BorderLayout());
        add(startButton, BorderLayout.SOUTH);
        startButton.addActionListener(e -> startAnimation());
        timer.addActionListener(e -> animateSubstitution());
    }

    private void startAnimation() {
        if (!animationStarted) {
            animationStarted = true;
            timer.start();
        }
    }

    private void animateSubstitution() {
        animationStep++;
        if (animationStep > 1) {
            timer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!animationStarted) {
            drawDNA(g, originalSequence, complementSequence, 50);
        } else {
            drawDNA(g, mutatedSequence, mutatedComplementSequence, 50);
        }
    }

    private void drawDNA(Graphics g, List<Character> sequence, List<Character> complement, int startY) {
        Graphics2D g2d = (Graphics2D) g;
        int x = 20;
        for (int i = 0; i < sequence.size(); i++) {
            char nucleotide = sequence.get(i);
            char compNucleotide = complement.get(i);

            // Dessiner les nucléotides et leurs compléments
            drawNucleotide(g2d, nucleotide, x, startY, i == 3 && animationStep == 1); // Si substitution à la 4ème position
            drawNucleotide(g2d, compNucleotide, x, startY + 80, i == 3 && animationStep == 1);

            // Dessiner les liaisons entre les nucléotides
            g2d.setColor(Color.BLACK);
            g2d.drawLine(x + 15, startY + 30, x + 15, startY + 80);

            x += 40;
        }
    }

    private void drawNucleotide(Graphics2D g2d, char nucleotide, int x, int y, boolean isMutated) {
        Color color = nucleotideColors.get(nucleotide);
        Shape shape;
        if (nucleotide == 'A' || nucleotide == 'T') {
            // A et T avec une forme allongée
            shape = new RoundRectangle2D.Double(x, y, 30, isMutated ? 60 : 30, 10, 10);
        } else {
            // G et C avec une autre forme
            shape = new RoundRectangle2D.Double(x, y, 30, isMutated ? 60 : 30, 10, 10);
        }
        g2d.setColor(color);
        g2d.fill(shape);
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        g2d.drawString(String.valueOf(nucleotide), x + 10, y + (isMutated ? 45 : 20));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation de Mutation de Substitution d'ADN Avec Liaisons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new Substitution());
        frame.setVisible(true);
    }
}