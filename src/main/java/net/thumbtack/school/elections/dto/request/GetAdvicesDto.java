package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.model.Voter;

public class GetAdvicesDto {
    private String uuidUser;
    private Voter voterByAdvice;


    public GetAdvicesDto(String  uuidUser, Voter voterByAdvice) {
        setUuidUser(uuidUser);
        setVoterByAdvice(voterByAdvice);
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public Voter getVoterByAdvice() {
        return voterByAdvice;
    }

    public void setVoterByAdvice(Voter voterByAdvice) {
        this.voterByAdvice = voterByAdvice;
    }
}
