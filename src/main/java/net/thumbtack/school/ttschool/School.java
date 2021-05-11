package net.thumbtack.school.ttschool;

import java.util.*;

public class School {

    private String schoolName;
    private int year;
    private Set<Group> setGroup;

    public School(String name, int year) throws TrainingException {
        setName(name);
        setYear(year);
        setGroup = new TreeSet<>();

    }

    public String getName() {
        return schoolName;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0)
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        schoolName = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Group> getGroups() {
        return setGroup;
    }

    //7
    public void addGroup(Group group) throws TrainingException {
        if (setGroup.contains(group))
            throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        setGroup.add(group);

    }

    public void removeGroup(Group group) throws TrainingException {
        if (!setGroup.contains(group))
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        setGroup.remove(group);


    }

    public void removeGroup(String name) throws TrainingException {
        Group test=new Group(name, "test");
        if (!setGroup.contains(test))
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        setGroup.remove(test);
    }

    public boolean containsGroup(Group group) {
        return setGroup.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                Objects.equals(schoolName, school.schoolName) &&
                Objects.equals(setGroup, school.setGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, year, setGroup);
    }

}
