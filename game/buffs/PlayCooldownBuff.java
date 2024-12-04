package game.buffs;

import controllers.CardController;
/**
 * A buff that modifies the play cooldown of cards
 */
public class PlayCooldownBuff implements Buff {
    /**
     * The number of ticks that the buff has been active
     */
    private int tick = 0;
    /**
     * The duration of the buff
     */
    private final int duration;
    /**
     * The multiplier for the play cooldown
     */
    private final double multiplier;
    /**
     * Create a new PlayCooldownBuff
     * @param duration the duration of the buff
     * @param multiplier the multiplier for the play cooldown
     */
    public PlayCooldownBuff(int duration, double multiplier) {
        this.multiplier = multiplier;
        this.duration = duration;
    }
    /**
     * Get the duration of the buff
     * @return the duration of the buff
     */
    @Override
    public void tick() {
        tick++;
    }

    /**
     * Setup the buff
     */
    @Override
    public void setup() {
        CardController cardController = CardController.getInstance();
        System.out.println("Setting play cooldown from " + cardController.getPlayCooldown() +  " to " + cardController.getPlayCooldown() * multiplier);
        cardController.setPlayCooldown((int) (cardController.getPlayCooldown() * multiplier));
    }
    /**
     * Cleanup the buff
     */
    @Override
    public void cleanup() {
        CardController cardController = CardController.getInstance();
        System.out.println("Setting play cooldown from " + cardController.getPlayCooldown() +  " to " + cardController.getPlayCooldown() / multiplier);
        cardController.setPlayCooldown((int) (cardController.getPlayCooldown() / multiplier));
    }
    /**
     * Check if the buff is active
     * @return if the buff is active
     */
    @Override
    public boolean isActive() {
        return tick < duration;
    }
}
