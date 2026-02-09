package exceptions;

public class GameException extends RuntimeException {
    private final String reason;

    public GameException(String reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return reason;
    }
}
