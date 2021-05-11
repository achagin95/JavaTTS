package net.thumbtack.school.figures.v3;

import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

import java.util.Objects;


public class Square extends Figure implements HasArea, Resizable, Movable {

    private int x2, y2;

    public Square(Point leftTop, int size) {

        this(leftTop.getX(), leftTop.getY(), size);

    }

    public Square(int xLeft, int yTop, int size) {

        super(xLeft, yTop);
        x2 = getTopLeft().getX() + size;
        y2 = getTopLeft().getY() + size;
    }

    public Square(int size) {

        this(0, -size, size);

    }

    public Square() {

        this(1);

    }


    public Point getTopLeft() {
        return super.getPoint();
    }

    public Point getBottomRight() {
        return new Point(x2, y2);
    }

    public void setTopLeft(Point topLeft) {
        int tmp = getLength();
        super.setPoint(topLeft);
        x2 = getTopLeft().getX() + tmp;
        y2 = getTopLeft().getY() + tmp;

    }

    public int getLength() {
        return x2 - getTopLeft().getX();
    }

    @Override
    public void moveTo(int x, int y) {

        setTopLeft(new Point(x,y));

    }



    @Override
    public void moveRel(int dx, int dy) {

        setTopLeft(new Point(getTopLeft().getX()+dx, getTopLeft().getY()+dy));


    }

    @Override
    public void resize(double ratio) {
        int tmp = getLength();
        x2 = getTopLeft().getX() + (int) ((double) tmp * ratio);
        y2 = getTopLeft().getY() + (int) ((double) tmp * ratio);

    }

    @Override
    public double getArea() {
        return (double) getLength() * (double) getLength();
    }

    @Override
    public double getPerimeter() {
        return (double) getLength() * 4;
    }

    @Override
    public boolean isInside(int x, int y) {
        int x1 = getTopLeft().getX();
        int y1 = getTopLeft().getY();
        return (x1 <= x && x2 >= x && y1 <= y && y2 >= y);

    }



    public boolean isIntersects(Square square) {
        int x1 = getTopLeft().getX();
        int y1 = getTopLeft().getY();
        return (isInside(square.getTopLeft()) || isInside(square.getBottomRight())
                || isInside(square.getTopLeft().getX(), square.getBottomRight().getY())
                || isInside(square.getBottomRight().getX(), square.getTopLeft().getY())) ||
                (x1 >= square.getTopLeft().getX() && x2 <= square.getBottomRight().getX()
                        && y1 >= square.getTopLeft().getY() && y2 <= square.getBottomRight().getY());

    }

    public boolean isInside(Square square) {

        return isInside(square.getTopLeft()) && isInside(square.getBottomRight());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return getTopLeft().getX() == square.getTopLeft().getX() &&
                x2 == square.x2 &&
                getTopLeft().getY() == square.getTopLeft().getY() &&
                y2 == square.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopLeft().getX(), x2, getTopLeft().getX(), y2);
    }


}
