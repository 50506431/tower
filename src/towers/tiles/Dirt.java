package towers.tiles;

import engine.EngineState;
import engine.game.Position;
import engine.input.MouseState;
import engine.pathing.PathFinder;
import engine.pathing.ShortestGridPathFinder;
import towers.GameState;
import towers.SpriteGallery;
import towers.enemies.Enemy;
import towers.enemies.Knight;

/**
 * A plain dirt tile that enemies can walk over.
 */
public class Dirt extends AbstractTile {

    /**
     * Creates a dirt tile at the given position.
     *
     * @param position where the tile is
     */
    public Dirt(Position position) {
        super(position);
        setSprite(SpriteGallery.dirt.getSprite("default"));
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        super.tick(engine, game);
        Enemy knight;
        PathFinder path;
        path = new ShortestGridPathFinder(game.getPathingMap());
        MouseState mouseState = engine.getMouse();
        if (mouseState.isRightPressed()) {
            boolean fill = false;
            for (Enemy enemy : game.getAllEnemies()) {
                if (enemy.grid().overlaps(grid())) {
                    fill = true;
                    break;
                }
            }
            if (grid().overlaps(mouseState.screenPosition().grid()) && !fill) {
                knight = new Knight(grid(), path);
                game.spawnEnemy(knight);
            }

        }
    }
}
