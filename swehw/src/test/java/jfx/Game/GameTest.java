package jfx.Game;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    GameController gc = new GameController();
    MarkerModel p = new MarkerModel(0,0,8);

    @Test
    void testSteps() {
        assertEquals(Directions.VERTICAL,gc.performNextStep(possibleStepDirections.LEFT));
        assertEquals(Directions.VERTICAL,gc.performNextStep(possibleStepDirections.RIGHT));
        assertEquals(Directions.HORIZONTAL,gc.performNextStep(possibleStepDirections.UP));
        assertEquals(Directions.HORIZONTAL,gc.performNextStep(possibleStepDirections.DOWN));
    }

    @Test
    void positionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> gc.setMarkerPosition(12,12));
        assertThrows(IllegalArgumentException.class, () -> gc.calculatePos(12,12));
    }

    @Test
    void modelCreationTest() {
        assertThrows(IllegalArgumentException.class, () -> new MarkerModel(10,10,8));
        assertThrows(IllegalArgumentException.class, () -> new MarkerModel(0,0,1));
    }

    @Test
    void gameSolvedTest() {
        assertFalse(gc.isSolved());
        gc.setMarkerPosition(7,7);
        assertTrue(gc.isSolved());
    }
}