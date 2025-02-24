package heritage;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FamilyTreeUI extends JPanel {
    private FamilyTree familyTree;
    private Timer timer;

    public FamilyTreeUI(FamilyTree familyTree) {
        this.familyTree = familyTree;
        setLayout(new BorderLayout()); // Setting layout to BorderLayout
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        timer = new Timer(1000, this::actionPerformed);
        timer.start();

        // Adding the button to add a new person
        JButton addButton = new JButton("Ajouter Personne");
        addButton.addActionListener(this::addPerson);
        this.add(addButton, BorderLayout.SOUTH);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the family tree starting from the grandparents
        Person grandFather = familyTree.getGrandFather();
        Person grandMother = familyTree.getGrandMother();
        int midX = getWidth() / 2;
        int startY = 50;
        drawPerson(g, grandFather, midX - 50, startY);
        drawPerson(g, grandMother, midX + 50, startY);
        g.drawLine(midX - 50 + 20, startY + 20, midX + 50 - 20, startY + 20); // Line connecting them

        // Assuming all children are from both grandparents
        drawChildren(g, grandFather, midX, startY + 100, 100);
    }

    private void drawPerson(Graphics g, Person person, int x, int y) {
        int circleDiameter = 40;
        g.setColor(Color.BLUE);
        g.fillOval(x - circleDiameter / 2, y - circleDiameter / 2, circleDiameter, circleDiameter);
        g.setColor(Color.BLACK);
        g.drawString(person.getName(), x - g.getFontMetrics().stringWidth(person.getName()) / 2, y);
    }

    private void drawChildren(Graphics g, Person parent, int x, int y, int yStep) {
        List<Person> children = parent.getChildren();
        int numChildren = children.size();
        int xStep = 150;
        int childX = x - (numChildren - 1) * xStep / 2;
        for (Person child : children) {
            if (child == familyTree.getGrandMother()) continue; // Skip drawing grandmother here
            g.drawLine(x, y - yStep + 20, childX, y);
            drawPerson(g, child, childX, y);
            drawChildren(g, child, childX, y + yStep, yStep);
            childX += xStep;
        }
    }

    private void addPerson(ActionEvent e) {
        String childName = "Personne " + (familyTree.getGrandFather().getChildren().size() + 1);
        familyTree.getGrandFather().addChild(new Person(childName));
        repaint();
    }

    private void actionPerformed(ActionEvent e) {
        repaint();
    }
}
