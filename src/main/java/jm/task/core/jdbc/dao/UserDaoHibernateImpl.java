package jm.task.core.jdbc.dao;
import org.hibernate.SQLQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
       try(Session session = Util.getSessionFactory().openSession()) {
           session.beginTransaction();
           session.createSQLQuery("CREATE TABLE IF NOT EXISTS USER (id INT NOT NULL AUTO_INCREMENT, " +
                   "NAME VARCHAR (40), LASTNAME VARCHAR (40), AGE INT, PRIMARY KEY (id))").executeUpdate();
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public void dropUsersTable() {
       try(Session session = Util.getSessionFactory().openSession()) {
           session.beginTransaction();
           session.createSQLQuery("DROP TABLE IF EXISTS USER").executeUpdate();
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
      try(Session session = Util.getSessionFactory().openSession())
      {
          session.beginTransaction();
          session.save(new User(name, lastName, age));
          session.getTransaction().commit();
          System.out.println("User с именем " + name + " добавлен в базу данных");
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public void removeUserById(long id) {
       try(Session session = Util.getSessionFactory().openSession()) {
           session.beginTransaction();
           session.createSQLQuery("DELETE FROM USER WHERE ID = " + id);
           session.getTransaction().commit();
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
            try(Session session = Util.getSessionFactory().openSession()) {
                session.beginTransaction();
                list = session.createSQLQuery("SELECT * FROM USER").addEntity(User.class).list();
                session.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }

        return  list;

    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM USER").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
