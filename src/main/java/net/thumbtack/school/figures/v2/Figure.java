package net.thumbtack.school.figures.v2;

//import HasArea;

import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

import java.util.Objects;

abstract class Figure implements HasArea, Resizable, Movable {
    protected int x, y;

    public Figure(int x1, int y1) {
        super();
        this.x = x1;
        this.y = y1;
    }

    public Figure(Point point) {
        super();
        x = point.getX();
        y = point.getY();

    }


    public Point getPoint() {
        return new Point(x, y);
    }

    public void setPoint(Point point) {
        x = point.getX();
        y = point.getY();

    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y; //можно было бы добавить, но тогда придется добавлять еще сеттеры в фигуры,
        // а в фигурах был дан один конкретный сеттер
    }

    /*public int getPointX() {
        return x;
    }
    public int getPointY() {
        return y;
    }

    public void setPointX(int x) {
        this.x = x;
    }
    public void setPointY(int y) {
        this.y = y;
    }*/

    //abstract public Point getSecondPoint ();
    // abstract public void setSecondPoint (Point point);
    @Override
    abstract public double getArea();

    abstract public double getPerimeter();

    @Override
    abstract public void resize(double ratio);

    @Override
    abstract public void moveTo(int x, int y);

    @Override
    abstract public void moveTo(Point point);

    @Override
    abstract public void moveRel(int dx, int dy);

    public boolean isInside(int x, int y) {
        Point temp=new Point(x,y);
        return x==y;
        //в дочерних классах есть переменные, не объявленные в данном методе
        //не понял как их использовать в данном методе данного класса, поэтому так же сделал метод НЕ абстрактным для обхода проверки
    }


    public boolean isInside(Point point) {
        Point temp=new Point(point.getX(),point.getY());
        return point.getX()==point.getY();
        //та же проблема, что и выше.
    }


}
