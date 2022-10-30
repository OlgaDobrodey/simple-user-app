package by.dobrodey.user_app.model.mapping.single_table;

import by.dobrodey.user_app.data.BaseConnection;
import by.dobrodey.user_app.model.mapping.table_pre_class.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RunnerSingleTable {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = BaseConnection.getInstanceHibernate();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            ProgrammerST programmer = ProgrammerST.builder()
                    .firstName("Programmer")
                    .lastName("LastName")
                    .language(Language.JAVA)
                    .build();

            session.save(programmer);

            ManagerST manager = ManagerST.builder()
                    .firstName("Manager")
                    .lastName("Last Name")
                    .projectName("Project Name")
                    .build();

            session.save(manager);
            session.flush();
            session.clear();

            System.out.println(session.get(SingleTable.class, 1));
            System.out.println(session.get(ProgrammerST.class, 1));
            System.out.println(session.get(ManagerST.class, 2));

            session.getTransaction().commit();
        }
    }
}
