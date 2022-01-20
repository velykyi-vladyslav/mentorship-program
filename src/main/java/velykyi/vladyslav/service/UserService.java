package velykyi.vladyslav.service;

import velykyi.vladyslav.model.User;

import java.util.List;

public interface UserService {
    User getUserById(long eventId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(long userId);
}
