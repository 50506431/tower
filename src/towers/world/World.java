package towers.world;

import engine.pathing.PathingMap;
import engine.renderer.RenderableGroup;
import towers.GameTickable;
import towers.tiles.Tile;

import java.util.List;

/**
 * A grid of tiles that enemies walk through.
 */
public interface World extends PathingMap, GameTickable, RenderableGroup {

    /**
     * Returns all the tiles in the world.
     *
     * @return the list of tiles
     */
    public List<Tile> allTiles();

    /**
     * Adds a tile to the world.
     *
     * @param tile the tile to add
     */
    public void place(Tile tile);

    /**
     * Removes any marked tiles from the world.
     */
    public void cleanup();
}
