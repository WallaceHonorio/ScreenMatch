package honorio.screenmatch.exception;

public class YearConversationErrorException extends RuntimeException {

    private String message;

    public YearConversationErrorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
