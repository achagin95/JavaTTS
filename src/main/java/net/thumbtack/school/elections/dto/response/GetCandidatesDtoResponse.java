package net.thumbtack.school.elections.dto.response;

import java.util.HashSet;
import java.util.Set;

public class GetCandidatesDtoResponse {

    private Set<CandidatesDto> candidatesDtoSet=new HashSet<>();

    public GetCandidatesDtoResponse() {
    }

    public Set<CandidatesDto> getCandidates() {
        return candidatesDtoSet;
    }
}
