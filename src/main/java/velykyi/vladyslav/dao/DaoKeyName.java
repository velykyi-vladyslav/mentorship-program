package velykyi.vladyslav.dao;

public class DaoKeyName {
    public static final String EVENT_PREFIX = "event:";
    public static final String TICKET_PREFIX = "ticket:";
    public static final String USER_PREFIX = "user:";

    private DaoKeyName(){
        throw new IllegalStateException("Utility class");
    }
}
