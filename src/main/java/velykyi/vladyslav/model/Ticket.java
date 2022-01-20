package velykyi.vladyslav.model;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket extends Model{
    public enum Category {STANDARD, PREMIUM, BAR}

    long getId();
    void setId(long id);
    long getEventId();
    void setEventId(long eventId);
    long getUserId();
    void setUserId(long userId);
    Category getCategory();
    void setCategory(Category category);
    int getPlace();
    void setPlace(int place);

}
