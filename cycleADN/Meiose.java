package cycleADN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Meiose extends JFrame {
    public Meiose() {
        setTitle("Phases de la Méiose");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // 4 boutons en colonne

        JButton prophaseButton = new JButton("Prophase");
        JButton metaphaseButton = new JButton("Métaphase");
        JButton anaphaseButton = new JButton("Anaphase");
        JButton telophaseButton = new JButton("Télophase");

        prophaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProphaseAnimation();
            }
        });
        
        metaphaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMetaphaseAnimation();
            }
        });

        anaphaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnaphaseAnimation();
            }
        });

        telophaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTelophaseAnimation(); // Modification pour ouvrir l'animation de la division cellulaire
            }
        });

        buttonPanel.add(prophaseButton);
        buttonPanel.add(metaphaseButton);
        buttonPanel.add(anaphaseButton);
        buttonPanel.add(telophaseButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }

    private void showTelophaseAnimation() {
        CellDivisionAnimation divisionAnimation = new CellDivisionAnimation();
        divisionAnimation.setVisible(true);
    }

    private void showProphaseAnimation() {
        JFrame prophaseFrame = new JFrame("Prophase Animation");
        Prophase prophaseAnimation = new Prophase();
        prophaseFrame.add(prophaseAnimation);
        prophaseFrame.pack();
        prophaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prophaseFrame.setLocationRelativeTo(null);
        prophaseFrame.setVisible(true);
    }

    private void showMetaphaseAnimation() {
        JFrame metaphaseFrame = new JFrame("Métaphase Animation");
        Metaphase metaphaseAnimation = new Metaphase();
        metaphaseFrame.add(metaphaseAnimation);
        metaphaseFrame.pack();
        metaphaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        metaphaseFrame.setLocationRelativeTo(null);
        metaphaseFrame.setVisible(true);
    }

    private void showAnaphaseAnimation() {
        JFrame anaphaseFrame = new JFrame("Anaphase Animation");
        Anaphase anaphaseAnimation = new Anaphase();
        anaphaseFrame.add(anaphaseAnimation);
        anaphaseFrame.pack();
        anaphaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        anaphaseFrame.setLocationRelativeTo(null);
        anaphaseFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Meiose interfaceMeiose = new Meiose();
            interfaceMeiose.setVisible(true);
        });
    }
}
