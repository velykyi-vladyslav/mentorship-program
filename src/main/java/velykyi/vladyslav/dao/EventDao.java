package velykyi.vladyslav.dao;

import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.Event;

import java.util.List;
import java.util.ArrayList;

import static velykyi.vladyslav.dao.DaoKeyName.EVENT_PREFIX;

public class EventDao extends AbstractDao<Event> {

    @Autowired
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

    public List<Event> getAllEvents(){
        return new ArrayList<>(super.getStorage().values());
    }
}
