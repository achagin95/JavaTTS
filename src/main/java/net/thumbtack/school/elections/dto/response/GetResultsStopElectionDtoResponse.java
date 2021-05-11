package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.database.DataBase;

import java.util.HashSet;
import java.util.Set;

public class GetResultsStopElectionDtoResponse {

    private Set<CandidatesResultDto> setCandidates=new HashSet<>();
    private int votersVoted, votersAll;

    public GetResultsStopElectionDtoResponse() {
    }

    public Set<CandidatesResultDto> getSetCandidates() {
        return setCandidates;
    }

    public void setSetCandidates(Set<CandidatesResultDto> setCandidates) {
        this.setCandidates = setCandidates;
    }

    public int getVotersVoted() {
        return votersVoted;
    }

    public void setVotersVoted(int votersVoted) {
        this.votersVoted = votersVoted;
    }

    public int getVotersAll() {
        return votersAll;
    }

    public void setVotersAll(int votersAll) {
        this.votersAll = votersAll;
    }
}
