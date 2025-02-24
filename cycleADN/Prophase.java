package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Prophase extends JPanel implements ActionListener {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CELL_DIAMETER = 500;
    private static final int NUCLEUS_DIAMETER = 300;
    private static final int CHROMOSOME_COUNT = 10;
    private Ellipse2D.Double cell;
    private Ellipse2D.Double nucleus;
    private Chromosome[] chromosomes = new Chromosome[CHROMOSOME_COUNT];
    private Random random = new Random();

    public Prophase() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);
        initCellNucleusAndChromosomes();
        JButton button = new JButton("Animate Chromosomes");
        button.addActionListener(this);
        this.add(button);
    }

    private void initCellNucleusAndChromosomes() {
        int centerX = FRAME_WIDTH / 2;
        int centerY = FRAME_HEIGHT / 2;
        cell = new Ellipse2D.Double(centerX - CELL_DIAMETER / 2, centerY - CELL_DIAMETER / 2, CELL_DIAMETER, CELL_DIAMETER);
        nucleus = new Ellipse2D.Double(centerX - NUCLEUS_DIAMETER / 2, centerY - NUCLEUS_DIAMETER / 2, NUCLEUS_DIAMETER, NUCLEUS_DIAMETER);
        for (int i = 0; i < CHROMOSOME_COUNT; i++) {
            int x = centerX + random.nextInt(NUCLEUS_DIAMETER / 2) - NUCLEUS_DIAMETER / 4;
            int y = centerY + random.nextInt(NUCLEUS_DIAMETER / 2) - NUCLEUS_DIAMETER / 4;
            chromosomes[i] = new Chromosome(x, y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw cell
        g2.setColor(new Color(255, 165, 0, 100));
        g2.fill(cell);

        // Draw nucleus
        g2.setColor(new Color(240, 128, 128)); // Light coral color
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{10}, 0));
        g2.draw(nucleus);

        // Draw chromosomes
        g2.setColor(Color.RED);
        for (Chromosome chromosome : chromosomes) {
            chromosome.draw(g2);
        }

        // Draw spindle fibers
        g2.setColor(new Color(60, 179, 113)); // Medium sea green
        for (int i = 0; i < CHROMOSOME_COUNT; i++) {
            g2.drawLine(FRAME_WIDTH / 2, FRAME_HEIGHT / 2, chromosomes[i].x, chromosomes[i].y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Chromosome chromosome : chromosomes) {
            chromosome.updatePosition(FRAME_WIDTH / 2, FRAME_HEIGHT / 2, NUCLEUS_DIAMETER / 2);
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Prophase Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Prophase());
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

        public void updatePosition(int centerX, int centerY, int radius) {
            this.x = centerX + random.nextInt(radius) - radius / 2;
            this.y = centerY + random.nextInt(radius) - radius / 2;
        }

        public void draw(Graphics2D g2) {
            // Drawing chromosome as a "X" shape
            g2.drawLine(x - 5, y - 5, x + 5, y + 5);
            g2.drawLine(x + 5, y - 5, x - 5, y + 5);
        }
    }
}