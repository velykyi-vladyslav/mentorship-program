package velykyi.vladyslav.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by maksym_govorischev.
 */
public interface Ticket extends Model{
    enum Category {@JsonProperty("standart") STANDARD, @JsonProperty("premium") PREMIUM, @JsonProperty("bar") BAR}

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
