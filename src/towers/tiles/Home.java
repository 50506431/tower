package towers.tiles;

import engine.game.Position;
import towers.SpriteGallery;

/**
 * The home tile that enemies are trying to reach.
 */
public class Home extends AbstractTile {

    /**
     * Creates a home tile at the given position.
     *
     * @param position where the home is
     */
    public Home(Position position) {
        super(position);
        setSprite(SpriteGallery.dirt.getSprite("village"));
    }
}
