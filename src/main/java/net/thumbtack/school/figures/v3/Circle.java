package net.thumbtack.school.figures.v3;


import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

import java.util.Objects;

public class Circle extends Figure implements HasArea, Movable, Resizable {



    private int r1;

    public Circle(Point center, int radius) {

        this(center.getX(),center.getY(),radius);


    }

    public Circle(int xCenter, int yCenter, int radius) {

        super(xCenter,yCenter);
        r1 = radius;

    }

    public Circle(int radius) {

        this(0,0,radius);

    }

    public Circle() {

        this(1);

    }

    public Point getCenter() {

        return super.getPoint();

    }

    public int getRadius() {
        return r1;
    }

    public void setCenter(Point center) {

        super.setPoint(center);
    }

    public void setRadius(int radius) {
        r1 = radius;
    }

    @Override
    public void moveTo(int x, int y) {


        setCenter(new Point(x,y));

    }



    @Override
    public void moveRel(int dx, int dy) {

        setCenter(new Point(getCenter().getX()+dx,getCenter().getY()+dy));


    }

    @Override
    public void resize(double ratio) {
        r1=(int)((double)r1*ratio);
    }

    @Override
    public double getArea() {
        return Math.PI*(double)r1*(double)r1;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*(double)r1;
    }

    @Override
    public boolean isInside(int x, int y) {
        int x1=getCenter().getX();
        int y1=getCenter().getY(); //записал инты отдельно, чтоб не получилась большая формула
        return (double)r1>=Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return r1 == circle.r1 &&
                getCenter().getX()==circle.getCenter().getX() &&
                getCenter().getY()==circle.getCenter().getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter().getX(),getCenter().getY(),r1);
    }
}
