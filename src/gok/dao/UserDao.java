package gok.dao;

import gok.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUserAll(User user)throws Exception;
    int addUser(User user) throws Exception;

    int deleteUser(List userId,User user) throws Exception;
}
