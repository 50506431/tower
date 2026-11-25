package towers.tiles;

import engine.EngineState;
import engine.game.Entity;
import engine.game.Position;
import engine.renderer.Renderable;

import java.util.ArrayList;
import java.util.List;

import engine.renderer.RenderableGroup;
import towers.buildings.Building;
import towers.GameState;
import towers.GameTickable;

/**
 * One square in the world, responsible for the {@link Building}s stacked on it.
 *
 * @invariant A tile's position never changes once constructed.
 * @provided
 */
public interface Tile extends GameTickable, RenderableGroup, Position {

    /**
     * Progress this tile and everything built on it by one tick, in this order:
     *
     * <ol>
     *     <li>discard buildings marked for removal (see {@link #cleanup()}),</li>
     *     <li>tick each remaining building (via {@link Building#tick(EngineState, GameState)}),
     *         and</li>
     * </ol>
     *
     * <p>Subclasses that override this must call {@code super.tick} first, or their buildings will
     * never progress.
     *
     * @param engine The state of the engine, including the mouse, keyboard information and
     *               dimensions of the window.
     * @param game   The state of the game, providing limited queries and updates.
     * @ensures No building owned by this tile is marked for removal.
     */
    @Override
    void tick(EngineState engine, GameState game);

    /**
     * Add a {@link Building} on this tile, making the tile responsible for it.
     *
     * @param building The building for this tile to take responsibility for.
     * @ensures The building is contained in {@link #getBuildings()}.
     */
    void addBuilding(Building building);

    /**
     * The {@link Building}s currently on this tile.
     *
     * @return The buildings this tile is responsible for, possibly empty but never null.
     * @ensures Modifying \result does not modify the tile.
     */
    List<Building> getBuildings();

    /**
     * Whether enemies may walk through this tile.
     *
     * <p>By default, a tile is walkable exactly when nothing has been built on it, so placing any building
     * blocks the square.
     *
     * @return True if this tile can be walked through.
     */
    boolean canWalkThrough();

    /**
     * Discard every {@link Building} on this tile marked for removal, which is how a destroyed
     * building stops being ticked and rendered.
     *
     * @ensures No building in {@link #getBuildings()} is marked for removal.
     * @hint Removing from a list shifts later elements down one, so a forwards loop skips entries.
     *     Iterate backwards from the last index instead.
     */
    void cleanup();

    /**
     * Everything needed to draw this tile and its contents.
     *
     * <p>This tile comes first so it is drawn behind everything stacked on it. The remainder
     * follows the order of {@link #getBuildings()}.
     *
     * @return The renderables required to draw this tile and its contents.
     * @hint Add each building's group rather than the building alone, or a tower's bombs are never
     *     drawn. See {@link Building#render()}.
     */
    @Override
    List<Renderable> render();
}
