package net.thumbtack.school.figures.v2;

//import net.thumbtack.school.figures.v1.Point;
//import HasArea;
//import Movable;
//import Resizable;
import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

import java.util.Objects;

public class Circle extends Figure implements HasArea, Movable, Resizable {

    //private int x1, y1;

    private int r1;

    public Circle(Point center, int radius) {
        //x1 = center.getX();
        //y1 = center.getY();
        super(center);
        r1 = radius;

    }

    public Circle(int xCenter, int yCenter, int radius) {
        //x1 = xCenter;
        //y1 = yCenter;
        super(xCenter,yCenter);
        r1 = radius;

    }

    public Circle(int radius) {
        //x1 = 0;
        //y1 = 0;
        super(0,0);
        r1 = radius;

    }

    public Circle() {
        //x1 = 0;
        //y1 = 0;
        super(0,0);
        r1 = 1;

    }

    public Point getCenter() {
        //return new Point(x1, y1);
        return super.getPoint();

    }

    public int getRadius() {
        return r1;
    }

    public void setCenter(Point center) {
        //x1 = center.getX();
        //y1 = center.getY();
        super.setPoint(center);
    }

    public void setRadius(int radius) {
        r1 = radius;
    }

    @Override
    public void moveTo(int x, int y) {
        //x1=x;
        //y1=y;
        Point tmp=new Point(x,y);
        setCenter(tmp);

    }

    @Override
    public void moveTo(Point point) {
        //x1=point.getX();
        //y1=point.getY();
        setCenter(point);
    }

    @Override
    public void moveRel(int dx, int dy) {
        //x1+=dx;
        //y1+=dy;
        int tmp1,tmp2;
        tmp1=getCenter().getX()+dx;
        tmp2=getCenter().getY()+dy;
        Point tmp=new Point(tmp1, tmp2);
        setCenter(tmp);


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
        //можно без корня сделать р1*р1 слева как в след методе
    }

    @Override
    public boolean isInside(Point point) {
        int x1=getCenter().getX();
        int y1=getCenter().getY();
        return (double)r1>=Math.sqrt((point.getX()-x1)*(point.getX()-x1)+(point.getY()-y1)*(point.getY()-y1));
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return x1 == circle.x1 &&
                y1 == circle.y1 &&
                r1 == circle.r1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, r1);
    }

     */


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
