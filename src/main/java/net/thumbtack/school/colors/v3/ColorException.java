package net.thumbtack.school.colors.v3;

public class ColorException extends Exception {

    private ColorErrorCode pe;

    public ColorException(ColorErrorCode errorCode) {
        pe=errorCode;
    }

    public ColorErrorCode getErrorCode() {
        return pe;


    }




}
