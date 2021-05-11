package net.thumbtack.school.ttschool;

public class TrainingException extends Exception{

    private TrainingErrorCode ec;

    public TrainingException (TrainingErrorCode ec) {
        this.ec=ec;
    }


    public TrainingErrorCode getErrorCode (){
        return ec;
    }



    /*public TrainingException(TrainingErrorCode errorCode, String param) {
        super(String.format(errorCode.getErrorCode(), param));
    }

     */

}
