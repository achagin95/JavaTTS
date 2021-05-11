package net.thumbtack.school.database.model;


import java.util.Objects;

public class Trainee {

    private int id;
    private String firstName, lastName;
    private int raiting;

    public Trainee() {

    }

    public Trainee(int id, String firstName, String lastName, int rating) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;
        Trainee trainee = (Trainee) o;
        return getId() == trainee.getId() &&
                raiting == trainee.raiting &&
                Objects.equals(getFirstName(), trainee.getFirstName()) &&
                Objects.equals(getLastName(), trainee.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), raiting);
    }

    public Trainee(String firstName, String lastName, int rating) {
        id=0;
        setFirstName(firstName);
        setLastName(lastName);
        setRating(rating);

    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;

    }

    public int getRating() {
        return raiting;
    }

    public void setRating(int rating){
        this.raiting=rating;
    }

    public String getFullName() {
        return getFirstName().concat(" ").concat(getLastName());
    }

    @Override
    public String toString() {
        return "Trainee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", raiting="
                + raiting + "]";
    }
}
