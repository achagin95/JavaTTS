package net.thumbtack.school.elections.dto.request;

public class AddChangeScoreToAdviceDto {
    String uuidUser, uuidByAdvice;
    int score, numberAdvice;

    public AddChangeScoreToAdviceDto(String uuidUser, String uuidByAdvice, int score, int numberAdvice) {
        setUuidUser(uuidUser);
        setUuidByAdvice(uuidByAdvice);
        setScore(score);
        setNumberAdvice(numberAdvice);
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getUuidByAdvice() {
        return uuidByAdvice;
    }

    public void setUuidByAdvice(String uuidByAdvice) {
        this.uuidByAdvice = uuidByAdvice;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberAdvice() {
        return numberAdvice;
    }

    public void setNumberAdvice(int numberAdvice) {
        this.numberAdvice = numberAdvice;
    }
}
