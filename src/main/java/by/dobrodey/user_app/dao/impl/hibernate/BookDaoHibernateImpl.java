package by.dobrodey.user_app.dao.impl.hibernate;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.model.Book;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BookDaoHibernateImpl implements BookDao {
    private static final String USER_ID = "userId";
    private static final String SELECT_ALL_QUERY = "from Book b";
    private static final String SELECT_ALL_BOOKS_FOR_USER = "select b from User u join u.bookList b where u.id =:userId";
    private static final String SELECT_ALL_BOOKS_BY_PAGES_MORE = "from Book b where b.pages > ";

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
            List<Book> books = session.createQuery(SELECT_ALL_QUERY, Book.class).setMaxResults(10).list();
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

    @Override
    public List<Book> findAllBookWhereCountPagesMore(Integer pages) {

        try (Session session = sessionFactory.openSession()) {
            Date finishLinked = new Date();
            Transaction transaction = session.beginTransaction();
            int count = count();
            List<Book> books = new LinkedList<>();
            int i = 0;
            while (i <= count) {
                Date finishLinked1 = new Date();

                List<Book> list = session
                        .createQuery(SELECT_ALL_BOOKS_BY_PAGES_MORE + pages, Book.class)
                        .setFirstResult(i).setMaxResults(i + 99999).list();
                session.clear();
                list = null;

                i += 100000;
                System.out.println("==================== " + i + " ===============");
                System.out.println(new Date().getTime() - finishLinked1.getTime());
            }

            transaction.commit();
            Date endLinked = new Date();
            System.out.println("Time get Book " + (endLinked.getTime() - finishLinked.getTime()));
            System.out.println("Count = " + count);
            return books;
        }
    }

    private int count() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long count = (Long) session.createQuery("select count(*) from Book b").list().get(0);
            transaction.commit();
            return Math.toIntExact(count);
        }
    }
}
