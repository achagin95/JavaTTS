package net.thumbtack.school.elections.dto.request;

public class LoginDto {

    private String login, password;

    public LoginDto(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
