package egc.atmservice.exception;

public class AtmException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AtmException(String message) {
        super(message);
    }
}
