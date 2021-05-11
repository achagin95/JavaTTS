package net.thumbtack.school.elections.exceptions;

public class RegException extends Exception {

    private RegErrorCode re;

    public RegException(RegErrorCode re) {
        this.re=re;
    }


    public RegErrorCode getErrorCode (){
        return re;
    }

}
