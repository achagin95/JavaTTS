package net.thumbtack.school.elections.exceptions;

public enum RegErrorCode {

    REGISTER_WRONG_FIRSTNAME("REGISTER_WRONG_FIRSTNAME"),
    REGISTER_WRONG_LASTNAME("REGISTER_WRONG_LASTNAME"),
    REGISTER_WRONG_LOGIN("REGISTER_WRONG_LOGIN"),
    REGISTER_WRONG_PASSWORD("REGISTER_WRONG_PASSWORD"),
    REGISTER_ERROR("REGISTER_ERROR"),
    WRONG_LOGIN("WRONG_LOGIN"),
    WRONG_PASSWORD("WRONG_PASSWORD"),
    DUPLICATE_VOTER("DUPLICATE_VOTER"),
    VOTER_NOT_FOUND("VOTER_NOT_FOUND"),
    UUID_NOT_FOUND("UUID_NOT_FOUND"),
    EMPTY_CANDIDATE_LIST("EMPTY_CANDIDATE_LIST"),
    OPPOSITE_ALL_WIN("OPPOSITE_ALL_WIN"),
    WRONG_ADDRESS("WRONG_ADDRESS"),
    WRONG_HOME_NUMBER("WRONG_HOME_NUMBER"),
    ERROR("ERROR");

    private String errorCode;

    RegErrorCode(String errorCode) {
        this.errorCode=errorCode;
    }
    public String getErrorCode () {
        return errorCode;
    }


}