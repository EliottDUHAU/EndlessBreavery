package controllers;

import entities.enemies.projectiles.Fireball;
import entities.enemies.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the projectiles
 */
public class ProjectileController {
    /**
     * The singleton instance of the ProjectileController
     */
    private static ProjectileController instance;
    /**
     * List of projectiles in the game
     */
    private final List<Projectile> projectiles;

    /**
     * Private constructor for the ProjectileController
     */
    private ProjectileController(){
        projectiles = new ArrayList<>();
    }
    /**
     * Reset the singleton instance of the ProjectileController
     */
    public static void reset(){
        instance = null;
    }
    /**
     * Get the singleton instance of the ProjectileController
     * @return the singleton instance of the ProjectileController
     */
    public static ProjectileController getInstance(){
        if(instance == null){
            instance = new ProjectileController();
        }
        return instance;
    }
    /**
     * Tick the projectiles
     */
    public void tick(){
        for(Projectile projectile : new ArrayList<>(projectiles)){
            projectile.tick();
            if (!projectile.isAlive()){
                projectiles.remove(projectile);
            }
        }
    }

    /**
     * Add a projectile to the list of projectiles
     * @param projectile the projectile to add
     */
    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
        // cap the number of projectiles to 250, fifo
        while (projectiles.size() > 250){
            projectiles.removeFirst();
        }
    }
    /**
     * Get the list of projectiles in the game
     * @return the list of projectiles in the game
     */
    public List<Projectile> getProjectiles(){
        return new ArrayList<>(projectiles);
    }
}