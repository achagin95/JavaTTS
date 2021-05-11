package net.thumbtack.school.figures.v3;


import net.thumbtack.school.colors.v3.Color;




import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

public class ColoredCircle extends Circle implements Colored {


    private Color color;

    public ColoredCircle(Point center, int radius, Color color) throws ColorException {
        this(center.getX(),center.getY(), radius,color);

        setColor(color);

    }
    public ColoredCircle(Point center, int radius, String color) throws ColorException {
        this(center.getX(),center.getY(), radius,color);



    }


    public ColoredCircle(int xCenter, int yCenter, int radius, Color color) throws ColorException {
        super(xCenter, yCenter, radius);
        Color.checkColorFromColor(color);

        setColor(color);
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, String color) throws ColorException {
        super(xCenter, yCenter, radius);

        setColor(color);
    }


    public ColoredCircle(int radius, Color color) throws ColorException{
        this(0,0,radius,color);

    }

    public ColoredCircle(int radius, String color) throws ColorException{
        this(0,0,radius,color);

    }


    public ColoredCircle(Color color) throws ColorException {
        this(1,color);

    }

    public ColoredCircle(String color) throws ColorException {
        this(1,color);

    }


    public ColoredCircle() throws ColorException {
        this(1,Color.RED);


    }

    //10
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        Color.checkColorFromColor(color);
        this.color=color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {

        this.color=Color.colorFromString(colorString);

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
