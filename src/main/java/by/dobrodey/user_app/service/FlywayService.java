package by.dobrodey.user_app.service;

import org.flywaydb.core.Flyway;

import static by.dobrodey.user_app.data.Properties.*;

public class FlywayService {

    private Flyway flyway;

    public FlywayService() {
        inti();
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() {
        flyway.clean();
    }

    private void inti() {
        flyway = Flyway.configure()
                .dataSource(URL, USER, PSW)
                .schemas(SHEMA)
                .locations(MIGRATIONS_LOCATION)
                .load();
    }
}
