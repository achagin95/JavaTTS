package net.thumbtack.school.elections.model;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Voter {

    private String firstName, lastName, middleName;
    private String login;
    private String password;
    private Status status;
    private Set<Advice> setAdvice;
    private int score;
    private String address;


    public Voter(RegisterVoterDtoRequest rvdto) /*throws RegException*/ {
        setFirstName(rvdto.getFirstName());
        setLastName(rvdto.getLastName());
        setMiddleName(rvdto.getMiddleName());
        setLogin(rvdto.getLogin());
        setPassword(rvdto.getPassword());
        setStatus(Status.VOTER);
        setAdvice = new TreeSet<>((a1, a2) -> {
            return a1.getNumber() - a2.getNumber();
        });
        score = 0;
        setAddress(rvdto.getAddress());
    }

    public Voter(String firstName, String lastName, String middleName, String login, String password, String address)/* throws RegException*/ {
        setFirstName(firstName);
        setLastName(lastName);
        setMiddleName(middleName);
        setLogin(login);
        setPassword(password);
        setStatus(Status.VOTER);
        setAdvice = new TreeSet<>((a1, a2) -> {
            return a1.getNumber() - a2.getNumber();
        });
        score = 0;
        setAddress(address);
    }

    public Voter(String firstName, String lastName, String login, String password, String address)/* throws RegException*/ {
        setFirstName(firstName);
        setLastName(lastName);
        setMiddleName("");
        setLogin(login);
        setPassword(password);
        setStatus(Status.VOTER);
        setAdvice = new TreeSet<>((a1, a2) -> {
            return a1.getNumber() - a2.getNumber();
        });
        score = 0;
        setAddress(address);
    }

    public Voter(String fullName, String login, String password, String address)/* throws RegException*/ {
        String[] st = fullName.split(" ");
        setFirstName(st[0]);
        setLastName(st[1]);
        if (st.length == 3) {
            setMiddleName(st[2]);
        } else {
            setMiddleName("");
        }
        setLogin(login);
        setPassword(password);
        setStatus(Status.VOTER);
        setAdvice = new TreeSet<>((a1, a2) -> {
            return a1.getNumber() - a2.getNumber();
        });
        score = 0;
        setAddress(address);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null || middleName.length() == 0)
            this.middleName = "";
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getFullName() {
        if (getMiddleName().length() == 0 || getMiddleName() == null)
            return getFirstName().concat(" ").concat(getLastName());
        else return getFirstName().concat(" ").concat(getLastName()).concat(" ").concat(getMiddleName());
    }

    public Set<Advice> getSetAdvice() {
        return setAdvice;
    }


    public String voterToJson() {
        //для тестов
        Gson gs = new Gson();
        return gs.toJson(this);
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(firstName, voter.firstName) &&
                Objects.equals(lastName, voter.lastName) &&
                Objects.equals(middleName, voter.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }
}
