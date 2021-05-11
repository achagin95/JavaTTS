package net.thumbtack.school.ttschool;

import java.util.*;

public class TraineeMap {

    private Map<Trainee, String> traineeMap;

    public TraineeMap() {
        traineeMap = new TreeMap<Trainee, String>((t1, t2) -> {
            return t1.getFullName().compareTo(t2.getFullName());
        });

    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (traineeMap.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        traineeMap.put(trainee, institute);

    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (!traineeMap.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        traineeMap.replace(trainee, institute);

    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if (!traineeMap.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        traineeMap.remove(trainee);

    }

    public int getTraineesCount() {
        return traineeMap.size();

    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if (!traineeMap.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return traineeMap.get(trainee);

    }

    public Set<Trainee> getAllTrainees() {
        return traineeMap.keySet();

    }

    public Set<String> getAllInstitutes() {
        Set<String> rez=new TreeSet<>();
        for (String i : traineeMap.values()) {
            rez.add(i);
        }

        return rez;

    }

    public boolean isAnyFromInstitute(String institute) {
        return traineeMap.values().contains(institute);

    }

}
