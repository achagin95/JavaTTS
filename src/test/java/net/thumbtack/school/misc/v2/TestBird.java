package net.thumbtack.school.misc.v2;
import net.thumbtack.school.figures.v1.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestBird {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testBird1() {
        Point center = new Point(10, 20);
        Bird bird = new Bird(center, 10);
        assertAll(
                () -> assertEquals(10, bird.getPosition().getX()),
                () -> assertEquals(20, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
    }

    @Test
    public void testBird2() {
        Bird bird = new Bird(10, 20, 10);
        assertAll(
                () -> assertEquals(10, bird.getPosition().getX()),
                () -> assertEquals(20, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
    }

    @Test
    public void testBird3() {
        Bird bird = new Bird(10);
        assertAll(
                () -> assertEquals(0, bird.getPosition().getX()),
                () -> assertEquals(0, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
    }

    @Test
    public void testBird4() {
        Bird bird = new Bird();
        assertAll(
                () -> assertEquals(0, bird.getPosition().getX()),
                () -> assertEquals(0, bird.getPosition().getY()),
                () -> assertEquals(1, bird.getColor())
        );
    }

    @Test
    public void testSetPositionAndColor() {
        Bird bird = new Bird(10, 20, 10);
        bird.setPosition(new Point(100, 50));
        bird.setColor(200);
        assertAll(
                () -> assertEquals(100, bird.getPosition().getX()),
                () -> assertEquals(50, bird.getPosition().getY()),
                () -> assertEquals(200, bird.getColor())
        );
    }

    @Test
    public void testMoveBird() {
        Bird bird = new Bird(10, 20, 10);
        bird.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, bird.getPosition().getX()),
                () -> assertEquals(70, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
        bird.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, bird.getPosition().getX()),
                () -> assertEquals(200, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
        bird.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1100, bird.getPosition().getX()),
                () -> assertEquals(1200, bird.getPosition().getY()),
                () -> assertEquals(10, bird.getColor())
        );
    }


}
