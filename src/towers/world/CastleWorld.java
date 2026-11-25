package towers.world;

import engine.EngineState;
import engine.game.Position;
import engine.game.ScreenPosition;
import engine.renderer.Renderable;
import towers.GameState;
import towers.tiles.Home;
import towers.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * A world made of tiles, where enemies walk towards the home tile.
 */
public class CastleWorld implements World {

    /** The tiles that make up the world. */
    private List<Tile> tilelist = new ArrayList<>();

    @Override
    public List<Tile> allTiles() {
        return tilelist;
    }

    @Override
    public Position getDestination() {
        for (Tile tiles : allTiles()) {
            if (tiles instanceof Home) {
                return tiles.grid();
            }
        }
        return null;
    }

    @Override
    public List<Renderable> render() {
        List<Renderable> renderlist = new ArrayList<>();
        for (Tile tiles : allTiles()) {
            renderlist.addAll(tiles.render());
        }
        return renderlist;
    }

    @Override
    public List<ScreenPosition> walkablePositions() {
        List<ScreenPosition> screenlist = new ArrayList<>();
        for (Tile tile : tilelist) {
            if (tile.canWalkThrough()) {
                screenlist.add(tile.screen());
            }
        }
        return screenlist;
    }

    @Override
    public void cleanup() {
        for (Tile tile : allTiles()) {
            tile.cleanup();
        }
    }

    @Override
    public void place(Tile tile) {
        if (tile instanceof Home && getDestination() != null) {
            return;
        }
        tilelist.add(tile);
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        cleanup();
        for (Tile tiles : tilelist) {
            tiles.tick(engine, game);
        }
    }
}
