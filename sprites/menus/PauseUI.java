package sprites.menus;

import controllers.AudioController;
import controllers.GameController;
import controllers.MovementController;
import controllers.SoundController;
import game.Camera;
import sprites.Sprite;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for the pause screen overlay
 */
public class PauseUI implements Sprite {
    private Rectangle muteButtonBounds;
    private Rectangle SFXButtonBounds;
    private Rectangle KeybindsButtonBounds;
    private boolean isMuted;
    private boolean isMuted2;
    private boolean isQwerty;

    public PauseUI() {
        muteButtonBounds = new Rectangle();
        SFXButtonBounds = new Rectangle();
        KeybindsButtonBounds = new Rectangle();
        isMuted = AudioController.getInstance("resources/audio/audio.wav").isMuted();
        isMuted2 = SoundController.getInstance().isMuted2();
        isQwerty = false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getXPosition() {
        return 0;
    }

    @Override
    public int getYPosition() {
        return 0;
    }

    @Override
    public void setXPosition(int xPosition) {
    }

    @Override
    public void setYPosition(int yPosition) {
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        String text = "PAUSED";
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, camera.getWidth(), camera.getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (camera.getWidth() - textWidth) / 2;
        int y = (camera.getHeight() - textHeight) / 3 + fm.getAscent();
        int outline = 2;
        g.setColor(Color.BLACK);
        g.drawString(text, x - outline, y - outline);
        g.drawString(text, x - outline, y + outline);
        g.drawString(text, x + outline, y - outline);
        g.drawString(text, x + outline, y + outline);
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);

        int buttonWidth = 150;
        int buttonHeight = 50;
        int centerX = (camera.getWidth() - buttonWidth) / 2;
        int centerY = (camera.getHeight() - buttonHeight) / 2;
        muteButtonBounds.setBounds(centerX, centerY, buttonWidth, buttonHeight);
        SFXButtonBounds.setBounds(centerX, centerY + 75, buttonWidth, buttonHeight);
        KeybindsButtonBounds.setBounds(centerX, centerY + 150, buttonWidth, buttonHeight);

        g.setColor(Color.WHITE);
        g.fillRect(muteButtonBounds.x, muteButtonBounds.y, muteButtonBounds.width, muteButtonBounds.height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString(isMuted ? "Unmute" : "Mute", muteButtonBounds.x + 35, muteButtonBounds.y + 35);

        g.setColor(Color.WHITE);
        g.fillRect(SFXButtonBounds.x, SFXButtonBounds.y, SFXButtonBounds.width, SFXButtonBounds.height);
        g.setColor(Color.BLACK);
        g.drawString(isMuted2 ? "SFX" : "NoSFX", SFXButtonBounds.x + 25, SFXButtonBounds.y + 35);
        g.setFont(new Font("Arial", Font.PLAIN, 24));

        g.setColor(Color.WHITE);
        g.fillRect(KeybindsButtonBounds.x, KeybindsButtonBounds.y, KeybindsButtonBounds.width, KeybindsButtonBounds.height);
        g.setColor(Color.BLACK);
        g.drawString(isQwerty ? "QWERTY" : "AZERTY", KeybindsButtonBounds.x + 25, KeybindsButtonBounds.y + 35);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
    }

    public void handleMouseClick(int X, int Y) {
        if (GameController.getInstance().getGame().isPaused() && muteButtonBounds.contains(X, Y)) {
            AudioController audioController = AudioController.getInstance("resources/audio/audio.wav");
            if (isMuted) {
                audioController.unmute();
                System.out.println("UnMuted");
            } else {
                audioController.mute();
                System.out.println("Muted");
            }
            isMuted = !isMuted;
        }
    }

    public void handleMousePressed(int X, int Y) {
        if (GameController.getInstance().getGame().isPaused() && SFXButtonBounds.contains(X, Y)) {
            SoundController soundController = SoundController.getInstance();
            if (isMuted2) {
                soundController.unmute();
                System.out.println("SFX UnMuted");
            } else {
                soundController.mute();
                System.out.println("SFX Muted");
            }
            isMuted2 = !isMuted2;
        } else if (GameController.getInstance().getGame().isPaused() && KeybindsButtonBounds.contains(X, Y)) {
            MovementController.getInstance().toggleKeybinding();
            isQwerty = !isQwerty;
            System.out.println(isQwerty ? "Switched to AZERTY" : "Switched to QWERTY");
        }
    }
}