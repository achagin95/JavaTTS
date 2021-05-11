package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    private int x1, x2, y1, y2;

    public Square(Point leftTop, int size) {
        this(leftTop.getX(),leftTop.getY(),size);

    }

    public Square(int xLeft, int yTop, int size) {
        x1 = xLeft;
        y1 = yTop;
        x2 = x1 + size;
        y2 = y1 + size;
    }

    public Square(int size) {
        this(0,-size,size);

    }

    public Square() {
        this(0,-1,1);

    }

    public Point getTopLeft() {
        return new Point(x1, y1);
    }

    public Point getBottomRight() {
        return new Point(x2, y2);
    }

    public void setTopLeft(Point topLeft) {
        int tmp = getLength();
        x1 = topLeft.getX();
        y1 = topLeft.getY();
        x2 = x1 + tmp;
        y2 = y1 + tmp;

        //в ректангле такого не было, нижние границы не устанавливал
    }

    public int getLength() {
        return x2 - x1;
    }

    public void moveTo(int x, int y) {
        int tmp = getLength();
        x1 = x;
        y1 = y;
        x2 = x1 + tmp;
        y2 = y1 + tmp;
    }

    public void moveTo(Point point) {
        moveTo(point.getX(),point.getY());
        /*int tmp = getLength();
        x1 = point.getX();
        y1 = point.getY();
        x2 = x1 + tmp;
        y2 = y1 + tmp;

         */
    }

    public void moveRel(int dx, int dy) {
        //int tmp=getLength();
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    public void resize(double ratio) {
        int tmp=getLength();
        x2 = x1 + (int) ((double) tmp * ratio); //реальзовал метод получше, чем в прямоугольнике
        // взять на заметку и если что в прямоугольнике исправить как тут
        y2 = y1 + (int) ((double) tmp * ratio);

    }

    public double getArea() {
        return (double) getLength() * (double) getLength();
    }

    public double getPerimeter() {
        return (double) getLength() * 4;
    }

    public boolean isInside(int x, int y) {
        return  (x1 <= x && x2 >= x && y1 <= y && y2 >= y);

    }

    public boolean isInside(Point point) {
        //return x1 <= point.getX() && x2 >= point.getX() && y1 <= point.getY() && y2 >= point.getY();
        return isInside(point.getX(),point.getY());
    }

    public boolean isIntersects(Square square) {
        return (isInside(square.getTopLeft()) || isInside(square.getBottomRight())
                || isInside(square.getTopLeft().getX(), square.getBottomRight().getY())
                || isInside(square.getBottomRight().getX(), square.getTopLeft().getY())) ||
                (x1 >= square.getTopLeft().getX() && x2 <= square.getBottomRight().getX()
                        && y1 >= square.getTopLeft().getY() && y2 <= square.getBottomRight().getY());

    }

    public boolean isInside(Square square) {
        return x1 <= square.getTopLeft().getX() && x2 >= square.getBottomRight().getX() &&
                y1 <= square.getTopLeft().getY() && y2 >= square.getBottomRight().getY();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x1 == square.x1 &&
                x2 == square.x2 &&
                y1 == square.y1 &&
                y2 == square.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, x2, y1, y2);
    }
}
