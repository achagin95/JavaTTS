package net.thumbtack.school.boxes.v3;




import net.thumbtack.school.figures.v3.Figure;

import net.thumbtack.school.iface.v3.HasArea;


class Box<T extends Figure> implements HasArea {

    private T figure;

    public Box(T figure) {
        super();
        this.figure=figure;
    }

    public T getContent(){
        return figure;
    }

     public void setContent (T figure) {
        this.figure=figure;
     }


    @Override
    public double getArea() {

        return figure.getArea();

    }


    public boolean isAreaEqual(Box<?> another) {

        return getArea()==another.getArea();


    }
    static boolean isAreaEqual(Box<?> box1, Box<?> box2) {

        return box1.getArea()==box2.getArea();
    }





    //смотри класс тест: а.компарету(б) а второй: бокс.компарету(а,б)
    //почистить код, пройтись по замечаниям




}
