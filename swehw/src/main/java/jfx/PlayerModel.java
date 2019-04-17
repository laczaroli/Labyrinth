package jfx;



public class PlayerModel {

    private int size = 8;
    private int currentPosX = 0;
    private int currentPosY = 0;
    private String nextDirection="DEFAULT";

    private int table[][]  = new int[][] {{3,5,0,2,1,2,3,4},
            {1,2,2,1,4,5,2,0},
            {2,0,1,3,4,3,2,1},
            {4,4,0,2,3,0,5,2},
            {4,1,0,3,3,2,4,3},
            {1,0,2,2,3,0,1,0},
            {4,0,2,2,1,4,0,1},
            {2,2,0,4,3,5,4,0}};

    public PlayerModel(int x, int y, int size) {
        this.size = size;
        this.currentPosX = x;
        this.currentPosY = y;
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

    public void setSize(int size) {
        this.size = size;
    }

    public void setPosX(int posX) {
        this.currentPosX = posX;
    }

    public void setPosY(int posY) {
        this.currentPosY = posY;
    }

    public void nextStepDirection(String dir) {
        if(dir.equals("horizontal")) {
            nextDirection=dir;
        } else if(dir.equals("vertical")) {
            nextDirection=dir;
        }
    }

    public int[] nextMove() {
        int[] moveArray = new int[]{0,0,0,0};
        if(currentPosY + table[currentPosX][currentPosY] < table[currentPosX].length && (nextDirection == "horizontal" || nextDirection == "DEFAULT"))
            moveArray[0] = (currentPosY + table[currentPosX][currentPosY])+(currentPosX*size); //jobb
        else
            moveArray[0] = -1;

        if(currentPosX + table[currentPosX][currentPosY] < table.length && (nextDirection == "vertical" || nextDirection == "DEFAULT"))
            moveArray[1] = ((currentPosX + table[currentPosX][currentPosY])*size)+currentPosY; //le
        else
            moveArray[1] = -1;

        if(currentPosY - table[currentPosX][currentPosY] >= 0 && (nextDirection == "horizontal" || nextDirection == "DEFAULT"))
            moveArray[2] = (currentPosY - table[currentPosX][currentPosY])+(currentPosX*size); //balra
        else
            moveArray[2] = -1;

        if(currentPosX - table[currentPosX][currentPosY] >= 0 && (nextDirection == "vertical" || nextDirection == "DEFAULT"))
            moveArray[3] = (currentPosX - table[currentPosX][currentPosY])*size+currentPosY; //fel
        else
            moveArray[3] = -1;
        return moveArray;
    }
}
