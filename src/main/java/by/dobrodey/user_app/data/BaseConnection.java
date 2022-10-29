package by.dobrodey.user_app.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;

import static by.dobrodey.user_app.data.Properties.DRIVER;
import static by.dobrodey.user_app.data.Properties.PSW;
import static by.dobrodey.user_app.data.Properties.URL;
import static by.dobrodey.user_app.data.Properties.USER;

public class BaseConnection {

    private static DataSource dataSource;
    private static SessionFactory sessionFactory;
    private static HikariConfig config = new HikariConfig();

    private BaseConnection() {
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            synchronized (BaseConnection.class) {
                if (dataSource == null) {
                    dataSource = getDataSource();
                }

            }
        }
        return dataSource;
    }

    public static SessionFactory getInstanceHibernate() {
        if (sessionFactory == null) {
            synchronized (BaseConnection.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    private static DataSource getDataSource() {
        try {
            Class.forName(DRIVER);
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PSW);
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
