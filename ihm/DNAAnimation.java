package ihm;

 
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;


public class DNAAnimation extends JFrame {

    private static final int WIDTH = AdnSettings.DNA_WIDTH;
    private static final int HEIGHT = AdnSettings.DNA_HEIGHT;
    int x, y;

    public DNAAnimation() {
        ArrayList<Point> points = createPoints();
        Animation animation = createAnimation(points);
        setWindowProperties();
        startAnimation(animation);
    }

    public ArrayList<Point> createPoints() {
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < 18; i++) {
            points.add(new Point(WIDTH / 2, 40 + (i * 20), i * 5));
        }

        for (int i = 0; i < 18; i++) {
            points.add(new Point(WIDTH / 2, 40 + (i * 20), 30 + (i * 5)));
        }

        return points;
    }

    private Animation createAnimation(ArrayList<Point> points) {
        Animation animation = new Animation(points);
        Container cp = getContentPane();
        cp.add(animation);
        animation.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        return animation;
    }

    private void setWindowProperties() {
        setResizable(false);
        pack();
        setTitle("DNA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(39, 38);
    }

    private void startAnimation(Animation animation) {
        Thread th = new Thread(animation);
        th.start();
    }

  

}

