package towers;


import engine.EngineState;
import engine.game.Game;
import engine.game.Tickable;
import engine.renderer.Renderable;
import towers.enemies.EnemyManager;
import towers.resources.Resource;
import towers.resources.ResourceManager;
import towers.world.WorldLoadException;

import java.util.ArrayList;
import java.util.List;
import engine.Engine;
import engine.game.GridPosition;
import engine.game.Position;
import towers.world.World;
import towers.world.WorldBuilder;
import towers.tiles.Tile;
import towers.tiles.Home;
import towers.tiles.Forest;
import towers.tiles.Grass;
/**
 * The tower defence game. The engine progresses the game and determines what to render via this class.
 *
 * @invariant Once the game is over it never resumes.
 * @multistage
 * @stage0
 * @stage1
 * @stage2
 * @provided
 */

public class FireWall implements Game, Tickable {

    // 📢 Stage 0: Uncomment this line to manage resources in the empty game
    private final ResourceManager resources;

    // 📢 Stage 1: Uncomment this line
    private final World world;

    // 📢 Stage 2: Uncomment this line
    private final EnemyManager enemies;

    /**
     * Construct a game from the given map or use the default map if the given is empty.
     *
     * <p>Creates a {@link Resource} from the {@code Resource.DEFAULT_} constants wrapped in a
     * {@link ResourceManager}, and an empty {@link EnemyManager}.</p>
     *
     * @param map of the world to load.
     * @throws WorldLoadException If the maps contents are not a valid world encoding.
     *
     * @multistage
     * @stage0
     * @stage1
     * @stage2
     * @provided
     */
    public FireWall(String map) throws WorldLoadException {
        // 📢 Stage 0: Uncomment these lines
        resources = new ResourceManager(
                new Resource(
                        Resource.DEFAULT_STARTING_RESOURCES,
                        Resource.DEFAULT_MIN_RESOURCES,
                        Resource.DEFAULT_MAX_RESOURCES
                )
        );

        // 📢 Stage 1: Uncomment these lines
        List<Tile> tiles = map.isBlank() ? generateMilliesWorld()
                : WorldBuilder.fromString(Engine.getSpatial(), map);
        world = WorldBuilder.fromTiles(tiles);

        // 📢 Stage 2: Uncomment these lines to construct the default enermy manager
        enemies = new EnemyManager();
    }


    /**
     * Progress the whole game by one tick.
     *
     * <p>Does nothing if the game is already over (according to {@link GameState#checkGameOver()}).
     *
     * <p>If the game continues, the resources, world and enemies are ticked in that order, the
     * latter two receiving the game state as well.</p>
     *
     * @param engine The state of the engine, including the mouse, keyboard information and
     *               dimensions of the window.
     * @ensures Once the game is over, no further ticks change anything.
     * @multistage
     * @provided
     * @stage1
     * @stage2
     */
    @Override
    public void tick(EngineState engine) {
        // 📢 Stage 1: Uncomment the below, comment for Stage 2

        // 📢 Stage 2: Uncomment the below
        GameState game = new FireWallState(resources, world, enemies);

        // 📢 Stage 1: Uncomment the below
        game.checkGameOver();
        if (game.isGameOver()) {
            return;
        }

        // 📢 Stage 0: Uncomment the below
        resources.tick(engine);

        // 📢 Stage 1: Uncomment the below
        world.tick(engine, game);

        // 📢 Stage 2: Uncomment the below
        enemies.tick(engine, game);
    }

    /**
     * Return everything to draw for the current frame, called by the engine immediately after each
     * {@link #tick(EngineState)}.
     *
     * <p>The order is important. All world renderables should be first, then all enemies, then all resources.
     *
     * @return The renderables to draw, back to front.
     * @multistage
     * @provided
     * @stage0
     * @stage1
     * @stage2
     */
    @Override
    public List<Renderable> render() {
        List<Renderable> renderables = new ArrayList<>();

        // 📢 Stage 1: Uncomment these lines
        renderables.addAll(world.render());

        // 📢 Stage 2: Uncomment these lines
        renderables.addAll(enemies.render());

        // 📢 Stage 0: Uncomment these lines
        renderables.addAll(resources.render());

        return renderables;
    }

    // 📢 Stage 1: Uncomment these lines, you may need to adjust imports
    private List<Tile> generateMilliesWorld() {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < Engine.DEFAULT_TILES_PER_ROW; i++) {
            for (int j = 0; j < Engine.DEFAULT_TILES_PER_ROW; j++) {
                Position position = new GridPosition(i, j);
                if (i == Engine.DEFAULT_TILES_PER_ROW / 2
                       && j == Engine.DEFAULT_TILES_PER_ROW / 2) {
                    tiles.add(new Home(position));
                    continue;
                }
                if ((i == 0 || i == Engine.DEFAULT_TILES_PER_ROW - 1)
                        || (j == 0 || j == Engine.DEFAULT_TILES_PER_ROW - 1)) {
                    tiles.add(new Forest(position));
                    continue;
                }
                tiles.add(new Grass(position));
            }
        }
        return tiles;
    }
}
