package velykyi.vladyslav.dao;

import org.springframework.stereotype.Component;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.User;

import java.util.ArrayList;
import java.util.List;

import static velykyi.vladyslav.dao.DaoKeyName.USER_PREFIX;

@Component
public class UserDao extends AbstractDao<User>{

    protected UserDao(MapStorage mapStorage) {
        super(mapStorage);
    }

    @Override
    public User map(Object o) {
        return (User) o;
    }

    @Override
    public String getPrefix() {
        return USER_PREFIX;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(getStorage().values());
    }
}
