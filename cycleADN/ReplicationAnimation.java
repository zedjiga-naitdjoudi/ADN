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

public class ReplicationAnimation extends JPanel implements ActionListener {
	private JEditorPane descriptionPane;
	
	public void showDnaAnimation() {
		JFrame frame = new JFrame("Réplication d'ADN ");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    // Utilisez un BorderLayout pour séparer l'animation du texte descriptif
	    frame.setLayout(new BorderLayout());
	    frame.add(this, BorderLayout.CENTER); // Ajoute l'animation au centre
	    
	    // Emballez le JEditorPane dans un JScrollPane au cas où le contenu dépasse
	    JScrollPane scrollPane = new JScrollPane(descriptionPane);
	    scrollPane.setBorder(null); // Optionnel: enlever les bordures pour un look plus épuré
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
	    frame.add(scrollPane, BorderLayout.SOUTH); // Ajoute le texte descriptif en bas

	    frame.setSize(800, 600);
	    frame.setVisible(true);
	}

    private Timer timer;
    private int replicationIndex = 0;
    private final int nucleotideWidth = 20; 
    private final int nucleotideHeight = 90; 
    private String userSequence = ""; 
    private final Map<Character, Color> nucleotideColors;

    public ReplicationAnimation(String sequence) {
        userSequence = sequence.toUpperCase(); 
        timer = new Timer(700, this);
        timer.start();
        

        nucleotideColors = new HashMap<>();
        nucleotideColors.put('A', Color.RED);
        nucleotideColors.put('T', Color.BLUE);
        nucleotideColors.put('G', Color.GREEN);
        nucleotideColors.put('C', Color.YELLOW);

        descriptionPane = new JEditorPane();
        descriptionPane.setContentType("text/html");
        String content = "<html>\r\n"
        		+ "<body style='font-family:Arial; font-size:14px;'>\r\n"
        		+ "<h1 style='font-size:16px;'>Réplication de l'ADN</h1>\r\n"
        		+ "<p>&nbsp; La réplication de l'ADN est un processus biologique crucial qui permet à une cellule de dupliquer son ADN avant de se diviser. .</p>\r\n"
        		+ "<p>Les enzymes, telles que l'ADN polymérase, jouent un rôle crucial dans ce processus. Elles ajoutent des nucléotides complémentaires aux brins d'ADN parentaux selon les règles d'appariement des bases, où l'adénine (A) s'apparie avec la thymine (T) et la cytosine (C) avec la guanine (G).</p>\r\n"
        		+ "<p>La réplication de l'ADN est un mécanisme hautement régulé, permettant de préserver l'intégrité du génome et d'éviter les mutations qui pourraient conduire à des maladies. Ce processus met en lumière la complexité et la précision de la machinerie cellulaire nécessaire au maintien de la vie.</p>\r\n"
        		+ "</body>\r\n"
        		+ "</html>";
        descriptionPane.setText(content);
        descriptionPane.setEditable(false);
        descriptionPane.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(255, 200, 200, 50));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < userSequence.length(); i++) {
            char nucleotide = userSequence.charAt(i);
            Color color = nucleotideColors.get(nucleotide);
            if (color == null) continue;

            Shape shape;
            int x = i * nucleotideWidth * 3;
            int y = 100;

            // Choisir la forme selon le nucléotide
            switch (nucleotide) {
                case 'A':
                case 'T':
                    shape = new RoundRectangle2D.Double(x, y, nucleotideWidth, nucleotideHeight, 10, 10);
                    break;
                case 'G':
                case 'C':
                    shape = new Ellipse2D.Double(x, y, nucleotideWidth, nucleotideHeight);
                    break;
                default:
                    shape = new Rectangle2D.Double(x, y, nucleotideWidth, nucleotideHeight); // Forme de secours
                    break;
            }

            g2d.setColor(color);
            g2d.fill(shape);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(nucleotide), x + nucleotideWidth / 4, y + 15);

            if (i < replicationIndex) {
                char complement = getComplement(nucleotide);
                Color complementColor = nucleotideColors.get(complement);

                int newY = 150; // Décaler la position y pour les compléments

                // Utiliser la même logique de sélection de forme pour les compléments
                switch (complement) {
                    case 'A':
                    case 'T':
                        shape = new RoundRectangle2D.Double(x, newY, nucleotideWidth, nucleotideHeight, 10, 10);
                        break;
                    case 'G':
                    case 'C':
                        shape = new Ellipse2D.Double(x, newY, nucleotideWidth, nucleotideHeight);
                        break;
                    default:
                        shape = new Rectangle2D.Double(x, newY, nucleotideWidth, nucleotideHeight);
                        break;
                }

                g2d.setColor(complementColor);
                g2d.fill(shape);
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(complement), x + nucleotideWidth / 4, newY + 15);
            }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        replicationIndex++;
        if (replicationIndex > userSequence.length()) {
            replicationIndex = 0; 
        }
        repaint();
    }

    public static void showDnaAnimation(String sequence) {
        JFrame frame = new JFrame("Replication de l'ADN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ReplicationAnimation(sequence));
        frame.setSize(800, 300);
        frame.setVisible(true);
    }

    

    



}