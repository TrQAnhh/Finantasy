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

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class credit extends JPanel implements ActionListener {

    GamePanel gp;
    Graphics2D g2;
    Timer creditTimer = new Timer(3,this);
    String text;
    int textY = 610;
    JFrame frame;
    JLabel label;
    Image image;
    public credit(String is, String text) throws IOException{
        frame = new JFrame("END GAME");
        image = ImageIO.read(new File(is));
        frame.setSize(1300,1300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(this);
        this.text = text;
        creditTimer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(image,0,0,this);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int y = textY;
        for(String line : text.split("\n")){
            
        int stringLength = (int)g2d.getFontMetrics().getStringBounds(line, g2d).getWidth();
        int x = getWidth()/2 - stringLength/2;
        g2d.drawString(line,x,y+=30);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        textY--;
        if(textY < -1150){
            creditTimer.stop();
            frame.dispose();
        }
        repaint();
        
    }
}
