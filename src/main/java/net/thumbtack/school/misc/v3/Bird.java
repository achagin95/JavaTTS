package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.Colored;
import net.thumbtack.school.iface.v3.Movable;

import java.util.Objects;

public class Bird implements Colored, Movable {

    private int x, y;
    Color color;

    public Bird(Point position, Color color) throws ColorException {
        this(position.getX(),position.getY(),color);


    }
    public Bird(Point position, String color) throws ColorException {
        this(position.getX(),position.getY(),color);

    }

    public Bird(int x, int y, Color color) throws ColorException {
        this.x = x;
        this.y = y;
        setColor(color);

    }
    public Bird(int x, int y, String color) throws ColorException {
        this.x = x;
        this.y = y;
        setColor(color);

    }

    public Bird(Color color) throws ColorException {
        this(0,0,color);

    }
    public Bird(String color) throws ColorException {
        this(0,0,color);

    }

    public Bird() {
        x = 0;
        y = 0;
        color = Color.RED;

    }

    public Point getPosition() {
        return new Point(x, y);

    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setPosition(Point position) {
        x = position.getX();
        y = position.getY();
    }

    @Override
    public void setColor(Color color) throws ColorException {
        Color.checkColorFromColor(color);
        this.color = color;
    }
    @Override
    public void setColor(String color) throws ColorException{
        this.color=Color.colorFromString(color);
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*public void moveTo(Point point) {
        moveTo(point.getX(),point.getY());
    }

     */

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


