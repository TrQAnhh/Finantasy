package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gamePanel;
    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }    
    public void saveConfif() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // Full screen
            if(gamePanel.fullScreenOn == true) {
                bw.write("On");
            }
            if(gamePanel.fullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();

            // Music volume
            bw.write(String.valueOf(gamePanel.music.volumeScale));
            bw.newLine();

            // SE volume
            bw.write(String.valueOf(gamePanel.se.volumeScale));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfig() {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));

            String s = br.readLine();

            // Full Screen
            if(s.equals("On")) {
                gamePanel.fullScreenOn = true;
            }
            if(s.equals("Off")) {
                gamePanel.fullScreenOn = false;
            }
            //Music volume
            s = br.readLine();
            gamePanel.music.volumeScale = Integer.parseInt(s);

            //SE volume
            s = br.readLine();
            gamePanel.se.volumeScale = Integer.parseInt(s);

            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
