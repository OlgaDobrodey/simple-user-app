package by.dobrodey.user_app.dao.impl.hibernate;

import by.dobrodey.user_app.dao.RoleDao;
import by.dobrodey.user_app.model.Role;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RoleDaoHibernateImpl implements RoleDao {
    private static final String SELECT_ALL_QUERY = "FROM Role r";

    private final SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Role> roles = session.createQuery(SELECT_ALL_QUERY, Role.class).list();
            transaction.commit();
            return roles;
        }
    }

    @Override
    public Optional<Role> findById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            transaction.commit();

            return Optional.ofNullable(role);
        }
    }
}
