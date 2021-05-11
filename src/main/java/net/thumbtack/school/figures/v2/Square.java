package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

//import HasArea;
//import Movable;
//import Resizable;

import java.util.Objects;

public class Square extends Figure implements HasArea, Resizable, Movable {
    //private int x1, y1;
    private int x2, y2;

    public Square(Point leftTop, int size) {
        //x1 = leftTop.getX();
        //y1 = leftTop.getY();
        super(leftTop);

        x2 = getTopLeft().getX() + size;
        y2 = getTopLeft().getY() + size;

    }

    public Square(int xLeft, int yTop, int size) {
        //x1 = xLeft;
        //y1 = yTop;
        super(xLeft, yTop);
        x2 = getTopLeft().getX() + size;
        y2 = getTopLeft().getY() + size;
    }

    public Square(int size) {
        //x1 = 0;
        //y2 = 0;
        super(0, -size);
        x2 = getTopLeft().getX() + size;
        y2 = 0;
    }

    public Square() {
        //x1 = 0;
        //y1 = -1;
        super(0, -1);
        x2 = 1;
        y2 = 0;
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
        //x1 = topLeft.getX();
        //y1 = topLeft.getY();
        x2 = getTopLeft().getX() + tmp;
        y2 = getTopLeft().getY() + tmp;

        //в ректангле такого не было, нижние границы не устанавливал
    }

    public int getLength() {
        return x2 - getTopLeft().getX();
    }

    @Override
    public void moveTo(int x, int y) {
        int tmp = getLength();
        //x1 = x;
        //y1 = y;
        Point tmpPo = new Point(x, y);
        setTopLeft(tmpPo);
        x2 = getTopLeft().getX() + tmp;
        y2 = getTopLeft().getY() + tmp;
    }

    @Override
    public void moveTo(Point point) {
        int tmp = getLength();
        //x1 = point.getX();
        //y1 = point.getY();
        setTopLeft(point);
        x2 = getTopLeft().getX() + tmp;
        y2 = getTopLeft().getY() + tmp;
    }

    @Override
    public void moveRel(int dx, int dy) {
        int tmpLen = getLength();
        int tmp1 = getTopLeft().getX();
        int tmp2 = getTopLeft().getY();
        tmp1 += dx;
        tmp2 += dy;
        Point tmpPo = new Point(tmp1, tmp2);
        setTopLeft(tmpPo);
        x2 = getTopLeft().getX() + tmpLen;
        y2 = getTopLeft().getY() + tmpLen;
    }

    @Override
    public void resize(double ratio) {
        int tmp = getLength();
        x2 = getTopLeft().getX() + (int) ((double) tmp * ratio); //реальзовал метод получше, чем в прямоугольнике
        // взять на заметку и если что в прямоугольнике исправить как тут
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
        if (x1 <= x && x2 >= x && y1 <= y && y2 >= y) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isInside(Point point) {
        int x1 = getTopLeft().getX();
        int y1 = getTopLeft().getY();
        return x1 <= point.getX() && x2 >= point.getX() && y1 <= point.getY() && y2 >= point.getY();
        //так проще, чам в методе выше
    }

    public boolean isIntersects(Square square) {
        int x1 = getTopLeft().getX();
        int y1 = getTopLeft().getY();
        return (isInside(square.getTopLeft()) || isInside(square.getBottomRight())
                || isInside(square.getTopLeft().getX(), square.getBottomRight().getY())
                || isInside(square.getBottomRight().getX(), square.getTopLeft().getY())) ||
                (x1 >= square.getTopLeft().getX() && x2 <= square.getBottomRight().getX()
                        && y1 >= square.getTopLeft().getY() && y2 <= square.getBottomRight().getY());
        //return x1 > square.getBottomRight().getX() || x2 < square.getTopLeft().getX()
        //        || y1 < square.getBottomRight().getY() || y2 > square.getTopLeft().getY();

    }

    public boolean isInside(Square square) {
        int x1 = getTopLeft().getX();
        int y1 = getTopLeft().getY();
        return x1 <= square.getTopLeft().getX() && x2 >= square.getBottomRight().getX() &&
                y1 <= square.getTopLeft().getY() && y2 >= square.getBottomRight().getY();

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
