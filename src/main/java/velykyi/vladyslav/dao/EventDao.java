package velykyi.vladyslav.dao;

import org.springframework.stereotype.Component;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.Event;

import static velykyi.vladyslav.dao.DaoKeyName.EVENT_PREFIX;

@Component
public class EventDao extends AbstractDao<Event> {

    public EventDao(MapStorage mapStorage) {
        super(mapStorage);
    }

    @Override
    public Event map(Object o) {
        return (Event) o;
    }

    @Override
    public String getPrefix() {
        return EVENT_PREFIX;
    }
}
