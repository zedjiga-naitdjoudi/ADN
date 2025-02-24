package cycleADN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

public class ReplicationAnimationAA extends JPanel implements ActionListener {
    private Timer timer;
    private int replicationIndex = 0;
    private final int nucleotideWidth = 20;
    private final int nucleotideHeight = 90;
    private String userSequence = "";
    private String mRNASequence = "";
    private final Map<Character, Color> nucleotideColors;
    private JTextField userInputField;
    private JLabel aminoAcidSequenceLabel;
    private JLabel mRNASequenceLabel;

    public ReplicationAnimationAA() {
        nucleotideColors = new HashMap<>();
        nucleotideColors.put('A', Color.RED);
        nucleotideColors.put('T', Color.BLUE);
        nucleotideColors.put('G', Color.GREEN);
        nucleotideColors.put('C', Color.YELLOW);

        timer = new Timer(700, this);
        timer.start();

        userInputField = new JTextField(20);
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSequence = userInputField.getText().toUpperCase();
                mRNASequence = transcribeDNAtoRNA(userSequence);
                aminoAcidSequenceLabel.setText("Acides aminés : " + translateToAminoAcids(mRNASequence));
                mRNASequenceLabel.setText("ARNm : " + mRNASequence);
                replicationIndex = 0;
                ReplicationAnimationAA.this.repaint();
            }
        });

        aminoAcidSequenceLabel = new JLabel("Acides aminés : ");
        mRNASequenceLabel = new JLabel("ARNm : ");
        setLayout(new BorderLayout());
        add(userInputField, BorderLayout.NORTH);
        add(mRNASequenceLabel, BorderLayout.CENTER);
        add(aminoAcidSequenceLabel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 200, 200, 50));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        drawNucleotides(g2d);
        drawmRNANucleotides(g2d);
    }

    private void drawNucleotides(Graphics2D g2d) {
        for (int i = 0; i < userSequence.length(); i++) {
            char nucleotide = userSequence.charAt(i);
            Color color = nucleotideColors.get(nucleotide);
            if (color == null) continue;

            int x = i * nucleotideWidth * 3;
            int y = 100;
            Shape shape = getNucleotideShape(nucleotide, x, y);
            g2d.setColor(color);
            g2d.fill(shape);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(nucleotide), x + nucleotideWidth / 4, y + 15);

            if (i < replicationIndex) {
                char complement = getComplement(nucleotide);
                Color complementColor = nucleotideColors.get(complement);
                int newY = y + nucleotideHeight + 10;
                shape = getNucleotideShape(complement, x, newY);
                g2d.setColor(complementColor);
                g2d.fill(shape);
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(complement), x + nucleotideWidth / 4, newY + 15);
            }
        }
    }

    private void drawmRNANucleotides(Graphics2D g2d) {
        for (int i = 0; i < mRNASequence.length(); i++) {
            char nucleotide = mRNASequence.charAt(i);
            Color color = getRNAColor(nucleotide);
            if (color == null) continue;

            int x = i * nucleotideWidth * 3;
            int y = 300; // Offset for mRNA visualization
            Shape shape = getNucleotideShape(nucleotide, x, y);
            g2d.setColor(color);
            g2d.fill(shape);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(nucleotide), x + nucleotideWidth / 4, y + 15);
        }
    }

    private Color getRNAColor(char nucleotide) {
        switch (nucleotide) {
            case 'A': return Color.ORANGE;
            case 'U': return Color.PINK;
            case 'G': return Color.LIGHT_GRAY;
            case 'C': return Color.MAGENTA;
            default: return null;
        }
    }

    private Shape getNucleotideShape(char nucleotide, int x, int y) {
        switch (nucleotide) {
            case 'A':
            case 'T':
            case 'U':
                return new RoundRectangle2D.Double(x, y, nucleotideWidth, nucleotideHeight, 10, 10);
            case 'G':
            case 'C':
                return new Ellipse2D.Double(x, y, nucleotideWidth, nucleotideHeight);
            default:
                return new Rectangle2D.Double(x, y, nucleotideWidth, nucleotideHeight);
        }
    }

    private char getComplement(char nucleotide) {
        switch (nucleotide) {
            case 'A': return 'T';
            case 'T': return 'A';
            case 'C': return 'G';
            case 'G': return 'C';
            default: return ' ';
        }
    }

    private String transcribeDNAtoRNA(String dna) {
        StringBuilder rna = new StringBuilder();
        for (char nucleotide : dna.toCharArray()) {
            switch (nucleotide) {
                case 'A': rna.append('U'); break;
                case 'T': rna.append('A'); break;
                case 'C': rna.append('G'); break;
                case 'G': rna.append('C'); break;
            }
        }
        return rna.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        replicationIndex++;
        if (replicationIndex > userSequence.length()) {
            replicationIndex = 0;
        }
        repaint();
    }

    private String translateToAminoAcids(String rna) {
        StringBuilder aminoAcids = new StringBuilder();
        for (int i = 0; i <= rna.length() - 3; i += 3) {
            if (i + 3 > rna.length()) break;
            String codon = rna.substring(i, i + 3);
            String aminoAcid = translateCodonToAminoAcid(codon);
            aminoAcids.append(aminoAcid).append(" ");
        }
        return aminoAcids.toString().trim();
    }

    private static final Map<String, String> CODON_TABLE = new HashMap<>();
    static {
        CODON_TABLE.put("AUG", "Methionine");;
        CODON_TABLE.put("UUU", "Phénylalanine");
        CODON_TABLE.put("UUC", "Phénylalanine");
        CODON_TABLE.put("CUG", "Leucine");
        CODON_TABLE.put("CUA", "Leucine");
        CODON_TABLE.put("CUC", "Leucine");
        CODON_TABLE.put("UUG", "Leucine");
        CODON_TABLE.put("UUA", "Leucine");
        CODON_TABLE.put("AUU", "Isoleucine");
        CODON_TABLE.put("AUC", "Isoleucine");
        CODON_TABLE.put("AUA", "Isoleucine");
        CODON_TABLE.put("GUG", "Valine");
        CODON_TABLE.put("GUA", "Valine");
        CODON_TABLE.put("GUC", "Valine");
        CODON_TABLE.put("GUU", "Valine");
        CODON_TABLE.put("UCU", "Sérine");
        CODON_TABLE.put("UCC", "Sérine");
        CODON_TABLE.put("UCA", "Sérine");
        CODON_TABLE.put("UCG", "Sérine");
        CODON_TABLE.put("CCU", "Proline");
        CODON_TABLE.put("CCC", "Proline");
        CODON_TABLE.put("CCA", "Proline");
        CODON_TABLE.put("CCG", "Proline");
        CODON_TABLE.put("ACU", "Thréonine");
        CODON_TABLE.put("ACC", "Thréonine");
        CODON_TABLE.put("ACA", "Thréonine");
        CODON_TABLE.put("ACG", "Thréonine");
        CODON_TABLE.put("GCU", "Alanine");
        CODON_TABLE.put("GCC", "Alanine");
        CODON_TABLE.put("GCA", "Alanine");
        CODON_TABLE.put("GCG", "Alanine");
        CODON_TABLE.put("UAU", "Tyrosine");
        CODON_TABLE.put("UAC", "Tyrosine");
        CODON_TABLE.put("UAG", "Pyrrolysine");
        CODON_TABLE.put("CAU", "Histidine");
        CODON_TABLE.put("CAC", "Histidine");
        CODON_TABLE.put("CAG", "Glutamine");
        CODON_TABLE.put("CAA", "Glutamine");
        CODON_TABLE.put("AAC", "Asparagine");
        CODON_TABLE.put("AAU", "Asparagine");
        CODON_TABLE.put("AAG", "Lysine");
        CODON_TABLE.put("AAA", "Lysine");
        CODON_TABLE.put("GAC", "Acide aspartique");
        CODON_TABLE.put("GAU", "Acide aspartique");
        CODON_TABLE.put("GAG", "Acide glutamique");
        CODON_TABLE.put("GAA", "Acide glutamique");
        CODON_TABLE.put("UGC", "Cystéine");
        CODON_TABLE.put("UGU", "Cystéine");
        CODON_TABLE.put("UGA", "Sélénocystéine");
        CODON_TABLE.put("UGA", "Arginine");
        CODON_TABLE.put("CGG", "Arginine");
        CODON_TABLE.put("CGA", "Arginine");        
        CODON_TABLE.put("CGC", "Arginine");   
        CODON_TABLE.put("CGU", "Arginine");
        CODON_TABLE.put("AGG", "Arginine");
        CODON_TABLE.put("AGA", "Arginine");
        CODON_TABLE.put("AGC", "Sérine");
        
    }

    private String translateCodonToAminoAcid(String codon) {
        return CODON_TABLE.getOrDefault(codon, "Unknown");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Réplication d'ADN et Traduction en Acides Aminés");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ReplicationAnimationAA animationPanel = new ReplicationAnimationAA();
            frame.add(animationPanel);
            
            // Définir la taille de la fenêtre
            int width = 1000; // largeur souhaitée
            int height = 600; // hauteur souhaitée
            frame.setSize(width, height);
            
            // Centrer la fenêtre sur l'écran
            frame.setLocationRelativeTo(null);
            
            frame.setVisible(true);
        });
    }

}