package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/Sound/VillageTheme.wav");
        soundURL[1] = getClass().getResource("/Sound/MenuTheme.wav");
    }

    public void setFile(int i){
        try {
            if (soundURL[i] == null) {
                throw new IOException("Resource URL is null");
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        if (clip != null) {
            clip.start();
        }
    }

    public void loop(){
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(){
        if (clip != null) {
            clip.stop();
        }
    }
}
