package net.thumbtack.school.figures.v3;

//import HasArea;


import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

public abstract class Figure implements HasArea, Resizable, Movable {
    protected int x, y;

    public Figure(int x1, int y1) {
        super();
        this.x = x1;
        this.y = y1;
    }

    public Figure(Point point) {
        this(point.getX(), point.getY());


    }


    public Point getPoint() {
        return new Point(x, y);
    }

    public void setPoint(Point point) {

        setPoint(point.getX(), point.getY());

    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }



    @Override
    abstract public double getArea();

    abstract public double getPerimeter();

    @Override
    abstract public void resize(double ratio);

    @Override
    abstract public void moveTo(int x, int y);


    @Override
    abstract public void moveRel(int dx, int dy);


    abstract public boolean isInside(int x, int y);


    public boolean isInside(Point point) {

        return isInside(point.getX(), point.getY());
    }





}
