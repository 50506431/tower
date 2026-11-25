package towers.buildings;

import engine.EngineState;
import engine.art.ArtNotFoundException;
import engine.art.sprites.SpriteGroup;
import engine.game.Entity;
import engine.game.Position;
import engine.game.Removable;
import engine.renderer.Renderable;
import engine.renderer.RenderableGroup;
import engine.timing.FixedTimer;
import towers.GameState;
import towers.SpriteGallery;
import towers.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * A building that shoots bombs at enemies within range.
 */
public class Tower extends Entity implements Building, RenderableGroup, Removable {

    /** How many resources it costs to build a tower. */
    public static final int COST = 1;

    /** How far a tower can shoot, in pixels. */
    public static final int DEFAULT_RANGE = 200;

    /** How many ticks a tower waits between shots. */
    public static final int DEFAULT_FIRERATE = 100;

    /** Counts down the time until the next shot. */
    private FixedTimer timer;

    /** The sprites used to draw the tower. */
    private SpriteGroup tower = SpriteGallery.tower;

    /** The bombs this tower has fired that are still flying. */
    private List<Bomb> bombs = new ArrayList<>();

    /**
     * Creates a tower at the given position.
     *
     * @param pos where the tower is built
     * @throws ArtNotFoundException if the tower sprites cannot be loaded
     */
    public Tower(Position pos) throws ArtNotFoundException {
        super(pos);
        setSprite(SpriteGallery.tower.getSprite("loaded"));
        timer = new FixedTimer(0);
        setRenderOrder(1);
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        cleanup();
        for (Bomb bomb : bombs) {
            bomb.tick(engine, game);
        }
        if (timer.isFinished()) {
            setSprite(tower.getSprite("loaded"));
            Enemy enemy = game.getNearestEnemy(screen());
            if (enemy != null && screen().distance(enemy) <= DEFAULT_RANGE) {
                bombs.add(new Bomb(screen(), enemy.screen()));
                setSprite(tower.getSprite("reloading"));
                this.timer = new FixedTimer(DEFAULT_FIRERATE);
            }
        }
        else {
            this.timer.tick();
            if (timer.isFinished()) {
                setSprite(tower.getSprite("loaded"));
            }
        }
    }

    @Override
    public List<Renderable> render() {
        List<Renderable> list = new ArrayList<>();
        list.add(this);
        list.addAll(bombs);
        return list;
    }

    /** Removes bombs on the map. */
    public void cleanup() {
        bombs.removeIf(bom -> bom.isMarkedForRemoval());
    }
}
