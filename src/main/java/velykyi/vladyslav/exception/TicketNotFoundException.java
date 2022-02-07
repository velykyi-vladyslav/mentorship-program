package velykyi.vladyslav.exception;

public class TicketNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Ticket is not found";

    public TicketNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TicketNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
