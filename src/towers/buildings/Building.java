package towers.buildings;

import engine.EngineState;
import engine.game.Removable;
import engine.renderer.Renderable;
import engine.renderer.RenderableGroup;
import towers.GameState;
import towers.GameTickable;

import java.util.List;


/**
 * Something the player can construct on top of a {@link towers.tiles.Tile}.
 * @provided
 */
public interface Building extends RenderableGroup, Removable, GameTickable {
    /**
     * process the engine
     *
     * @param engine The state of the engine, including the mouse, keyboard information and
     *               dimensions of the window.
     * @param game   The state of the game, providing limited queries and updates.
     */
    void tick(EngineState engine, GameState game);

    /**
     * As an interface for Bomb and Tower.
     *
     * @return true if markForRemoval
     */
    boolean isMarkedForRemoval();

    /**
     * Mark for removal.
     */
    void markForRemoval();

}

