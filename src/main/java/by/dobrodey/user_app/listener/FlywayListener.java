package by.dobrodey.user_app.listener;

import by.dobrodey.user_app.service.FlywayService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlywayListener implements ServletContextListener {

    private FlywayService flyway;

    public void contextInitialized(ServletContextEvent sce) {
        flyway = new FlywayService();
        flyway.migrate();
    }
}
