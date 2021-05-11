package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class PairBox<T extends Figure, C extends Figure> implements HasArea {
    private T figure1;
    private C figure2;

    public PairBox(T figure1, C figure2) {
        super();
        this.figure1=figure1;
        this.figure2=figure2;
    }

    public T getContentFirst () {
        return figure1;
    }

    public C getContentSecond () {
        return figure2;
    }

    public void setContentFirst (T figure1) {
        this.figure1=figure1;
    }
    public void setContentSecond (C figure2) {
        this.figure2=figure2;
    }

    @Override
    public double getArea() {
        return figure1.getArea()+figure2.getArea();
    }

    public boolean isAreaEqual (PairBox<?,?> another) {
        return getArea()==another.getArea();
    }

    public static boolean isAreaEqual (PairBox<?,?> box1, PairBox<?,?> box2) {
        return box1.getArea()==box2.getArea();
    }
}
