package net.thumbtack.school.elections.dto.response;

import java.util.HashSet;
import java.util.Set;

public class GetAdviceDtoResponse {

    private Set<AdviceDto> adviceDtoSet=new HashSet<>();

    public GetAdviceDtoResponse() {
    }

    public Set<AdviceDto> getGetAdviceDtoSet() {
        return adviceDtoSet;
    }



}
