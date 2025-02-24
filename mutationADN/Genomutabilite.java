package mutationADN;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Genomutabilite extends JFrame {
    public Genomutabilite() {
        setTitle("Genomutabilite");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        JTextArea descriptionArea = new JTextArea("QU’est-ce qu’une mutation ?\n\n"
                + "Modification de la séquence de nucléotides de l’ADN...");
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(150, getHeight()));
        sidePanel.setBackground(new Color(240, 240, 240));
        sidePanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btn1 = new JButton("Types de mutations");
        JButton btn2 = new JButton("Étude de cas");
        
        Dimension btnDimension = new Dimension(120, 30);
        btn1.setPreferredSize(btnDimension);
        btn2.setPreferredSize(btnDimension);
        

        sidePanel.add(btn1);
        btn1.addActionListener(e -> {
            TypesMutations typemut = new TypesMutations();
            typemut.setVisible(true);
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("DNA Mutation Visualizer");
                DNAMutationVisualizer visualizer = new DNAMutationVisualizer();

                JButton detectMutationButton = new JButton("Detect Mutation");
                detectMutationButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        visualizer.detectMutation();
                    }
                });

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(visualizer, BorderLayout.CENTER);
                frame.add(detectMutationButton, BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });


        sidePanel.add(btn2);
       

        JButton accueilButton = new JButton("Accueil");
        accueilButton.setPreferredSize(new Dimension(100, 30));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidePanel, BorderLayout.WEST);
        getContentPane().add(descriptionPanel, BorderLayout.CENTER);
        getContentPane().add(accueilButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Genomutabilite();
    }
}
