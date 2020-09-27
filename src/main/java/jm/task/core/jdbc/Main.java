package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Pupkin", (byte) 22);
        userDao.saveUser("Petya", "Pechkin", (byte) 40);
        userDao.saveUser("Nikola", "Tesla", (byte) 1);
        userDao.saveUser("Violetta", "Petrovna", (byte) 12);
        for (User s : userDao.getAllUsers()){
            System.out.println(s);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();





    }
}
