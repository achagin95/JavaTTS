package net.thumbtack.school.elections.dto.request;

public class AddAdviceDto {
    private String uuid;
    private String advice;

    public AddAdviceDto(String uuid, String advice) {
        setUuid(uuid);
        setAdvice(advice);
    }

    public String getUuid() {
        return uuid;
    }

    public String getAdvice() {
        return advice;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
