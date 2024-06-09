package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 7;
    float volume = -20;
    public Sound(){
        soundURL[0] = getClass().getResource("/Sound/VillageTheme.wav");
        soundURL[1] = getClass().getResource("/Sound/MenuTheme.wav");
        soundURL[2] = getClass().getResource("/Sound/BarrelsBreakSE.wav");
    }

    public void setFile(int i){
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            // PASS A VALUE TO THIS CLIP SO THAT WE CAN CHANGE THE VOLUME
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                checkVolume();

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
            case 1: volume = -70f;
                break;
            case 2: volume = -60f;
                break;
            case 3:volume = -50f;
                break;
            case 4: volume = -40f;
                break;
            case 5: volume = -30f;
                break;
            case 6: volume = -20f;
                break;
            case 7: volume = -10f;
                break;
            case 8: volume = 0f;
                break;
            case 9: volume = 2f;
                break;
            case 10: volume = 4f;
                break;
            case 11: volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}

