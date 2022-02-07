package velykyi.vladyslav.model;

import java.util.Date;

public interface Event extends Model {
    /**
     * Event id. UNIQUE.
     * @return Event Id
     */
    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    Date getDate();

    void setDate(Date date);
}
