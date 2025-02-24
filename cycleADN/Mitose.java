package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mitose extends JFrame implements ActionListener {
    private JButton prophaseButton = new JButton("Prophase");
    private JButton metaphaseButton = new JButton("Metaphase");
    private JButton anaphaseButton = new JButton("Anaphase");
    private JButton telophaseButton = new JButton("Telophase");
    

    public Mitose() {
        setTitle("Cycle de la Mitose");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());  // Using FlowLayout for simplicity

        // Add action listeners
        prophaseButton.addActionListener(this);
        metaphaseButton.addActionListener(this);
        anaphaseButton.addActionListener(this);
        telophaseButton.addActionListener(this);

        JLabel explanationLabel = new JLabel("<html>Ce processus comprend quatre phases principales: prophase, métaphase, anaphase, et télophase. Sélectionnez une phase</html>");
        add(explanationLabel);
        add(prophaseButton);
        add(metaphaseButton);
        add(anaphaseButton);
        add(telophaseButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == prophaseButton) {
            JFrame animationFrame = new JFrame("Prophase Animation");
            Prophase prophaseAnimation = new Prophase();
            animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            animationFrame.getContentPane().add(prophaseAnimation);
            animationFrame.pack();
            animationFrame.setLocationRelativeTo(null);
            animationFrame.setVisible(true);
        } else if (source == metaphaseButton) {
            JFrame animationFrame = new JFrame("Metaphase Animation");
            Metaphase metaphaseAnimation = new Metaphase();
            animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            animationFrame.getContentPane().add(metaphaseAnimation);
            animationFrame.pack();
            animationFrame.setLocationRelativeTo(null);
            animationFrame.setVisible(true);
        } else if (source == anaphaseButton) {
        	JFrame animationFrame = new JFrame("Anaphase Animation");
            Anaphase anaphaseAnimation = new Anaphase();
            animationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            animationFrame.getContentPane().add(anaphaseAnimation);
            animationFrame.pack();
            animationFrame.setLocationRelativeTo(null);
            animationFrame.setVisible(true);
        } else if (source == telophaseButton) {
            Telophase telophaseAnimation = new Telophase();
            telophaseAnimation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            telophaseAnimation.pack();
            telophaseAnimation.setLocationRelativeTo(null);
            telophaseAnimation.setVisible(true);
        }

         
        
    }


    public static void main(String[] args) {
        new Mitose();
    }
}
