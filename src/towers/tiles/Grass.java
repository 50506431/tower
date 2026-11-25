package towers.tiles;

import engine.EngineState;
import engine.game.Position;
import engine.input.MouseState;
import towers.GameState;
import towers.SpriteGallery;
import towers.buildings.Tower;

/**
 * A tile the player can place a tower on.
 */
public class Grass extends AbstractTile {

    /**
     * Creates a grass tile at the given position.
     *
     * @param position where the tile is
     */
    public Grass(Position position) {
        super(position);
        setSprite(SpriteGallery.grass.getSprite("default"));
    }

    @Override
    public void tick(EngineState engine, GameState game) {
        super.tick(engine, game);
        MouseState mouseState = engine.getMouse();
        if (mouseState.isLeftPressed()) {
            click(mouseState.screenPosition(), game);
        }
    }

    /**
     * Places a tower on this tile if the mouse is over it and the player
     * can afford one.
     *
     * @param mousePosition where the mouse is
     * @param game the game state, used to spend resources
     */
    public void click(Position mousePosition, GameState game) {
        if (this.getBuildings().isEmpty()) {
            if (grid().overlaps(mousePosition)) {
                if (game.spend(Tower.COST)) {
                    addBuilding(new Tower(screen()));
                }
            }
        }
    }
}
