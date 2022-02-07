package velykyi.vladyslav.exception;

public class InvalidValueException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Provided invalid value";

    public InvalidValueException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidValueException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
