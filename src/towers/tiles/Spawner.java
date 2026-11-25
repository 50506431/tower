package towers.tiles;

import engine.EngineState;
import engine.game.Position;
import engine.pathing.PathFinder;
import engine.pathing.ShortestGridPathFinder;
import engine.timing.RepeatingTimer;
import engine.timing.TickTimer;
import towers.GameState;
import towers.SpriteGallery;
import towers.enemies.Enemy;
import towers.enemies.Knight;

/**
 * A tile that keeps spawning knights while the game runs.
 */
public class Spawner extends AbstractTile {

    /** How many ticks between each spawn. */
    static final int SPAWN_INTERVAL = 100;

    /** Counts down the time until the next spawn. */
    private TickTimer timer;

    /**
     * Creates a spawner at the given position.
     *
     * @param position where the spawner is
     */
    public Spawner(Position position) {
        super(position);
        setSprite(SpriteGallery.dirt.getSprite("camp"));
        this.timer = new RepeatingTimer(SPAWN_INTERVAL);
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        super.tick(engine, game);
        timer.tick();
        Enemy knight;
        PathFinder path;
        path = new ShortestGridPathFinder(game.getPathingMap());
        if (timer.isFinished()) {
            knight = new Knight(screen(), path);
            game.spawnEnemy(knight);
        }
    }
}
