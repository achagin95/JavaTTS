package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.model.Voter;

public class AddCandidateDto {

    private String uuid;
    private Voter voter;

    public AddCandidateDto(String uuid, Voter voter) {
        setUuid(uuid);
        setVoter(voter);
    }

    public String getUuid() {
        return uuid;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}
