package net.thumbtack.school.figures.v2;

//import HasArea;
//import Movable;
//import Stretchable;
import net.thumbtack.school.iface.v2.HasArea;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Stretchable;

import java.util.Objects;

public class Ellipse extends Figure implements HasArea, Movable, Stretchable {
    //private int xc, yc;
    private int x2, y2;
    // xc yc координаты центра, х2-размер по оси х, у2-размер по оси у

    public Ellipse(Point center, int xAxis, int yAxis) {
        //xc = center.getX();
        //yc = center.getY();
        super(center);
        x2 = xAxis;
        y2 = yAxis;
    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        //xc = xCenter;
        //yc = yCenter;
        super(xCenter,yCenter);
        x2 = xAxis;
        y2 = yAxis;
    }


    public Ellipse(int xAxis, int yAxis) {
        //xc = 0;
        //yc = 0;
        super(0,0);
        x2 = xAxis;
        y2 = yAxis;
    }


    public Ellipse() {

        //xc = 0;
        //yc = 0;
        super(0,0);
        x2 = 1;
        y2 = 1;
        //может оказаться, что х=минус 1??
    }


    public Point getCenter() {
        //return new Point(xc, yc);
        return super.getPoint();
    }


    public int getXAxis() {
        return x2;
    }


    public int getYAxis() {
        return y2;
    }


    public void setXAxis(int xAxis) {
        x2 = xAxis;
    }


    public void setYAxis(int yAxis) {
        y2 = yAxis;
    }


    public void setCenter(Point center) {
        //xc = center.getX();
        //yc = center.getY();
        super.setPoint(center);
    }

    @Override
    public void moveTo(int x, int y) {
        //xc = x;
        //yc = y;
        Point tmp=new Point(x,y);
        setCenter(tmp);
    }


    @Override
    public void moveTo(Point point) {
        //xc = point.getX();
        //yc = point.getY();

        setCenter(point);

    }


    @Override
    public void moveRel(int dx, int dy) {
        //xc = xc + dx;
        //yc = yc + dy;
        int tmp1=getCenter().getX()+dx;
        int tmp2=getCenter().getY()+dy;
        Point tmp=new Point(tmp1,tmp2);
        setCenter(tmp);
    }


    @Override
    public void resize(double ratio) {
        x2 = (int) ((double) x2 * ratio);
        y2 = (int) ((double) y2 * ratio);
    }


    @Override
    public void stretch(double xRatio, double yRatio) {
        x2 = (int) ((double) x2 * xRatio);
        y2 = (int) ((double) y2 * yRatio);
    }

    @Override
    public double getArea() {
        return Math.PI * ((double) getYAxis()/2)  * ((double) getXAxis()/2);
    }
//нужно ли вводить отдельно переменную (dooble tmp) а затем ее возвращать (retern tmp)??
//как в нижнем методе

    @Override
    public double getPerimeter() {
        double v;
        double tmp;
        tmp=(double)(getXAxis()*getXAxis()+getYAxis()*getYAxis())/8;
        v = 2 * Math.PI * Math.sqrt(tmp);
        return v;
        //какая-то дич, разница в ответах 0.2, не могу понять где ошибка
    }

    @Override
    public boolean isInside(int x, int y) {
        int xc=getCenter().getX();
        int yc=getCenter().getY();
        return (((x - xc) * (x - xc)) / ((double)getXAxis()/2 * (double)getXAxis()/2))
                + (((y - yc) * (y - yc)) / (((double)getYAxis()/2 * (double)getYAxis()/2))) <= 1;


    }

    @Override
    public boolean isInside(Point point) {
        double a=getXAxis()/2;
        double b=getYAxis()/2;
        int c=point.getX()-getCenter().getX();
        int d=point.getY()-getCenter().getY();
        return ((c*c)/(a*a)+(d*d)/(b*b)<=1);
        //return ((point.getX() - xc) * (point.getX() * xc) / (getXAxis()/2 * getXAxis()/2)) + ((point.getY() - yc) * (point.getY() - yc) / (getYAxis()/2 * getYAxis()/2)) <= 1;
//разница в дубле
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return getCenter().getX() == ellipse.getCenter().getX() &&
                getCenter().getY() == ellipse.getCenter().getY() &&
                x2 == ellipse.x2 &&
                y2 == ellipse.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCenter().getX(), getCenter().getY(), x2, y2);
    }


}
