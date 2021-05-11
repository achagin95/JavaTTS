package net.thumbtack.school.elections.dto.request;

public class UuidDto {

    private String uuid;

    public UuidDto(String uuid) {
        setUuid(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
