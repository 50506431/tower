package towers.resources;

import engine.EngineState;
import engine.game.Tickable;
import engine.timing.RepeatingTimer;
import engine.timing.TickTimer;

/**
 * A counter of resources that slowly increases over time.
 */
public class Resource implements Tickable {

    /** How many ticks between each resource increase. */
    public static final int RESOURCE_INTERVAL = 400;

    /** How many resources are added each time. */
    public static final int RESOURCE_INCREMENT = 1;

    /** How many resources the player starts with. */
    public static final int DEFAULT_STARTING_RESOURCES = 4;

    /** The lowest the resources can go. */
    public static final int DEFAULT_MIN_RESOURCES = 0;

    /** The highest the resources can go. */
    public static final int DEFAULT_MAX_RESOURCES = 9;

    /** The lowest the resources can go. */
    private int min = DEFAULT_MIN_RESOURCES;

    /** The highest the resources can go. */
    private int max = DEFAULT_MAX_RESOURCES;

    /** How many resources the player has right now. */
    private int current = DEFAULT_STARTING_RESOURCES;

    /** Counts down the time until the next increase. */
    private TickTimer timer;

    /**
     * Creates a resource counter with the given start and limits.
     * @param initial how many resources to start with
     * @param min the lowest the resources can go
     * @param max the highest the resources can go
     */
    public Resource(int initial, int min, int max) {
        this.max = max;
        this.min = min;
        this.current = initial;
        this.timer = new RepeatingTimer(RESOURCE_INTERVAL);
        if (initial < this.min) {
            this.current = min;
        }
        if (initial > max) {
            this.current = max;
        }
    }

    /**
     * Returns how many resources the player has.
     *
     * @return the current resource count
     */
    public int getCurrent() {
        return current;
    }

    /**
     * Takes the given amount away, without going below the minimum.
     *
     * @param cost how much to spend
     */
    public void spend(int cost) {
        this.current = Math.max(this.min, current - cost);
    }

    @Override
    public void tick(EngineState state) {
        timer.tick();
        if (timer.isFinished()) {
            if(this.current<=4){
            this.current = Math.min(max, current + RESOURCE_INCREMENT*2);}
            else {
                this.current = Math.min(max, current + RESOURCE_INCREMENT);
            }

        }
    }
}
