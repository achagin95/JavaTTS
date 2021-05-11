package net.thumbtack.school.iface.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.figures.v2.Rectangle;

public interface Movable {


    void moveTo(int x, int y);

    default void moveTo(Point point) {
        Point temp=new Point(point.getX(),point.getY());
        //не понял как сделать присвоение переменным, которые не объявлены в данном методе,а объявлены в другом.
        //просто якобы переписал эти методы в классах


    }

    /*Circle
    public void moveTo(Point point) {
        //x1=point.getX();
        //y1=point.getY();
        setCenter(point);
    }
    Ellipse
    public void moveTo(Point point) {
        //xc = point.getX();
        //yc = point.getY();

        setCenter(point);
    }
    Rectangle
    public void moveTo(Point point) {
        int tmp1 = getLength();
        int tmp2 = getWidth();
        //x1 = point.getX();
        //y1 = point.getY();
        setTopLeft(point);
        x2 = tmp1 + getTopLeft().getX();
        y2 = tmp2 + getTopLeft().getY();
    }
    Square
    public void moveTo(Point point) {
        int tmp = getLength();
        //x1 = point.getX();
        //y1 = point.getY();
        setTopLeft(point);
        x2 = getTopLeft().getX() + tmp;
        y2 = getTopLeft().getY() + tmp;
    }

     */

    void moveRel(int dx, int dy);

}
