package by.dobrodey.user_app.dao.impl.hibernate;

import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.model.Role;
import by.dobrodey.user_app.model.User;
import by.dobrodey.user_app.proxy.UserProxy;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDaoHibernateImpl implements UserDao {
    private static final String SELECT_ALL_QUERY = "FROM User u";
    private static final String DELETE_ALL_QUERY = "DELETE FROM User u";

    private final SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.getDefaultRole());
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user != null ? proxyUser(user) : user;
    }

    @Override
    public Optional<User> findById(Integer id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(user == null ? user : proxyUser(user));
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery(SELECT_ALL_QUERY, User.class).list();
            session.getTransaction().commit();
            return users.stream().map(u -> proxyUser(u)).collect(Collectors.toList());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("attempt to create delete event with null entity");
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery(DELETE_ALL_QUERY).executeUpdate();
            session.getTransaction().commit();
        }
    }

    private User proxyUser(User user) {
        User proxy = new UserProxy().contextInitialized();
        proxy.setId(user.getId());
        proxy.setFirstName(user.getFirstName());
        proxy.setLastName(user.getLastName());
        proxy.setEmail(user.getEmail());
        proxy.setDateOfBirth(user.getDateOfBirth());
        proxy.setRole(user.getRole());
        return proxy;
    }
}
