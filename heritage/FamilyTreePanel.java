package heritage;


import javax.swing.*;
import java.awt.*;

public class FamilyTreePanel extends JPanel {
    private static final int BOX_WIDTH = 60;
    private static final int BOX_HEIGHT = 30;
    private static final int VERTICAL_GAP = 80;
    private static final int HORIZONTAL_GAP = 20;
    private static final int LEVEL_GAP = 180;

    public FamilyTreePanel() {
        setPreferredSize(new Dimension(1200, 400)); // Adjust size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the family tree starting from the grandparents
        drawFamilyTree(g);
    }

    private void drawFamilyTree(Graphics g) {
        int midX = getWidth() / 2;
        int midY = 30;

        // Grandparents level
        drawPerson(g, midX, midY, "1", "Grandfather");
        drawPerson(g, midX, midY + BOX_HEIGHT, "2", "Grandmother");

        // Parents level
        int parentX = midX - LEVEL_GAP;
        drawPerson(g, parentX, midY + VERTICAL_GAP, "3", "Uncle");
        drawPerson(g, midX, midY + VERTICAL_GAP, "4", "Father");
        drawPerson(g, midX + LEVEL_GAP, midY + VERTICAL_GAP, "5", "Aunt");

        // Connect parents to grandparents
        drawLineBetweenPeople(g, midX, midY, parentX, midY + VERTICAL_GAP);
        drawLineBetweenPeople(g, midX, midY, midX, midY + VERTICAL_GAP);
        drawLineBetweenPeople(g, midX, midY, midX + LEVEL_GAP, midY + VERTICAL_GAP);

        // Children level
        int childX = parentX - 2 * HORIZONTAL_GAP;
        for (int i = 0; i < 3; i++) {  // Uncle's children
            drawPerson(g, childX, midY + 2 * VERTICAL_GAP, Integer.toString(6 + i), "");
            drawLineBetweenPeople(g, parentX, midY + VERTICAL_GAP, childX, midY + 2 * VERTICAL_GAP);
            childX += HORIZONTAL_GAP;
        }
        childX = midX - HORIZONTAL_GAP;  // Father's children
        for (int i = 0; i < 2; i++) {
            drawPerson(g, childX, midY + 2 * VERTICAL_GAP, Integer.toString(9 + i), "");
            drawLineBetweenPeople(g, midX, midY + VERTICAL_GAP, childX, midY + 2 * VERTICAL_GAP);
            childX += HORIZONTAL_GAP;
        }
        childX = midX + LEVEL_GAP - HORIZONTAL_GAP;  // Aunt's children
        for (int i = 0; i < 2; i++) {
            drawPerson(g, childX, midY + 2 * VERTICAL_GAP, Integer.toString(11 + i), "");
            drawLineBetweenPeople(g, midX + LEVEL_GAP, midY + VERTICAL_GAP, childX, midY + 2 * VERTICAL_GAP);
            childX += HORIZONTAL_GAP;
        }
    }

    private void drawPerson(Graphics g, int x, int y, String number, String label) {
        g.setColor(Color.BLACK);
        g.drawRect(x - BOX_WIDTH / 2, y - BOX_HEIGHT / 2, BOX_WIDTH, BOX_HEIGHT);
        g.drawString(number + " " + label, x - g.getFontMetrics().stringWidth(number + " " + label) / 2, y + 5);
    }

    private void drawLineBetweenPeople(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1 + BOX_HEIGHT / 2, x2, y2 - BOX_HEIGHT / 2);
    }
}