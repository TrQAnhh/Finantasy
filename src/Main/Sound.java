package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
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
    public void checkVolume() {
        switch (volumeScale) {
            case 0: volume = -80f;
                break;
            case 1: volume = -20f;
                break;
            case 2: volume = -12f;
                break;
            case 3:volume = -5f;
                break;
            case 4: volume = 1f;
                break;
            case 5: volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
