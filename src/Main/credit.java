package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class credit extends JPanel implements ActionListener {

    Timer creditTimer = new Timer(15, this);  // Increased delay for slower scrolling
    String text;
    int textY = 610;
    JFrame frame;
    Image image;

    public credit(String imagePath, String text) throws IOException {
        frame = new JFrame("END GAME");
        frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(this);
        this.text = text;
        creditTimer.start();

        // Load the image
        image = ImageIO.read(new File(imagePath));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the image
        if (image != null) {
            g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        // Set the font and color for the text
        g2d.setFont(new Font("alagard", Font.ITALIC, 30));
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw the text
        int y = textY;
        for (String line : text.split("\n")) {
            int stringLength = (int) g2d.getFontMetrics().getStringBounds(line, g2d).getWidth();
            int x = getWidth() / 2 - stringLength / 2;
            g2d.drawString(line, x, y += 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        textY--;
        if (textY < -1000) {
            creditTimer.stop();
            frame.dispose();
        }
        repaint();
        
    }
}
