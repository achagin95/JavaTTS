package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredCircle extends Circle {

    private int color;

    public ColoredCircle(Point center, int radius, int color) {
        super(center, radius);
        this.color = color;

    }

    public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
        super(xCenter, yCenter, radius);
        this.color = color;
    }


    public ColoredCircle(int radius, int color) {
        super(radius);
        this.color = color;
    }


    public ColoredCircle(int color) {
        super();
        this.color = color;
    }


    public ColoredCircle() {
        super();
        color = 1;
    }


    public Point getCenter() {
        return super.getCenter();
    }


    public int getRadius() {
        return super.getRadius();
    }


    public void setCenter(Point center) {
        super.setCenter(center);
    }


    public void setRadius(int radius) {
        super.setRadius(radius);
    }

    //10
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color=color;
    }


    //12
    public void moveRel(int dx, int dy) {
        super.moveRel(dx,dy);
    }


    //1.
    public void resize(double ratio) {
        super.resize(ratio);
    }

    //13
    public double getArea() {
        return super.getArea();
    }

    //14.
    public double getPerimeter() {
        return super.getPerimeter();
    }

    //15.
    public boolean isInside(int x, int y) {
        return super.isInside(x,y);
    }

    //16
    public boolean isInside(Point point) {
        return super.isInside(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredCircle that = (ColoredCircle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
