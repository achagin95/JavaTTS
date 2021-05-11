package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

public class CandidatesDto {

    private int numberOfCandidate;
    private String fio;

    public CandidatesDto(int numberOfCandidate, Voter voter) {
        setNumberOfCandidate(numberOfCandidate);
        setFio(voter.getFullName());
    }

    public int getNumberOfCandidate() {
        return numberOfCandidate;
    }

    public void setNumberOfCandidate(int numberOfCandidate) {
        this.numberOfCandidate = numberOfCandidate;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
