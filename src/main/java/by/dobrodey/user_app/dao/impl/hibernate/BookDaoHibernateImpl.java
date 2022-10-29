package by.dobrodey.user_app.dao.impl.hibernate;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.model.Book;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BookDaoHibernateImpl implements BookDao {
    private static final String USER_ID = "userId";
    private static final String SELECT_ALL_QUERY = "from Book b";
    private static final String SELECT_ALL_BOOKS_FOR_USER = "select b from User u join u.bookList b where u.id =:userId";

    private final SessionFactory sessionFactory;

    /**
     * Method find all book in library
     *
     * @return list of books and list empty - if library doesn't have books
     */
    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Book> books = session.createQuery(SELECT_ALL_QUERY, Book.class).list();
            transaction.commit();
            return books;
        }
    }

    /**
     * Fond book for id
     *
     * @param id - id book
     * @return Optional book for id
     */
    @Override
    public Optional<Book> findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Optional<Book> book = Optional.ofNullable(session.get(Book.class, id));
            transaction.commit();
            return book;
        }
    }

    /**
     * find all book for user by id
     *
     * @param userId
     * @return List<Book>
     */
    @Override
    public List<Book> findAllBookByUserId(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Book> books = session.createQuery(SELECT_ALL_BOOKS_FOR_USER, Book.class)
                    .setParameter(USER_ID, userId)
                    .getResultList();
            transaction.commit();
            return books;
        }
    }
}
