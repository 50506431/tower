package towers.tiles;

import engine.EngineState;
import engine.game.Entity;
import engine.game.Position;
import engine.renderer.Renderable;
import towers.GameState;
import towers.buildings.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * A base tile that holds the buildings placed on top of it.
 */
public class AbstractTile extends Entity implements Tile {

    /** The buildings currently built on this tile. */
    private List<Building> buildinglist = new ArrayList<>();

    /**
     * Creates a tile at the given position.
     *
     * @param pos where the tile is
     */
    public AbstractTile(Position pos) {
        super(pos);
    }

    @Override
    public void addBuilding(Building building) {
        buildinglist.add(building);
    }

    @Override
    public void cleanup() {
        buildinglist.removeIf(building -> building.isMarkedForRemoval());
    }

    @Override
    public List<Building> getBuildings() {
        if (buildinglist != null) {
            return new ArrayList<>(buildinglist);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Renderable> render() {
        List<Renderable> list = new ArrayList<>();
        list.add(this);
        for (Building building : buildinglist) {
            list.addAll(building.render());
        }
        return list;
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        super.tick(engine);
        cleanup();
        for (Building building : buildinglist) {
            building.tick(engine, game);
        }
    }

    @Override
    public boolean canWalkThrough() {
        return buildinglist.isEmpty();
    }
}
