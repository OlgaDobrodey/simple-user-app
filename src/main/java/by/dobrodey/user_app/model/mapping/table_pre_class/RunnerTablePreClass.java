package by.dobrodey.user_app.model.mapping.table_pre_class;

import by.dobrodey.user_app.data.BaseConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RunnerTablePreClass {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = BaseConnection.getInstanceHibernate();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Programmer programmer = Programmer.builder()
                    .firstName("Programmer")
                    .lastName("LastName")
                    .language(Language.JAVA)
                    .build();

            session.save(programmer);

            Manager manager = Manager.builder()
                    .firstName("Manager")
                    .lastName("Last Name")
                    .projectName("Project Name")
                    .build();

            session.save(manager);
            session.flush();
            session.clear();

            System.out.println(session.get(Employee.class,1));
            System.out.println(session.get(Programmer.class,1));
            System.out.println(session.get(Manager.class,2));

            session.getTransaction().commit();
        }
    }
}

