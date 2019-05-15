package jfx.Game;

/**
 * It contains the possible step directions that connects
 * to the {@code nextMove} array.
 */
public enum possibleStepDirections {
    RIGHT(0),
    DOWN(1),
    LEFT(2),
    UP(3);
    final int directionNumber;

    /**
     * Customize the enum object.
     * @param directionNumber a number that connects to a direction
     */
    possibleStepDirections(int directionNumber) {
        this.directionNumber = directionNumber;
    }

    /**
     * Gets a direction value by its name.
     * @return a direction's value
     */
    public int getValue() {
        return directionNumber;
    }
}