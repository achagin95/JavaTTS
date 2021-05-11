package net.thumbtack.school.elections.model;

import java.util.*;

public class Advice {
    private int number;
    private String advice;
    //private List<ScoreAdvice> score;
    private Map<String,Integer > score;//запишется ли в файл потом вотер в ключе как вотер, а не как ссылка
    //иначе взять лист или лучше сет, чтоб лишнего не добавлять
    //т.к. ключ является уникальным пришлось поменять местами, запишется ли вотер НЕссылкой в файл?

    public Advice() {
        //score = new ArrayList<>();
        score = new HashMap<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Map<String,Integer> getScore() {
        return score;
    }

    public double getAverage() {
        double rez = 0;
        //Set<Integer> set= (Set<Integer>) score.values();
        //List<Integer> list= (List<Integer>) score.values();
        List<Integer> list= new ArrayList<>(score.values());
        for (Integer integer : list) {
            rez = rez + integer;
        }
        return rez/score.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advice advice1 = (Advice) o;
        return Objects.equals(advice, advice1.advice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(advice);
    }
}
