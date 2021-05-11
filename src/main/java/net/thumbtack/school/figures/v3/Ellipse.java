package net.thumbtack.school.figures.v3;


import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Stretchable;

import java.util.Objects;

public class Ellipse extends Figure implements HasArea, Movable, Stretchable {

    private int x2, y2;


    public Ellipse(Point center, int xAxis, int yAxis) {

        this(center.getX(), center.getY(), xAxis, yAxis);

    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {

        super(xCenter, yCenter);
        x2 = xAxis;
        y2 = yAxis;
    }


    public Ellipse(int xAxis, int yAxis) {

        this(0, 0, xAxis, yAxis);

    }


    public Ellipse() {

        this(1, 1);

    }


    public Point getCenter() {

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

        super.setPoint(center);
    }

    @Override
    public void moveTo(int x, int y) {

        setCenter(new Point(x, y));
    }





    @Override
    public void moveRel(int dx, int dy) {

        setCenter(new Point(getCenter().getX() + dx, getCenter().getY() + dy));
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
        return Math.PI * ((double) getYAxis() / 2) * ((double) getXAxis() / 2);
    }


    @Override
    public double getPerimeter() {
        double v;
        double tmp;
        tmp = (double) (getXAxis() * getXAxis() + getYAxis() * getYAxis()) / 8;
        v = 2 * Math.PI * Math.sqrt(tmp);
        return v;

    }

    @Override
    public boolean isInside(int x, int y) {
        int xc = getCenter().getX();
        int yc = getCenter().getY();
        return (((x - xc) * (x - xc)) / ((double) getXAxis() / 2 * (double) getXAxis() / 2))
                + (((y - yc) * (y - yc)) / (((double) getYAxis() / 2 * (double) getYAxis() / 2))) <= 1;


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
