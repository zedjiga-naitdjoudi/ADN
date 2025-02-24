package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Anaphase extends JPanel implements ActionListener {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CELL_DIAMETER = 500;
    private static final int NUCLEUS_DIAMETER = 350; // Noyau agrandi
    private static final int CHROMOSOME_COUNT = 10;
    private static final int PLATE_THICKNESS = 10; // Épaisseur accrue pour visibilité
    private static final int CHROMATID_LENGTH = 20;
    private static final int STEP = 5;

    private Ellipse2D.Double cell;
    private Ellipse2D.Double nucleus;
    private Line2D.Double equatorialPlate; // Ligne pour la plaque équatoriale
    private List<Chromosome> chromosomes = new ArrayList<>();
    private Timer timer;

    public Anaphase() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);
        initCellStructure();
        JButton button = new JButton("Start Anaphase");
        button.addActionListener(this);
        this.add(button);
        timer = new Timer(50, e -> moveChromatids());
    }

    private void initCellStructure() {
        int centerX = FRAME_WIDTH / 2;
        int centerY = FRAME_HEIGHT / 2;
        cell = new Ellipse2D.Double(centerX - CELL_DIAMETER / 2, centerY - CELL_DIAMETER / 2, CELL_DIAMETER, CELL_DIAMETER);
        nucleus = new Ellipse2D.Double(centerX - NUCLEUS_DIAMETER / 2, centerY - NUCLEUS_DIAMETER / 2, NUCLEUS_DIAMETER, NUCLEUS_DIAMETER);
        
        // Définir la plaque équatoriale
        equatorialPlate = new Line2D.Double(centerX - NUCLEUS_DIAMETER / 2, centerY, centerX + NUCLEUS_DIAMETER / 2, centerY);

        // Position chromosomes at the equatorial plate
        for (int i = 0; i < CHROMOSOME_COUNT; i++) {
            int xPosition = centerX - (NUCLEUS_DIAMETER / 2) + NUCLEUS_DIAMETER / CHROMOSOME_COUNT * i + NUCLEUS_DIAMETER / CHROMOSOME_COUNT / 2;
            chromosomes.add(new Chromosome(xPosition, centerY));
        }
    }

    private void moveChromatids() {
        for (Chromosome chromosome : chromosomes) {
            if (chromosome.yTop > nucleus.y) {
                chromosome.yTop -= STEP;
            }
            if (chromosome.yBottom < nucleus.y + nucleus.height) {
                chromosome.yBottom += STEP;
            }
        }

        // Check if all chromatids have reached the nucleus boundary
        boolean allChromatidsAtBoundary = true;
        for (Chromosome chromosome : chromosomes) {
            if (chromosome.yTop > nucleus.y || chromosome.yBottom < nucleus.y + nucleus.height) {
                allChromatidsAtBoundary = false;
                break;
            }
        }

        if (allChromatidsAtBoundary) {
            timer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw cell
        g2.setColor(new Color(255, 165, 0, 100));
        g2.fill(cell);

        // Draw nucleus
        g2.setColor(new Color(0, 150, 136));
        g2.fill(nucleus);

        // Draw equatorial plate
        g2.setColor(new Color(255, 69, 0)); // Couleur rouge-orangé pour la plaque
        g2.setStroke(new BasicStroke(PLATE_THICKNESS));
        g2.draw(equatorialPlate);

        // Draw spindle fibers
        g2.setColor(Color.GRAY);
        int centerX = (int) nucleus.getCenterX();
        for (Chromosome chromosome : chromosomes) {
            g2.drawLine(centerX, (int) nucleus.getY(), chromosome.x, chromosome.yTop);
            g2.drawLine(centerX, (int) (nucleus.getY() + nucleus.height), chromosome.x, chromosome.yBottom);
        }

        // Draw chromosomes or separated chromatids
        g2.setColor(Color.RED);
        for (Chromosome chromosome : chromosomes) {
            g2.drawLine(chromosome.x, chromosome.yTop, chromosome.x, chromosome.yTop + CHROMATID_LENGTH);
            g2.drawLine(chromosome.x, chromosome.yBottom, chromosome.x, chromosome.yBottom - CHROMATID_LENGTH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Anaphase Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Anaphase());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class Chromosome {
        int x;
        int yTop, yBottom;

        public Chromosome(int x, int centerY) {
            this.x = x;
            this.yTop = centerY - CHROMATID_LENGTH / 2;
            this.yBottom = centerY + CHROMATID_LENGTH / 2;
        }
    }
}