package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v1.Point;
import net.thumbtack.school.iface.v2.Colored;
import net.thumbtack.school.iface.v2.Movable;

import java.util.Objects;

public class Bird implements Colored, Movable {

    private int x, y, color;

    public Bird(Point position, int color) {
        x = position.getX();
        y = position.getY();
        this.color = color;

    }

    public Bird(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;

    }

    public Bird(int color) {
        x = 0;
        y = 0;
        this.color = color;

    }

    public Bird() {
        x = 0;
        y = 0;
        color = 1;

    }

    public Point getPosition() {
        return new Point(x, y);

    }

    public int getColor() {
        return color;
    }

    public void setPosition(Point position) {
        x = position.getX();
        y = position.getY();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveTo(Point point) {
        x = point.getX();
        y = point.getY();
    }

    public void moveRel(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return x == bird.x &&
                y == bird.y &&
                color == bird.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }
}
