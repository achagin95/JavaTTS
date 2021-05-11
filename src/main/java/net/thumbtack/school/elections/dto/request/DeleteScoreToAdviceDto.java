package net.thumbtack.school.elections.dto.request;

public class DeleteScoreToAdviceDto {

    String uuidUser, uuidByAdvice;
    int numberAdvice;

    public DeleteScoreToAdviceDto(String uuidUser, String uuidByAdvice, int numberAdvice) {
        setUuidUser(uuidUser);
        setUuidByAdvice(uuidByAdvice);
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

    public int getNumberAdvice() {
        return numberAdvice;
    }

    public void setNumberAdvice(int numberAdvice) {
        this.numberAdvice = numberAdvice;
    }
}
