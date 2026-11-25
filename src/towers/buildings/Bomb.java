package towers.buildings;

import engine.EngineState;
import engine.art.sprites.Sprite;
import engine.game.Direction;
import engine.game.Entity;
import engine.game.Position;
import engine.pathing.DirectPathFinder;
import towers.GameState;
import towers.GameTickable;
import towers.SpriteGallery;
import towers.enemies.Enemy;

/**
 * A projectile fired by a tower that flies at an enemy and removes it on contact.
 */
public class Bomb extends Entity implements GameTickable {

    /** How fast the bomb moves each tick. */
    public static final int SPEED = 1;

    /** The position the bomb is flying towards. */
    private final Position target;

    private Sprite sprite;

    /** The direction the bomb is currently moving. */
    private Direction direction;

    /**
     * Creates a bomb that flies from pos towards target.
     *
     * @param pos where the bomb starts
     * @param target where the bomb is heading
     */
    public Bomb(Position pos, Position target) {
        super(pos);
        setSprite(SpriteGallery.bomb.getSprite("default"));
        DirectPathFinder pathfinder = new DirectPathFinder(target);
        this.target = target;
        this.sprite = SpriteGallery.bomb.getSprite("default");
        this.direction = pathfinder.findPath(this).nextDirection(this);
        setRenderOrder(2);
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        setPosition(screen().shift(direction, SPEED));
        for (Enemy enemy : game.getAllEnemies()) {
            if (grid().overlaps(enemy)) {
                this.markForRemoval();
                enemy.markForRemoval();
            }
        }
        if (isOutOfBounds()) {
            markForRemoval();
        }
    }
}
