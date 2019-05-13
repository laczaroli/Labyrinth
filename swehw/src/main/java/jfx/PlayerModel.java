package jfx;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PlayerModel {

    private int size;
    private int currentPosX;
    private int currentPosY;
    private Directions nextDirection = Directions.DEFAULT;



    private int table[][] = new int[][] {{1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0}};

    public PlayerModel(int x, int y, int size) {
        this.size = size;
        this.currentPosX = x;
        this.currentPosY = y;

    }

    public int[] nextMove() {
        int[] moveArray = new int[]{0,0,0,0};
        if(currentPosY + table[currentPosX][currentPosY] < table[currentPosX].length && (nextDirection == Directions.HORIZONTAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirecitons.RIGHT.getValue()] = (currentPosY + table[currentPosX][currentPosY])+(currentPosX*size); //jobb
        else
            moveArray[possibleStepDirecitons.RIGHT.getValue()] = -1;

        if(currentPosX + table[currentPosX][currentPosY] < table.length && (nextDirection == Directions.VERTICAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirecitons.DOWN.getValue()] = ((currentPosX + table[currentPosX][currentPosY])*size)+currentPosY; //le
        else
            moveArray[possibleStepDirecitons.DOWN.getValue()] = -1;

        if(currentPosY - table[currentPosX][currentPosY] >= 0 && (nextDirection == Directions.HORIZONTAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirecitons.LEFT.getValue()] = (currentPosY - table[currentPosX][currentPosY])+(currentPosX*size); //balra
        else
            moveArray[possibleStepDirecitons.LEFT.getValue()] = -1;

        if(currentPosX - table[currentPosX][currentPosY] >= 0 && (nextDirection == Directions.VERTICAL || nextDirection == Directions.DEFAULT))
            moveArray[possibleStepDirecitons.UP.getValue()] = (currentPosX - table[currentPosX][currentPosY])*size+currentPosY; //fel
        else
            moveArray[possibleStepDirecitons.UP.getValue()] = -1;
        return moveArray;
    }

    public void getResults(long gameDuration, int numberOfRounds) {
        EntityManagerFactory emf = new Persistence.createEntityManagerFactory();
    }

    public void nextStepDirection(Directions dir) {
        nextDirection = dir;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPosX(int posX) {
        this.currentPosX = posX;
    }

    public void setPosY(int posY) {
        this.currentPosY = posY;
    }

    public int[][] getTable() {
        return table;
    }

    public int getPosX() {
        return currentPosX;
    }

    public int getPosY() {
        return currentPosY;
    }

    public int getPos() { return (currentPosX*size)+currentPosY;}

    public int getSize() {
        return size;
    }


}
