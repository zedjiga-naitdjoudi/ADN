package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Metaphase extends JPanel implements ActionListener {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CELL_DIAMETER = 500;
    private static final int NUCLEUS_DIAMETER = 350;
    private static final int CHROMOSOME_COUNT = 10;
    private static final int CHROMOSOME_LENGTH = 30; // Longueur accrue des chromosomes
    private static final int CHROMOSOME_WIDTH = 5;   // Largeur accrue des chromosomes
    private static final int STEP = 5;

    private Ellipse2D.Double cell;
    private Ellipse2D.Double nucleus;
    private Line2D.Double equatorialPlate;
    private List<Chromosome> chromosomes = new ArrayList<>();
    private Timer timer;
    private Random random = new Random();

    public Metaphase() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);
        initCellStructure();
        JButton button = new JButton("Align Chromosomes");
        button.addActionListener(this);
        this.add(button);
        timer = new Timer(50, e -> moveChromosomesToEquatorialPlate());
    }

    private void initCellStructure() {
        int centerX = FRAME_WIDTH / 2;
        int centerY = FRAME_HEIGHT / 2;
        cell = new Ellipse2D.Double(centerX - CELL_DIAMETER / 2, centerY - CELL_DIAMETER / 2, CELL_DIAMETER, CELL_DIAMETER);
        nucleus = new Ellipse2D.Double(centerX - NUCLEUS_DIAMETER / 2, centerY - NUCLEUS_DIAMETER / 2, NUCLEUS_DIAMETER, NUCLEUS_DIAMETER);
        equatorialPlate = new Line2D.Double(centerX - NUCLEUS_DIAMETER / 2, centerY, centerX + NUCLEUS_DIAMETER / 2, centerY);

        // Position chromosomes randomly within the nucleus initially
        for (int i = 0; i < CHROMOSOME_COUNT; i++) {
            int x = centerX - NUCLEUS_DIAMETER / 4 + random.nextInt(NUCLEUS_DIAMETER / 2);
            int y = centerY - NUCLEUS_DIAMETER / 4 + random.nextInt(NUCLEUS_DIAMETER / 2);
            chromosomes.add(new Chromosome(x, y));
        }
    }

    private void moveChromosomesToEquatorialPlate() {
        int centerY = (int) nucleus.getCenterY();
        boolean allChromosomesAligned = true;

        for (Chromosome chromosome : chromosomes) {
            if (chromosome.y != centerY) {
                chromosome.y += (chromosome.y < centerY) ? STEP : -STEP;
                allChromosomesAligned = false;
            }
        }

        if (allChromosomesAligned) {
            timer.stop(); // Stop the timer when all chromosomes are aligned
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw cell
        g2.setColor(new Color(255, 165, 0, 100)); // Couleur orange avec une transparence
        g2.fill(cell);
        // Draw nucleus
        g2.setColor(new Color(0, 150, 136));
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2.fill(nucleus);
        // Draw nucleus in dashed line
      /*  g2.setColor(new Color(0, 150, 136));
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2.draw(nucleus); */

        // Draw equatorial plate
        g2.setColor(new Color(255, 69, 0)); // Orange color
        g2.setStroke(new BasicStroke(5)); // Make the equatorial plate visible
        g2.draw(equatorialPlate);

        // Draw chromosomes
        g2.setColor(Color.RED);
        for (Chromosome chromosome : chromosomes) {
            g2.fillRect(chromosome.x - CHROMOSOME_WIDTH / 2, chromosome.y - CHROMOSOME_LENGTH / 2, CHROMOSOME_WIDTH, CHROMOSOME_LENGTH);
        }
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Metaphase Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Metaphase());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class Chromosome {
        int x, y;

        public Chromosome(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}