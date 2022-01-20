package velykyi.vladyslav.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.dao.UserDao;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(long eventId) {
        return userDao.get(eventId);
    }

    @Override
    public User getUserByEmail(String email){
        return userDao.getAllUsers().stream()
                                    .filter(user -> user.getEmail().equals(email))
                                    .findFirst()
                                    .orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name){
        return userDao.getAllUsers().stream()
                                    .filter(user -> user.getName()
                                    .equals(name))
                                    .collect(toList());
    }

    @Override
    public User createUser(User user){
        userDao.save(user);
        return userDao.get(user.getId());
    }

    @Override
    public User updateUser(User user){
        return userDao.update(user, user);
    }

    @Override
    public boolean deleteUser(long userId){
        userDao.delete(userId);
        return true;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
