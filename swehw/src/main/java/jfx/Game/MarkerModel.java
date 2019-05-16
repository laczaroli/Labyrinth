package jfx.Game;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import result.GameResult;

import java.time.ZonedDateTime;

/**
 * The player's model, that contains his/her datas.
 */
public class MarkerModel {

    Logger logger = LoggerFactory.getLogger(MarkerModel.class);
    private int size;
    private int currentPosX;
    private int currentPosY;
    private Directions nextDirection = Directions.DEFAULT;


    /**
     * The playground in an array.
     */
    private int table[][] = new int[][]{
            {3,5,0,2,1,2,3,4},
            {1,2,2,1,4,5,2,0},
            {2,0,1,3,4,3,2,1},
            {4,4,0,2,3,0,5,2},
            {4,1,0,3,3,2,4,3},
            {1,0,2,2,3,0,1,0},
            {4,0,2,2,1,4,0,1},
            {2,2,0,4,3,5,4,0}};
    /**
     * A constructor that sets the marker's starting position and
     * the size of the table.
     * @param x an X coordinate
     * @param y a Y coordinate
     * @param size the size of the table.
     */
    public MarkerModel(int x, int y, int size) {
        this.size = size;
        if(x > size || y > size)
            throw new IllegalArgumentException();
        else if(size != 8)
            throw new IllegalArgumentException("Size " + size + " currently not possible!");
        logger.info("Table size has been set to {}  ", size);
        this.currentPosX = x;
        this.currentPosY = y;
        logger.info("Starter positions are: ({}, {})", x, y);
    }


    /**
     * This function calculates the next possible step by the marker's
     * current position and the table's length.
     *
     * @return the next possible step wraps in an array.
     */
    public int[] nextMove() {
        int[] moveArray = new int[]{0,0,0,0};
        if(currentPosY + table[currentPosX][currentPosY] < table[currentPosX].length && (nextDirection == Directions.HORIZONTAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirections.RIGHT.getValue()] = (currentPosY + table[currentPosX][currentPosY])+(currentPosX*size); //jobb
        else
            moveArray[possibleStepDirections.RIGHT.getValue()] = -1;

        if(currentPosX + table[currentPosX][currentPosY] < table.length && (nextDirection == Directions.VERTICAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirections.DOWN.getValue()] = ((currentPosX + table[currentPosX][currentPosY])*size)+currentPosY; //le
        else
            moveArray[possibleStepDirections.DOWN.getValue()] = -1;

        if(currentPosY - table[currentPosX][currentPosY] >= 0 && (nextDirection == Directions.HORIZONTAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirections.LEFT.getValue()] = (currentPosY - table[currentPosX][currentPosY])+(currentPosX*size); //balra
        else
            moveArray[possibleStepDirections.LEFT.getValue()] = -1;

        if(currentPosX - table[currentPosX][currentPosY] >= 0 && (nextDirection == Directions.VERTICAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirections.UP.getValue()] = (currentPosX - table[currentPosX][currentPosY])*size+currentPosY; //fel
        else
            moveArray[possibleStepDirections.UP.getValue()] = -1;
        return moveArray;
    }

    /**
     * Sets the game results to the {@code GameResult} class.
     * @param name the player's name
     * @param gameDuration the game duration
     * @param numberOfRounds the number of rounds
     * @return a {@code GameResult} object
     */
    public GameResult setResults(String name, long gameDuration, int numberOfRounds, boolean solved) {
        return GameResult.builder()
                .player(name)
                .solved(solved)
                .created(ZonedDateTime.now())
                .roundCount(numberOfRounds)
                .duration(gameDuration)
                .build();
    }

    /**
     * Sets the next step direction.
     * @param dir a direction
     */
    public void nextStepDirection(Directions dir) {
        nextDirection = dir;
    }

    /**
     * Sets the table's size..
     * @param size the table's size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the maker's X coordinate.
     * @param posX an X coordinate
     */
    public void setPosX(int posX) {
        this.currentPosX = posX;
    }

    /**
     * Sets the marker's Y coordinate.
     * @param posY a Y coordinate
     */
    public void setPosY(int posY) {
        this.currentPosY = posY;
    }

    /**
     * Gets the table array.
     * @return the table array.
     */
    public int[][] getTable() {
        return table;
    }


    /**
     * Gets the marker's X coordinate.
     * @return an X coordinate
     */
    public int getPosX() {
        return currentPosX;
    }


    /**
     * Gets the marker's Y coordinate.
     * @return an Y coordinate
     */
    public int getPosY() {
        return currentPosY;
    }

    /**
     * Calculates the current marker's position.
     * @return the marker's position
     */
    public int getPos() { return (currentPosX*size)+currentPosY;}

    /**
     * Gets the size of the table.
     * @return the size of the table
     */
    public int getSize() {
        return size;
    }


}
