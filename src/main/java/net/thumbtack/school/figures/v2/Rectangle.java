package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Stretchable;

//import HasArea;
//import Movable;
//import Stretchable;

import java.util.Objects;

public class Rectangle extends Figure implements HasArea, Stretchable, Movable {
    //private int x1,y1;
    private int x2, y2;


    public Rectangle(Point leftTop, Point rightBottom) {

        super(leftTop);

        this.x2 = rightBottom.getX();
        this.y2 = rightBottom.getY();
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {


        super(xLeft, yTop);
        this.x2 = xRight;
        this.y2 = yBottom;
    }

    public Rectangle(int length, int width) {
        super(0, -width);

        this.x2 = length;
        this.y2 = 0;
    }

    public Rectangle() {
        super(0, -1);

        this.x2 = 1;
        this.y2 = 0;
    }


    public Point getTopLeft() {
        return super.getPoint(); //без ретерна не работает
    }

    public Point getBottomRight() {
        return new Point(x2, y2); // то де самое
    }


    public void setTopLeft(Point topLeft) {
        super.setPoint(topLeft);

    }

    public void setBottomRight(Point bottomRight) {
        x2 = bottomRight.getX();
        y2 = bottomRight.getY();
    }


    public int getLength() {
        return x2 - getTopLeft().getX();
    }

    public int getWidth() {
        return y2 - getTopLeft().getY();
    }

    public void moveTo(int x, int y) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        //x1 = x;
        //y1 = y;
        Point tmpPo = new Point(x, y);
        setTopLeft(tmpPo);
        x2 = getTopLeft().getX() + tmp1;
        y2 = getTopLeft().getY() + tmp2;

    }

    @Override
    public void moveTo(Point point) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        //x1 = point.getX();
        //y1 = point.getY();
        setTopLeft(point);
        x2 = tmp1 + getTopLeft().getX();
        y2 = tmp2 + getTopLeft().getY();

    }

    @Override
    public void moveRel(int dx, int dy) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        int tmpX=getTopLeft().getX()+dx;
        int tmpY=getTopLeft().getY()+dy;
        //x1 = x1 + dx;
        //y1 += dy;
        x2 = tmpX + tmp1;
        y2 = tmpY + tmp2;
        Point tmpPo=new Point(tmpX,tmpY);
        setTopLeft(tmpPo);


    }

    @Override
    public void resize(double ratio) {
        x2 = (int) ((double) getLength() * ratio + (double) getTopLeft().getX());   //В дальнейшем может оказаться, что в тип int преобразовать только в конце
        y2 = (int) ((double) getWidth() * ratio + (double) getTopLeft().getY());   //то есть: (int)x2=(double)getLengh()*ratio;
        // в квадрате метод реализован лучше, если что взять оттуда
    }


    @Override
    public void stretch(double xRatio, double yRatio) {
        x2 = (int) ((double) getLength() * xRatio + (double) getTopLeft().getX());
        y2 = (int) ((double) getWidth() * yRatio + (double) getTopLeft().getY());
    }

    @Override
    public double getArea() {
        return (double) getLength() * (double) getWidth();
    }

    @Override
    public double getPerimeter() {
        return ((double) getLength() + (double) getWidth()) * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        int x1=getTopLeft().getX();
        int y1=getTopLeft().getY();
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }
        return false; //???? без этого ошибка
    }

    @Override
    public boolean isInside(Point point) {
        int x1=getTopLeft().getX();
        int y1=getTopLeft().getY();
        if (point.getX() >= x1 && point.getX() <= x2 && point.getY() >= y1 && point.getY() <= y2) {
            return true;
        }
        return false; // то же самое
    }

    public boolean isIntersects(Rectangle rectangle) {
        int x1=getTopLeft().getX();
        int y1=getTopLeft().getY();
        return (isInside(rectangle.getTopLeft()) || isInside(rectangle.getBottomRight())
                || isInside(rectangle.getTopLeft().getX(), rectangle.getBottomRight().getY())
                || isInside(rectangle.getBottomRight().getX(), rectangle.getTopLeft().getY())) ||
                (x1 >= rectangle.getTopLeft().getX() && x2 <= rectangle.getBottomRight().getX()
                        && y1 >= rectangle.getTopLeft().getY() && y2 <= rectangle.getBottomRight().getY());

        //if (x1 > rectangle.getBottomRight().getX()
        //        || x2 < rectangle.getTopLeft().getX()
        //        || y1 < rectangle.getBottomRight().getY()
        //        || y2 > rectangle.getTopLeft().getY()) {
        //    return true;
        //}
        //return false;


    }

    public boolean isInside(Rectangle rectangle) {
        int x1=getTopLeft().getX();
        int y1=getTopLeft().getY();
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
        return getTopLeft().getX() == rectangle.getTopLeft().getX() &&
                getTopLeft().getY() == rectangle.getTopLeft().getY() &&
                x2 == rectangle.x2 &&
                y2 == rectangle.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopLeft().getX(), getTopLeft().getY(), x2, y2);
    }
}



