package gok.dao.impl;

import gok.dao.UserDao;
import gok.model.User;
import gok.util.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getUserAll(User user) throws Exception {
        List<Object> objectList = JDBCUtil.queryAll(user);
//        System.out.println(objectList);
        ArrayList<User> userList = new ArrayList<>();

        for (Object object : objectList) {
            if (object instanceof User){
                User u = (User) object;
                userList.add(u);
            }
        }
        return userList;
    }

    @Override
    public int addUser(User user) throws Exception {
        int a = JDBCUtil.addAll(user);
        return a;
    }
}
