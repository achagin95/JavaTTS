package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;

import java.lang.reflect.Array;

public class ArrayBox<T extends Figure> {

    private T[] figures;


    public ArrayBox(T[] figures) {
        super();
        this.figures=figures;
    }

    public T[] getContent(){
        return figures;
    }

    public void setContent (T[] figures) {
        this.figures=figures;
    }

    public T getElement(int i) {
        return figures[i];
    }

    public void setElement(T figure, int i) {

        figures[i]=figure;
    }

    public boolean isSameSize (ArrayBox<?> another){
        return figures.length==another.getContent().length;

    }





}
