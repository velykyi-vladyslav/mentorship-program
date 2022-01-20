package velykyi.vladyslav;

import org.mapdb.DB;
import org.mapdb.DBMaker;


public class MapStorage {
    private final DB mapDb;

   // @Value("${file:test.db}")
    private static final String FILE_NAME = "storage";

    private MapStorage() {
        this.mapDb = DBMaker.fileDB(FILE_NAME).closeOnJvmShutdown().make();
    }

    public DB getMapDb() {
        return mapDb;
    }

    public void initBean() {
        System.out.println("Init Bean for : EmployeeDAOImpl");
    }
}
