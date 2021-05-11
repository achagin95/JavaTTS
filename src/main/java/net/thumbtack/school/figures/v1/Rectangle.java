package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle {
    private int x1, y1, x2, y2;


    public Rectangle(Point leftTop, Point rightBottom) {
        this(leftTop.getX(),leftTop.getY(),rightBottom.getX(),rightBottom.getY());

    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {

        this.x1 = xLeft;
        this.y1 = yTop;
        this.x2 = xRight;
        this.y2 = yBottom;
    }

    public Rectangle(int length, int width) {
        this(0,-width,length,0);

    }

    public Rectangle() {
        this(1,1);

    }

    public Point getTopLeft() {
        return new Point(x1, y1);
    }

    public Point getBottomRight() {
        return new Point(x2, y2);
    }

    public void setTopLeft(Point topLeft) {
        x1 = topLeft.getX();
        y1 = topLeft.getY();
    }

    public void setBottomRight(Point bottomRight) {
        x2 = bottomRight.getX();
        y2 = bottomRight.getY();
    }

    public int getLength() {
        return x2 - x1;
    }

    public int getWidth() {
        return y2 - y1;
    }

    public void moveTo(int x, int y) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        x1 = x;
        y1 = y;
        x2 = x1 + tmp1;
        y2 = y1 + tmp2;

    }

    public void moveTo(Point point) {
        moveTo(point.getX(),point.getY());



    }

    public void moveRel(int dx, int dy) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        x1 = x1 + dx;
        y1 += dy;
        x2 = x1 + tmp1;
        y2 = y1 + tmp2;


    }

    public void resize(double ratio) {
        x2 = (int) ((double) getLength() * ratio + (double) x1);
        y2 = (int) ((double) getWidth() * ratio + (double) y1);

    }

    public void stretch(double xRatio, double yRatio) {
        x2 = (int) ((double) getLength() * xRatio + (double) x1);
        y2 = (int) ((double) getWidth() * yRatio + (double) y1);
    }

    public double getArea() {
        return (double) getLength() * (double) getWidth();
    }

    public double getPerimeter() {
        return ((double) getLength() + (double) getWidth()) * 2;
    }

    public boolean isInside(int x, int y) {
        return  (x >= x1 && x <= x2 && y >= y1 && y <= y2);

    }

    public boolean isInside(Point point) {
        return isInside(point.getX(),point.getY());

    }

    public boolean isIntersects(Rectangle rectangle) {
        return (isInside(rectangle.getTopLeft()) || isInside(rectangle.getBottomRight())
                || isInside(rectangle.getTopLeft().getX(), rectangle.getBottomRight().getY())
                || isInside(rectangle.getBottomRight().getX(), rectangle.getTopLeft().getY())) ||
                (x1 >= rectangle.getTopLeft().getX() && x2 <= rectangle.getBottomRight().getX()
                        && y1 >= rectangle.getTopLeft().getY() && y2 <= rectangle.getBottomRight().getY());




    }

    public boolean isInside(Rectangle rectangle) {
        if (x1 <= rectangle.getTopLeft().getX() && x2 >= rectangle.getBottomRight().getX()
                && y1 <= rectangle.getTopLeft().getY() && y2 >= rectangle.getBottomRight().getY()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return x1 == rectangle.x1 &&
                y1 == rectangle.y1 &&
                x2 == rectangle.x2 &&
                y2 == rectangle.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }
}



