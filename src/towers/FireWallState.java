package towers;

import engine.game.Position;
import engine.pathing.PathingMap;
import towers.enemies.Enemy;
import towers.enemies.EnemyManager;
import towers.resources.ResourceManager;
import towers.world.World;

import java.util.List;

/**
 * The state of the game, holding the world, enemies and resources.
 */
public class FireWallState implements GameState {

    /** True once the game is over. */
    private boolean gameover = false;

    /** The resource manager the player spends from. */
    private ResourceManager resources;

    /** The world the game is played on. */
    private World world;

    /** The enemies currently in the game. */
    private EnemyManager enemyManager;

    /**
     * Creates a game state with a new enemy manager.
     *
     * @param resources the resource manager to use
     * @param world the world to play on
     */
    public FireWallState(ResourceManager resources, World world) {
        this(resources, world, new EnemyManager());
    }

    /**
     * Creates a game state with the given resources, world and enemies.
     *
     * @param resources the resource manager to use
     * @param world the world to play on
     * @param enemies the enemy manager to use
     */
    public FireWallState(ResourceManager resources, World world, EnemyManager enemies) {
        this.resources = resources;
        this.world = world;
        this.enemyManager = enemies;
    }

    @Override
    public boolean isGameOver() {
        return gameover;
    }

    @Override
    public PathingMap getPathingMap() {
        return world;
    }

    @Override
    public boolean spend(int cost) {
        return resources.spend(cost);
    }

    @Override
    public List<Enemy> getAllEnemies() {
        return enemyManager.getEnemies();
    }

    @Override
    public Enemy getNearestEnemy(Position pos) {
        Enemy enemy = enemyManager.getNearest(pos);
        return enemy;
    }

    @Override
    public void spawnEnemy(Enemy enemy) {
        this.enemyManager.spawnEnemy(enemy);
    }

    @Override
    public void checkGameOver() {
        Enemy enemy = getNearestEnemy(this.world.getDestination());
        if (enemy != null && enemy.grid().overlaps(this.world.getDestination())) {
            this.gameover = true;
        }
    }
}
