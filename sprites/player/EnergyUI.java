package sprites.player;

import game.Camera;

import java.awt.*;

public class EnergyUI extends LoadingBar {

    public EnergyUI(int xPosition, int yPosition, int duratioon) {
        super("resources/ui/EnergyBarFull.png", "resources/ui/EnergyBarEmpty.png",
                duratioon, xPosition, yPosition, 1.8);
    }
    public EnergyUI(int xPosition, int yPosition, int duratioon, double scale) {
        super("resources/ui/EnergyBarFull.png", "resources/ui/EnergyBarEmpty.png",
                duratioon, xPosition, yPosition, scale);
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        if (!isDone()) super.draw(g, camera);
    }

}
