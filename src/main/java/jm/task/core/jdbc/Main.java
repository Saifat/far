package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Pupkin", (byte) 22);
        userDao.saveUser("Petya", "Pechkin", (byte) 40);
        userDao.saveUser("Nikola", "Tesla", (byte) 1);
        userDao.saveUser("Violetta", "Petrovna", (byte) 12);
        for (User us : userDao.getAllUsers()){
            System.out.println(us);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }
}
