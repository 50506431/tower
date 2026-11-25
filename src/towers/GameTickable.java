package towers;

import engine.EngineState;

/**
 * A variation of {@link engine.game.Tickable} that also receives the {@link GameState}.
 * @provided
 */
public interface GameTickable {
    /**
     * Progress this component's state forward by one tick.
     *
     * <p>Implementations that own children must tick them too, passing both parameters down
     * unchanged.
     *
     * @param engine The state of the engine, including the mouse, keyboard information and
     *               dimensions of the window.
     * @param game   The state of the game, providing limited queries and updates.
     */
    void tick(EngineState engine, GameState game);
}
