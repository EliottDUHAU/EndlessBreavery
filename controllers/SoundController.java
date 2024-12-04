package controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for the sound
 */
public class SoundController {
    /**
     * The singleton instance of the SoundController
     */
    private static SoundController instance;
    /**
     * Map of the sould effects mapped to their names
     */
    private Map<String, Clip> clips = new HashMap<>();
    /**
     * True if the audio is muted
     */
    private boolean isMuted2 = false;
    /**
     * Private constructor for the SoundController
     */
    private SoundController() {
        loadSound("slash", "resources/sounds/slash.wav");
        loadSound("heal", "resources/sounds/heal.wav");
        loadSound("death", "resources/sounds/death.wav");
        loadSound("damage", "resources/sounds/damage.wav");
    }
    /**
     * Get the singleton instance of the SoundController
     * @return the singleton instance of the SoundController
     */
    public static SoundController getInstance() {
        if (instance == null) {
            instance = new SoundController();
        }
        return instance;
    }
    /**
     * Load a sound effect
     * @param name the name of the sound effect
     * @param filePath the file path of the sound effect
     */
    public void loadSound(String name, String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clips.put(name, clip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Play a sound effect
     * @param name the name of the sound effect
     */
    public void play(String name) {
        if (isMuted2) return;
        Clip clip = clips.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    /**
     * Mute the audio
     */
    public void mute() {
        for (Clip clip : clips.values()) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getMinimum());
        }
        isMuted2 = true;
    }
    /**
     * Unmute the audio
     */
    public void unmute() {
        for (Clip clip : clips.values()) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getMaximum());
        }
        isMuted2 = false;
    }
    /**
     * Get whether the audio is muted
     * @return true if the audio is muted
     */
    public boolean isMuted2() {
        return isMuted2;
    }
}
