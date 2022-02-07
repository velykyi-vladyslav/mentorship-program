package velykyi.vladyslav.dao;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.Model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public abstract class AbstractDao<T extends Model> {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    private final DB db;

    private final ConcurrentMap<String, T> storage;

    protected String prefix;

    protected AbstractDao(MapStorage mapStorage) {
        this.db = mapStorage.getMapDb();
        this.storage = (ConcurrentMap<String, T>) openDb();
        this.prefix = getPrefix();
    }

    public void save(T model) {
        storage.put(prefix + model.getId(), model);

        logger.info("Saved entity: {}", model);
    }

    public T update(T oldEvent, T newEvent) {
        if (storage.replace(prefix + oldEvent.getId(), oldEvent, newEvent)) {
            logger.info("Updated event: {}.", newEvent);

            return newEvent;
        }
        logger.error("Failed to update event: {}.", oldEvent);

        return null;
    }

    public T get(long id) {
        logger.info("Getting {}{}", prefix, id);

        return map(storage.get(prefix + id));
    }

    public List<T> getAll(){
        return getStorage().entrySet().stream()
                           .filter(map -> map.getKey().contains(prefix))
                           .map(Map.Entry::getValue)
                           .collect(Collectors.toList());
    }

    public void delete(long id) {
        logger.error("Deleted item: {}{}", prefix, id);

        openDb().remove(prefix + id);
    }

    public abstract T map(Object o);

    public abstract String getPrefix();

    private HTreeMap<?, ?> openDb() {
        return db.hashMap("map").createOrOpen();
    }

    public ConcurrentMap<String, T> getStorage() {
        return storage;
    }
}
