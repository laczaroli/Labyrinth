package jfx;

enum Directions {
    DEFAULT,
    HORIZONTAL,
    VERTICAL
}

enum possibleStepDirecitons {
    RIGHT(0),
    DOWN(1),
    LEFT(2),
    UP(3);
    final int directionNumber;
    possibleStepDirecitons(int directionNumber) {
        this.directionNumber = directionNumber;
    }

    public int getValue() {
        return directionNumber;
    }
}
