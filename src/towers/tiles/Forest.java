package towers.tiles;

import engine.game.Position;
import towers.SpriteGallery;

/**
 * A forest tile that enemies cannot walk through.
 */
public class Forest extends AbstractTile {

    /**
     * Creates a forest tile at the given position.
     *
     * @param position where the tile is
     */
    public Forest(Position position) {
        super(position);
        setSprite(SpriteGallery.forest.getSprite("default"));
    }

    @Override
    public boolean canWalkThrough() {
        return false;
    }
}
