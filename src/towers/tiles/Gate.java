package towers.tiles;

import engine.EngineState;
import engine.game.Position;
import engine.input.MouseState;
import towers.GameState;
import towers.SpriteGallery;

public class Gate extends AbstractTile{
    private boolean clicked = false;
    public Gate(Position position) {
        super(position);
        setSprite(SpriteGallery.gate.getSprite("open"));

    }

    @Override
    public void tick(EngineState engine, GameState game) {
        super.tick(engine, game);
        MouseState mouseState = engine.getMouse();
        if (grid().overlaps(mouseState.screenPosition())){
            if(mouseState.isLeftPressed()){
                this.clicked=true;
                setSprite(SpriteGallery.gate.getSprite("closed"));
            }
            else{
                this.clicked=false;
                setSprite(SpriteGallery.gate.getSprite("open"));
            }
        }
    }

    @Override
    public boolean canWalkThrough() {
        if( !clicked){
            return true;}
        else{
            return false;
        }
    }
}
