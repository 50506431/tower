package towers.enemies;

import engine.EngineState;
import engine.game.Position;
import engine.input.MouseState;
import engine.pathing.PathFinder;
import engine.pathing.ShortestGridPathFinder;
import engine.renderer.Renderable;
import engine.renderer.RenderableGroup;
import towers.GameState;
import towers.GameTickable;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of all the enemies in the game and moves them each tick.
 */
public class EnemyManager implements GameTickable, RenderableGroup {

    /** The enemies currently alive in the game. */
    private final List<Enemy> list;

    /**
     * create a new Arraylist for knights
     */
    public EnemyManager() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns every enemy currently alive.
     *
     * @return the list of enemies
     */
    public List<Enemy> getEnemies() {
        return new ArrayList<>(list);
    }

    /**
     * Adds a new enemy to the game.
     *
     * @param enemy the enemy to add
     */
    public void spawnEnemy(Enemy enemy) {
        list.add(enemy);
    }

    /**
     * Returns the enemy closest to the given position.
     *
     * @param position the position to measure from
     * @return the nearest enemy, or null if there are none
     */
    public Enemy getNearest(Position position) {
        Enemy near = null;
        double d = Double.POSITIVE_INFINITY;
        for (Enemy enemy : list) {
            if (enemy.screen().distance(position) <= d) {
                d = enemy.screen().distance(position);
                near = enemy;
            }
        }
        return near;
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        cleanup();
        for (Enemy enemy : list) {
            enemy.tick(engine, game);
        }

    }

    @Override
    public List<Renderable> render() {
        List<Renderable> renderlist = new ArrayList<>();
        for (Enemy enemy : list) {
            renderlist.addAll(enemy.render());
        }
        return renderlist;
    }

    /** Removes any enemies that are marked for removal. */
    public void cleanup() {
        list.removeIf(enemy -> enemy.isMarkedForRemoval());
    }
}
