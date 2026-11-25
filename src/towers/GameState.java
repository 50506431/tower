package towers;

import engine.EngineState;
import engine.game.Position;

import java.util.List;

import engine.pathing.PathingMap;
import towers.enemies.Enemy;

/**
 * An interface to query and make limited updates to the running game.
 *
 * <p>A game may not support enemies in which case {@link #getNearestEnemy(Position)}
 * and {@link #getAllEnemies()} behave as if there are no enemies.
 *
 * <p>The game state is passed to the {@link GameTickable#tick(EngineState, GameState)}
 * method of {@link GameTickable} implementations to allow them to query and update the game.
 * @provided
 */
public interface GameState {

    /**
     * Whether the game has been marked as over.
     *
     * <p>Note: this reports a flag rather than recomputing it. The flag is only updated by
     * {@link #checkGameOver()}.
     *
     * @return True if the game has been marked as over.
     */
    boolean isGameOver();

    /**
     * The map enemies navigate by.
     *
     * @return A {@link PathingMap} describing the currently navigable world.
     * @hint World already implements {@link PathingMap}, so an implementation can return
     *     the world it holds.
     */
    PathingMap getPathingMap();

    /**
     * Attempt to spend the given amount from the game's resources.
     * If the amount is unaffordable nothing is deducted.
     *
     * @param cost The amount to attempt to spend.
     * @return True if the amount was affordable and has now been deducted.
     * @requires cost >= 0
     * @ensures If \result is false then the game's resources are unchanged.
     */
    boolean spend(int cost);

    /**
     * Every {@link Enemy} currently alive, in an unspecified order.
     *
     * @return All active enemies, possibly empty but never null.
     * @ensures Modifying \result does not modify the game.
     */
    List<Enemy> getAllEnemies();

    /**
     * The {@link Enemy} closest to the given {@link Position} by straight line screen distance
     * (see {@link engine.game.ScreenPosition#distance(Position)}).
     *
     * @param pos The position to measure each enemy's distance from.
     * @return The nearest active enemy, or null if there are none.
     * @hint Callers must handle the null case; an empty game has no nearest enemy.
     */
    Enemy getNearestEnemy(Position pos);

    /**
     * Spawn a new enemy within the game if the game supports enemies
     * otherwise do nothing.
     *
     * @param enemy The enemy to add to the game.
     * @ensures The enemy is contained in {@link #getAllEnemies()} if supported.
     */
    void spawnEnemy(Enemy enemy);

    /**
     * Recompute the flag that {@link #isGameOver()} reports.
     *
     * <p>The game is over once an enemy reaches the world's destination, the
     * {@link towers.tiles.Home} tile. It may be possible for the game to
     * never end if enemies are not supported.
     *
     * @ensures {@link #isGameOver()} reflects the game as at the moment this was called.
     */
    void checkGameOver();
}
