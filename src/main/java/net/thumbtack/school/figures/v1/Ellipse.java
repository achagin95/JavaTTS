package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Ellipse {
    private int xc, yc, x2, y2;
    // xc yc координаты центра, х2-размер по оси х, у2-размер по оси у

    public Ellipse(Point center, int xAxis, int yAxis) {
        this(center.getX(),center.getY(),xAxis,yAxis);

    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        xc = xCenter;
        yc = yCenter;
        x2 = xAxis;
        y2 = yAxis;
    }


    public Ellipse(int xAxis, int yAxis) {
        this(0,0,xAxis,yAxis);

    }


    public Ellipse() {
        this(0,0,1,1);

    }


    public Point getCenter() {
        return new Point(xc, yc);
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
        xc = center.getX();
        yc = center.getY();
    }

    public void moveTo(int x, int y) {
        xc = x;
        yc = y;
    }


    public void moveTo(Point point) {

        moveTo(point.getX(),point.getY());
    }


    public void moveRel(int dx, int dy) {
        xc = xc + dx;
        yc = yc + dy;
    }


    public void resize(double ratio) {
        x2 = (int) ((double) x2 * ratio);
        y2 = (int) ((double) y2 * ratio);
    }


    public void stretch(double xRatio, double yRatio) {
        x2 = (int) ((double) x2 * xRatio);
        y2 = (int) ((double) y2 * yRatio);
    }

    public double getArea() {
        return Math.PI * ((double) getYAxis()/2)  * ((double) getXAxis()/2);
    }


    public double getPerimeter() {
        double v;
        double tmp;
        tmp=(double)(getXAxis()*getXAxis()+getYAxis()*getYAxis())/8;
        v = 2 * Math.PI * Math.sqrt(tmp);
        return v;

    }

    public boolean isInside(int x, int y) {
        return (((x - xc) * (x - xc)) / ((double)getXAxis()/2 * (double)getXAxis()/2))
                + (((y - yc) * (y - yc)) / (((double)getYAxis()/2 * (double)getYAxis()/2))) <= 1;


    }

    public boolean isInside(Point point) {

        return isInside(point.getX(),point.getY());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return xc == ellipse.xc &&
                yc == ellipse.yc &&
                x2 == ellipse.x2 &&
                y2 == ellipse.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xc, yc, x2, y2);
    }
}
