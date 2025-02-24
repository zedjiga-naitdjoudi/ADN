package ihm;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Animation extends JPanel implements Runnable {

    private static final Color BACK_COLOR =  new Color(255, 255, 255);
    private Color currentForeColor = new Color(255, 255, 255);//initial
    private ArrayList<Point> points;

    Animation(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        long now;
        double timeElapsed = 0.0;
        double FPS = 40.0;

        while (true) {
            now = System.nanoTime();
            timeElapsed += ((now - start) / 1_000_000_000.0) * FPS;
            start = System.nanoTime();

            if (timeElapsed > 1) {
                updateColor(); // Mettre à jour la couleur ici
                update();
                repaint();
                timeElapsed--;
            }
            sleepThread();
        }
    }

    private void updateColor() {
        // changement de couleur basé sur le temps
        
        long time = System.currentTimeMillis();
        int red = (int) (Math.sin(time * 0.0005) * 127 + 128);
        int green = (int) (Math.sin(time * 0.0007) * 127 + 128);
        int blue = (int) (Math.sin(time * 0.0006) * 127 + 128);
        currentForeColor = new Color(red, green, blue);
    }

    private void update() {
        points.forEach(Point::increment);
    }

    private void sleepThread() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if ("Linux".equals(System.getProperty("os.name"))) {
            Toolkit.getDefaultToolkit().sync();
        }

        setBackground(BACK_COLOR);
        drawDNA(graphics);
    }

    private void drawDNA(Graphics graphics) {
        drawBridges(graphics);
        drawRidges(graphics);
        drawNodes(graphics);
    }

    private void drawRidges(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(currentForeColor);

        for (int i = 0; i < points.size() - 1; i++) {
            if (Math.abs(points.get(i).getY() - points.get(i + 1).getY()) <= 20) {
                g.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
            }
        }
    }

    private void drawBridges(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(currentForeColor);

        points.forEach(p1 -> points.stream().filter(p2 -> p1.getY() == p2.getY()).forEachOrdered(p2 -> g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY())));
    }

    private void drawNodes(Graphics graphics) {
        points.forEach(point -> point.draw(graphics, currentForeColor));
    }
}
