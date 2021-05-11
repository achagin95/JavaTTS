package net.thumbtack.school.misc.v3;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestBird {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testBird1() throws ColorException {
        Point center = new Point(10, 20);
        Bird bird = new Bird(center, Color.RED);
        assertAll(
                () -> assertEquals(10, bird.getPosition().getX()),
                () -> assertEquals(20, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
    }

    @Test
    public void testBird2() throws ColorException {
        Bird bird = new Bird(10, 20, "BLUE");
        assertAll(
                () -> assertEquals(10, bird.getPosition().getX()),
                () -> assertEquals(20, bird.getPosition().getY()),
                () -> assertEquals(Color.BLUE, bird.getColor())
        );
    }

    @Test
    public void testBird3() throws ColorException {
        Bird bird = new Bird(Color.RED);
        assertAll(
                () -> assertEquals(0, bird.getPosition().getX()),
                () -> assertEquals(0, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
    }

    @Test
    public void testBird4() throws ColorException {
        Bird bird = new Bird();
        assertAll(
                () -> assertEquals(0, bird.getPosition().getX()),
                () -> assertEquals(0, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
    }

    @Test
    public void testSetPositionAndColor() throws ColorException {
        Bird bird = new Bird(10, 20, Color.RED);
        bird.setPosition(new Point(100, 50));
        bird.setColor("GREEN");
        assertAll(
                () -> assertEquals(100, bird.getPosition().getX()),
                () -> assertEquals(50, bird.getPosition().getY()),
                () -> assertEquals(Color.GREEN, bird.getColor())
        );
    }

    @Test
    public void testMoveBird() throws ColorException {
        Bird bird = new Bird(10, 20, "RED");
        bird.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, bird.getPosition().getX()),
                () -> assertEquals(70, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
        bird.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, bird.getPosition().getX()),
                () -> assertEquals(200, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
        bird.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1100, bird.getPosition().getX()),
                () -> assertEquals(1200, bird.getPosition().getY()),
                () -> assertEquals(Color.RED, bird.getColor())
        );
    }
}




