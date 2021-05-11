package net.thumbtack.school.elections.dto.response;

public class CandidatesResultDto {

    private int numberOfCandidate, score;
    private String fullName;

    public CandidatesResultDto(String fullName, int numberOfCandidate, int score) {
        setFullName(fullName);
        setNumberOfCandidate(numberOfCandidate);
        setScore(score);
    }

    public int getNumberOfCandidate() {
        return numberOfCandidate;
    }

    public void setNumberOfCandidate(int numberOfCandidate) {
        this.numberOfCandidate = numberOfCandidate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
