package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {

    WRONG_COLOR_STRING("WRONG_COLOR_STRING"),
    NULL_COLOR("NULL_COLOR");

    private String errorString1;

    ColorErrorCode(String errorString) {this.errorString1=errorString;}
    public String getErrorColor() {
        return errorString1;

    }


}
