package controllers;

import game.buffs.Buff;

import java.util.ArrayList;
import java.util.List;

/**
 * The BuffController class is a singleton class that controls the buffs in the game.
 */
public class BuffController {
    /**
     * The instance of the BuffController class.
     */
    private static BuffController instance;
    /**
     * Get the instance of the BuffController class.
     * @return the instance of the BuffController class
     */
    public static BuffController getInstance() {
        if (instance == null) {
            instance = new BuffController();
        }
        return instance;
    }
    /**
     * Private constructor.
     */
    private BuffController() {
    }
    /**
     * The list of buffs in the game.
     */
    private List<Buff> buffs = new ArrayList<>();
    /**
     * Tick the buffs in the game.
     */
    public void tick() {
        List<Buff> buffsCopy = new ArrayList<>(buffs);
        for (Buff buff : buffsCopy) {
            buff.tick();
            if (!buff.isActive()) {
                buff.cleanup();
                buffs.remove(buff);
            }
        }
    }
    /**
     * Add a buff to the game.
     * @param buff the buff to add
     */
    public void addBuff(Buff buff) {
        buff.setup();
        buffs.add(buff);
    }
    /**
     * Reset the instance of the BuffController class.
     */
    public static void reset() {
        instance = null;
    }
}
