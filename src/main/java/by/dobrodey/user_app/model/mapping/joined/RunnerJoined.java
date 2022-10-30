package by.dobrodey.user_app.model.mapping.joined;

import by.dobrodey.user_app.data.BaseConnection;
import by.dobrodey.user_app.model.mapping.table_pre_class.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RunnerJoined {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = BaseConnection.getInstanceHibernate();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            ProgrammerJ programmer = ProgrammerJ.builder()
                    .firstName("Programmer")
                    .lastName("LastName")
                    .language(Language.JAVA)
                    .build();

            session.save(programmer);

            ManagerJ manager = ManagerJ.builder()
                    .firstName("Manager")
                    .lastName("Last Name")
                    .projectName("Project Name")
                    .build();

            session.save(manager);
            session.flush();
            session.clear();

            System.out.println(session.get(Joined.class, 1));
            System.out.println(session.get(ProgrammerJ.class, 1));
            System.out.println(session.get(ManagerJ.class, 2));

            session.getTransaction().commit();
        }
    }
}
