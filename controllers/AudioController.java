package controllers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 * Controller class for the audio
 */
public class AudioController {
    /**
     * The singleton instance of the AudioController
     */
    private static AudioController instance;
    /**
     * The clip that plays the audio
     */
    private Clip clip;
    /**
     * True if the audio is muted
     */
    private boolean isMuted = false;
    /**
     * The volume control for the audio
     */
    private FloatControl volumeControl;
    /**
     * Get the singleton instance of the AudioController
     * @return the singleton instance of the AudioController
     */
    public AudioController(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * Play the audio
     */
    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    /**
     * Stop the audio
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
    /**
     * Mute the audio
     */
    public void mute() {
        if (volumeControl != null) {
            volumeControl.setValue(volumeControl.getMinimum());
            isMuted = true;
        }
    }
    /**
     * Unmute the audio
     */
    public void unmute() {
        if (volumeControl != null) {
            volumeControl.setValue(volumeControl.getMaximum());
            isMuted = false;
        }
    }
    /**
     * Get wherever the audio is muted
     * @return true if the audio is muted
     */
    public boolean isMuted() {
        return isMuted;
    }
    /**
     * Get the singleton instance of the AudioController
     * @return the singleton instance of the AudioController
     */
    private AudioController() {
    }
    /**
     * Get the singleton instance of the AudioController
     * @return the singleton instance of the AudioController
     */
    public static AudioController getInstance(String filePath) {
        if (instance == null) {
            instance = new AudioController(filePath);
        }
        return instance;
    }
}