package net.thumbtack.school.ttschool;

import java.io.Serializable;
import java.util.Objects;

public class Trainee implements Serializable {

    private static final long serialVersionUID = 803745049486954915L;

    private String firstName, lastName;
    private int raiting;


    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);

    }


    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) throws TrainingException {
        if (firstName == null || firstName.length() == 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }
        else this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;

    }

    public void setLastName(String lastName) throws TrainingException {
        if (lastName==null||lastName.length()==0)
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        this.lastName=lastName;

    }

    public int getRating() {
        return raiting;

    }

    public void setRating(int rating) throws TrainingException {
        if(rating<1 || rating >5)
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        this.raiting=rating;

    }

    public String getFullName() {
        return getFirstName().concat(" ").concat(getLastName());

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return raiting == trainee.raiting &&
                Objects.equals(firstName, trainee.firstName) &&
                Objects.equals(lastName, trainee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, raiting);
    }
}
