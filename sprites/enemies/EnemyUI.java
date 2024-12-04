package sprites.enemies;

import entities.enemies.Enemy;
import game.Camera;
import sprites.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyUI <type extends Enemy> implements Sprite {
    static final int  HEALTH_BAR_WIDTH = 24;
    static final int HEALTH_BAR_HEIGHT = 5;

    private type enemy;
    private int health;
    private int maxHealth;

    private int xPosition;
    private int yPosition;

    public EnemyUI(type enemy){
        this.enemy = enemy;
        this.health = enemy.getHealth();
        this.maxHealth = enemy.getMaxHealth();
        this.xPosition = enemy.getXPosition();
        this.yPosition = enemy.getYPosition();
    }

    public int getHealth(){return health;}

    public int getMaxHealth(){return health;}
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getXPosition(){
        return xPosition;
    }
    @Override
    public int getYPosition(){
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

    public Enemy getEnemy(){
        return enemy;
    }

    @Override
    public void draw(Graphics g, Camera camera) {
        return;
    }

    @Override
    public void tick() {
        return;
    }
}