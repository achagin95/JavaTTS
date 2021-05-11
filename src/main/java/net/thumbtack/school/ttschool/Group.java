package net.thumbtack.school.ttschool;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Group implements Comparable<Group>{

    private String groupName;
    private String room;
    private List<Trainee> studentsList;

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
        studentsList = new ArrayList<>();


    }

    public String getName() {
        return groupName;
    }


    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        groupName = name;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.length() == 0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;

    }

    public List<Trainee> getTrainees() {
        return studentsList;
    }


    public void addTrainee(Trainee trainee) {
        studentsList.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!studentsList.contains(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        //studentsList.remove(studentsList.indexOf(trainee));
        studentsList.remove(trainee);

    }

    public void removeTrainee(int index) throws TrainingException {
        if (index < 0 || index >= studentsList.size())
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        studentsList.remove(index);

    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        Trainee t = null;
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getFirstName().equals(firstName)) {
                t = studentsList.get(i);
                break;
            }
        }
        if (t != null)
            return t;
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);

    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        Trainee t = null;
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getFullName().equals(fullName)) {
                t = studentsList.get(i);
                break;
            }
        }
        if (t != null)
            return t;
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    //12
    public void sortTraineeListByFirstNameAscendant() {
        Collections.sort(studentsList, (t1, t2) -> t1.getFirstName().compareTo(t2.getFirstName()));


    }

    public void sortTraineeListByRatingDescendant() {
        //Collections.sort(studentsList, (t1,t2) -> Integer.toString(t1.getRating()).compareTo(Integer.toString(t2.getRating())));
        //Collections.reverse(studentsList);
        Collections.sort(studentsList, (t1, t2) -> t2.getRating() - t1.getRating());

    }

    public void reverseTraineeList() {
        Collections.reverse(studentsList);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(studentsList, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {

        List<Trainee> rez = new ArrayList<>();

        sortTraineeListByRatingDescendant();
        if (studentsList.size() != 0) {
            int max = studentsList.get(0).getRating();
            for (int i = 0; i < studentsList.size(); i++) {
                if (studentsList.get(i).getRating() == max) {
                    rez.add(studentsList.get(i));
                } else break;
            }

            if (rez.size() > 0)
                return rez;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);


    }

    public boolean hasDuplicates() {
        sortTraineeListByFirstNameAscendant();
        for (int i = 0; i < studentsList.size(); i++) {
            for (int j = 0; j < studentsList.size(); j++) {
                if (j == i)
                    continue;
                if (studentsList.get(i).getFullName().equals(studentsList.get(j).getFullName()) &&
                        studentsList.get(i).getRating() == studentsList.get(j).getRating()) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) &&
                Objects.equals(room, group.room) &&
                Objects.equals(studentsList, group.studentsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, room, studentsList);
    }

    public int compareTo (Group g) {
        return getName().compareTo(g.getName());
    }
}
