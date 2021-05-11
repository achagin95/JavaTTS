package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Circle {

    private int x1, y1, r1;

    public Circle(Point center, int radius) {
        this(center.getX(),center.getY(),radius);


    }

    public Circle(int xCenter, int yCenter, int radius) {
        x1 = xCenter;
        y1 = yCenter;
        r1 = radius;

    }

    public Circle(int radius) {
        this(0,0,radius);


    }

    public Circle() {
        this(1);


    }

    public Point getCenter() {
        return new Point(x1, y1);

    }

    public int getRadius() {
        return r1;
    }

    public void setCenter(Point center) {
        x1 = center.getX();
        y1 = center.getY();
    }

    public void setRadius(int radius) {
        r1 = radius;
    }

    public void moveTo(int x, int y) {
        x1=x;
        y1=y;
    }

    public void moveTo(Point point) {

        moveTo(point.getX(),point.getY());
    }

    public void moveRel(int dx, int dy) {
        x1=x1+dx;
        y1=y1+dy;
    }

    public void resize(double ratio) {
        r1=(int)((double)r1*ratio);
    }

    public double getArea() {
        return Math.PI*(double)r1*(double)r1;
    }

    public double getPerimeter() {
        return 2*Math.PI*(double)r1;
    }

    public boolean isInside(int x, int y) {
        return (double)r1>=Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));

    }

    public boolean isInside(Point point) {
        return isInside(point.getX(),point.getY());

    }

    @Override
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
}
