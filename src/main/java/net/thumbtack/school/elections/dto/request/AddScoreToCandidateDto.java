package net.thumbtack.school.elections.dto.request;

public class AddScoreToCandidateDto {
    private String uuidUser;
    private int numberOfCandidate;

    public AddScoreToCandidateDto(String uuidUser, int numberOfCandidate) {
        setUuidUser(uuidUser);
        setNumberOfCandidate(numberOfCandidate);
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public int getNumberOfCandidate() {
        return numberOfCandidate;
    }

    public void setNumberOfCandidate(int numberOfCandidate) {
        this.numberOfCandidate = numberOfCandidate;
    }
}
