package jm.task.core.jdbc.util;
import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/newbase?useUnicode=true&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASS = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();

                Properties setting = new Properties();
                setting.put(Environment.DRIVER, DRIVER);
                setting.put(Environment.URL, URL);
                setting.put(Environment.USER, USERNAME);
                setting.put(Environment.PASS, PASS);
                setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                setting.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(setting);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }


}
