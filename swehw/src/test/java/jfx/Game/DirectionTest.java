package jfx.Game;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DirectionTest {
    @Test
    void testPossibleDirections() {
        assertEquals(3, possibleStepDirections.UP.getValue());
        assertEquals(1, possibleStepDirections.DOWN.getValue());
        assertEquals(2, possibleStepDirections.LEFT.getValue());
        assertEquals(0, possibleStepDirections.RIGHT.getValue());
    }
}
