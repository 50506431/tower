package towers.enemies;

import engine.EngineState;
import engine.game.Direction;
import engine.game.Entity;
import engine.game.Position;
import engine.pathing.PathFinder;
import engine.renderer.Renderable;
import towers.GameState;
import towers.SpriteGallery;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic enemy that walks along a path towards the home.
 */
public class Knight extends Entity implements Enemy {

    /** How many pixels the knight moves each tick. */
    private static final int DEFAULT_SPEED = 1;

    /** The path finder that tells the knight which way to go. */
    private PathFinder pathfinder;

    /** The default direction the knight is walking. */
    private Direction direction = Direction.SOUTH;

    private int t=0;
    /**
     * Creates a knight at the given position that follows the given route.
     *
     * @param pos where the knight starts
     * @param route the path finder the knight follows
     */
    public Knight(Position pos, PathFinder route) {
        super(pos);
        setSprite(SpriteGallery.knight.getSprite("down"));
        setRenderOrder(1);
        this.pathfinder = route;
    }

    @Override
    public List<Renderable> render() {
        List<Renderable> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public void setRoute(PathFinder route) {
        this.pathfinder = route;
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        if(t%2==0){
            Direction dir = pathfinder.findPath(this).nextDirection(this);
            if (dir != direction) {
                setPosition(grid());
                direction = dir;
            }
            setPosition(screen().shift(direction, DEFAULT_SPEED));
            switch (direction) {
                case EAST -> setSprite(SpriteGallery.knight.getSprite("right"));
                case WEST -> setSprite(SpriteGallery.knight.getSprite("left"));
                default -> setSprite(SpriteGallery.knight.getSprite("down"));
            }
            if (isOutOfBounds()) {
                markForRemoval();
            }
        }

        t++;
    }
}
