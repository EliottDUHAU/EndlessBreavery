package sprites.cards;

import game.Camera;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class CardSprite implements Sprite {
    private int xPosition;
    private int yPosition;
    private double scale;
    private BufferedImage image;

    @Override
    public int getWidth() {
        return (int) (image.getWidth() * scale);
    }

    @Override
    public int getHeight() {
        return (int) (image.getHeight() * scale);
    }

    @Override
    public int getXPosition() {
        return xPosition;
    }

    @Override
    public int getYPosition() {
        return yPosition;
    }

    @Override
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    @Override
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        g.drawImage(image, xPosition, yPosition, getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        // do nothing
    }

    public CardSprite(String imagePath, double scale) {
        try {image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.out.println("Could not load card sprite: " + imagePath);
            e.printStackTrace();
        }
        this.scale = scale;
    }
}
