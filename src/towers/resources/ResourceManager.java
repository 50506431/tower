package towers.resources;

import engine.EngineState;
import engine.game.GridPosition;
import engine.game.Position;
import engine.game.Tickable;
import engine.renderer.Renderable;
import engine.renderer.RenderableGroup;
import engine.ui.Letter;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows how many resources the player has and lets them spend resources.
 */
public class ResourceManager implements Tickable, RenderableGroup {

    /** The resource counter this manager controls. */
    private Resource resources;

    /** The letter used to draw the number on screen. */
    private Letter letter;

    /** The list of things to draw. */
    private List<Renderable> list;

    /** The digit currently shown on screen. */
    private char digital;

    /** The digits 0 to 9, used to turn a number into a character. */
    private List<Character> number = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    /**
     * Creates a manager for the given resource counter.
     *
     * @param resource the resource counter to use
     */
    public ResourceManager(Resource resource) {
        this.resources = resource;
        this.digital = number.get(this.resources.getCurrent());
    }

    /**
     * Tries to spend the given amount of resources.
     *
     * @param cost how much to spend
     * @return true if the resources were spent
     */
    public boolean spend(int cost) {
        if (cost > resources.getCurrent()) {
            return false;
        }
        if (cost <= resources.getCurrent()) {
            resources.spend(cost);
            this.digital = number.get(this.resources.getCurrent());
        }
        return true;
    }

    @Override
    public void tick(EngineState engine) {
        resources.tick(engine);
        this.digital = number.get(this.resources.getCurrent());
    }

    @Override
    public List<Renderable> render() {
        ArrayList<Renderable> list = new ArrayList<>();
        Position myPos = new GridPosition(15, 0);
        letter = new Letter(myPos, digital, 1);
        list.add(letter);
        return list;
    }
}
