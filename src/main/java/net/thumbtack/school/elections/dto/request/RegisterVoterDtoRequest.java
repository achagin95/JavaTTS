package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.RegErrorCode;
import net.thumbtack.school.elections.exceptions.RegException;

import java.util.Objects;

public class RegisterVoterDtoRequest {


    private String firstName, lastName, middleName;
    private String login;
    private String password;
    private String address;


    private static final int MIN_PASSWORD_LEN = 6;


    public RegisterVoterDtoRequest(String firstName, String lastName, String middleName, String login, String password, String address) {
        setFirstName(firstName);
        setLastName(lastName);
        setMiddleName(middleName);
        setLogin(login);
        setPassword(password);
        setAddress(address);
        //setHomeNumber(homeNumber);



    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)  {

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)  {

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
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    

    public String getFullName() {
        if (getMiddleName().length() == 0)
            return getFirstName().concat(" ").concat(getLastName());
        else return getFirstName().concat(" ").concat(getLastName()).concat(" ").concat(getMiddleName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterVoterDtoRequest that = (RegisterVoterDtoRequest) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, login, password);
    }



    public void valuable() throws RegException {

        if (getFirstName() == null || getFirstName().length() == 0)
            throw new RegException(RegErrorCode.REGISTER_WRONG_FIRSTNAME);
        if (getLastName() == null || getLastName().length() == 0)
            throw new RegException(RegErrorCode.REGISTER_WRONG_LASTNAME);
        if (getLogin() == null || getLogin().length() == 0)
            throw new RegException(RegErrorCode.REGISTER_WRONG_LOGIN);
        if (getPassword() == null || getPassword().length() < MIN_PASSWORD_LEN)
            throw new RegException(RegErrorCode.REGISTER_WRONG_PASSWORD);
        if (getAddress()==null || getAddress().length()==0)
            throw new RegException(RegErrorCode.WRONG_ADDRESS);



    }


}

