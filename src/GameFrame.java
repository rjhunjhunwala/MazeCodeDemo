package islandsofviolence;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Raycasting game "Islands of Violence
 *
 */
public final class GameFrame extends JFrame {
public JPanel p;
    GameFrame() throws IOException {

        super("Can you escape?");

        p = new GamePanel();

        this.add(p);

        this.pack();

        this.setBackground(new Color(255, 255, 255));

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.addKeyListener(new Controller());
    this.setLocationRelativeTo(null);
    }
}
