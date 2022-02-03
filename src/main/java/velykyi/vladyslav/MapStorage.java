package velykyi.vladyslav;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.stereotype.Component;

@Component
public class MapStorage {
    private final DB mapDb;

    private MapStorage() {
        this.mapDb = DBMaker.fileDB("file").closeOnJvmShutdown().checksumHeaderBypass().make();
    }

    public DB getMapDb() {
        return mapDb;
    }
}
