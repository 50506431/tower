package towers.tiles;

import engine.game.Position;

/**
 * Conversion of a character into a {@link Tile} instance.
 * @provided
 */
public class TileFactory {

    /**
     * Construct the {@link Tile} the given symbol stands for, at the given position.
     *
     * <table>
     *     <tr><th>Character</th><th>Tile</th></tr>
     *     <tr><td>d</td><td>{@link Dirt}</td></tr>
     *     <tr><td>g</td><td>{@link Grass}</td></tr>
     *     <tr><td>f</td><td>{@link Forest}</td></tr>
     *     <tr><td>e</td><td>{@link Home}</td></tr>
     *     <tr><td>s</td><td>{@link Spawner}</td></tr>
     *     <caption>&nbsp;</caption>
     * </table>
     *
     * @param position The position to place the new tile at.
     * @param symbol The character identifying which kind of tile to create.
     * @return A new tile of the type the symbol stands for, never null.
     * @throws IllegalArgumentException If the symbol is not listed above.
     * @multistage
     * @stage1 Implement {@link Dirt}, {@link Grass}, {@link Forest}, {@link Home}
     * @stage2 Add the {@link Spawner}
     */
    public static Tile fromSymbol(Position position, char symbol) throws IllegalArgumentException {
        // 📢 Stage 1: Uncomment the below
        return switch (symbol) {
            case 'f' -> new Forest(position);
            case 'd' -> new Dirt(position);
            case 'g' -> new Grass(position);
            case 'e' -> new Home(position);
            // 📢 Stage 2: Uncomment the below
            case 's' -> new Spawner(position);
            case 'G' -> new Gate(position);
            default -> throw new IllegalArgumentException("Invalid symbol");
        };

        // 📢 Stage 1: remove the below

    }
}
