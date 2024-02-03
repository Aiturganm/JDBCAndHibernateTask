package peaksoft.service;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();

    public void createUsersTable() {
        try {
            userDao.createUsersTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            userDao.dropUsersTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDao.saveUser(name, lastName, age);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            userDao.removeUserById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
