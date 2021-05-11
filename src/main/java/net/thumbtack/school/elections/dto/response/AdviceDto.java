package net.thumbtack.school.elections.dto.response;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdviceDto {

    private String advice;
    private Map<String,Integer> score = new HashMap<>();
    private double average;

    public AdviceDto(String advice, Map<String,Integer> score, double average) {
        setAdvice(advice);
        setScore(score);
        setAverage(average);
    }

    public void setAverage(double average) {
        this.average=average;
    }

    public double getAverage() {
        return average;
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

    public void setScore(Map<String,Integer> score) {
        this.score = score;
    }


}
