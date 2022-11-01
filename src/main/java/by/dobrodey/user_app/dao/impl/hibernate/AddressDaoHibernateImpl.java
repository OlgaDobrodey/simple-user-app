package by.dobrodey.user_app.dao.impl.hibernate;

import by.dobrodey.user_app.dao.AddressDao;
import by.dobrodey.user_app.model.Address;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class AddressDaoHibernateImpl implements AddressDao {

    private final SessionFactory sessionFactory;

    private static final String SELECT_ALL_QUERY = "from Address a";

    @Override
    public List<Address> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Address> addresses = session.createQuery(SELECT_ALL_QUERY, Address.class).list();
            transaction.commit();
            return addresses;
        }
    }
}
