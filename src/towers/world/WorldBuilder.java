package towers.world;

import engine.game.GridPosition;
import engine.renderer.Spatial;
import towers.tiles.Tile;
import towers.tiles.TileFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a world from text or from a list of tiles.
 */
public class WorldBuilder {

    /**
     * Empty
     */
    public WorldBuilder() {
    }

    /**
     * Puts the given tiles into a new world.
     *
     * @param tiles the tiles to place
     * @return the world that was built
     */
    public static World fromTiles(List<Tile> tiles) {
        World world = new CastleWorld();
        for (Tile tile : tiles) {
            world.place(tile);
        }
        return world;
    }

    /**
     * Turns a map of characters into a list of tiles.
     *
     * @param spatial the spatial to get sizes from
     * @param text the map, one row of characters per line
     * @return the tiles described by the map
     * @throws WorldLoadException if the map is not square or has an unknown symbol
     */
    public static List<Tile> fromString(Spatial spatial, String text) throws WorldLoadException {
        int windowsize = spatial.windowSize();
        int percell = spatial.gridCellSize();
        String[] rows = text.split("\n");
        List<Tile> list = new ArrayList<>();
        int size = windowsize / percell;
        if (rows.length != size) {
            throw new WorldLoadException("row dimension not match", rows.length);
        }
        for (int i = 0; i < size; i++) {
            if (rows[i].length() != size) {
                throw new WorldLoadException("col dimension not match", i);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char a = rows[i].charAt(j);
                GridPosition pos = new GridPosition(j, i);
                try {
                    list.add(TileFactory.fromSymbol(pos, a));
                } catch (IllegalArgumentException e) {
                    throw new WorldLoadException("unknown symbol" + a, j, i);
                }
            }
        }
        return list;
    }
}
