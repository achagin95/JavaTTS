package net.thumbtack.school.figures.v2;

//import Colored;
import net.thumbtack.school.iface.v2.Colored;
import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {
    private int color;

    public ColoredRectangle(Point leftTop, Point rightBottom, int color) {
        super(leftTop, rightBottom);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        super(xLeft, yTop, xRight, yBottom);
        this.color = color;
    }


    public ColoredRectangle(int length, int width, int color) {
        //super(setTopLeft(0, -width)); ту нельзя, т.к. вызывать метод класса, нужно через точку
        //super(setBottomRight(length, 0));
        //super.setTopLeft(0,-width); так нельзя, т.к. в скобках должен быть класс поинт
        super(0, -width, length, 0);
        this.color = color;
    }


    public ColoredRectangle(int color) {
        super();
        this.color = color;
    }


    public ColoredRectangle() {
        super();
        color = 1;
    }


    public Point getTopLeft() {
        Point topLeft = super.getTopLeft(); //пришлось ввести новую ссылку, иначе просто через ретерн была ошибка
        // Ошибку закоментил в методе снизу...........внизу красивее смотрится
        return topLeft;
    }


    public Point getBottomRight() {
        //super.getBottomRight();
        return super.getBottomRight();
    }

    @Override
    public int getColor() {
        return color;
    }


    public void setTopLeft(Point topLeft) {
        super.setTopLeft(topLeft);
    }

    //10.
    public void setBottomRight(Point bottomRight) {
        super.setBottomRight(bottomRight);
    }


    //11.
    @Override
    public void setColor(int color) {
        this.color = color;
    }

    public int getLength() {
        return super.getLength();
    }

    public int getWidth() {
        //int a;
        //a = super.getBottomRight().getX() - super.getTopLeft().getX();
        //return a;
        //проверил написание программы через длинную цепочку, после проверки вернул как в методе сверху
        return super.getWidth();
    }

    public void moveRel(int dx, int dy) {
        super.moveRel(dx, dy);
    }


    public void resize(double ratio) {
        super.resize(ratio);
    }


    public void stretch(double xRatio, double yRatio) {
        super.stretch(xRatio, yRatio);
    }


    public double getArea() {
        return super.getArea();
    }

    //18
    public double getPerimeter() {
        return super.getPerimeter();
    }

    public boolean isInside(int x, int y) {
        return super.isInside(x, y);
    }


    public boolean isInside(Point point) {
        return super.isInside(point);
    }

    //21
    public boolean isIntersects(ColoredRectangle rectangle) {
        return super.isIntersects(rectangle);
    }


    public boolean isInside(ColoredRectangle rectangle) {
        return super.isInside(rectangle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }


}
