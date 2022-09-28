package by.dobrodey.user_app.dao.impl;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.model.Book;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class consist on methods for work with model Book
 */
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private static final String ID_BOOK_COLUMN = "id";
    private static final String TITLE_BOOK_COLUMN = "title";
    private static final String WRITER_BOOK_COLUMN = "writer";
    private static final String CROSS_TABLE_ID_BOOK = "book_id";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM book";
    private static final String SELECT_BOOK_BY_ID_QUERY = "SELECT * FROM book WHERE id = ";

    private static final String SELECT_ALL_BOOKS_FOR_USER = "SELECT book_id FROM users_book WHERE users_id = ";


    private final DataSource dataSource;

    /**
     * Method find all book in library
     *
     * @return list of books and list empty - if library doesn't have books
     * @throws SQLException
     */
    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                Book book = getBook(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new SQLException("ERROR: SELECT ALL TASK: " + e);
        }
        return books;
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .bookId(resultSet.getInt(ID_BOOK_COLUMN))
                .author(resultSet.getString(WRITER_BOOK_COLUMN))
                .title(resultSet.getString(TITLE_BOOK_COLUMN))
                .build();
    }

    /**
     * Fond book for id
     *
     * @param id - id book
     * @return Optional book for id
     * @throws SQLException
     */
    @Override
    public Optional<Book> findById(Integer id) throws SQLException {
        Book book = null;

        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_BOOK_BY_ID_QUERY + id)) {

            if (resultSet.next()) {
                book = getBook(resultSet);
                if (resultSet.next()) {
                    throw new SQLIntegrityConstraintViolationException("Count books more one");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: SELECT TASK BY ID: " + ex);
        }
        return Optional.ofNullable(book);
    }

    /**
     * find all book for user by id
     *
     * @param userId
     * @return List<Optional < Book>
     * @throws SQLException
     */
    @Override
    public List<Book> findAllBookByUserId(Integer userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_BOOKS_FOR_USER + userId)) {

            while (resultSet.next()) {
                Optional<Book> book = findById(resultSet.getInt(CROSS_TABLE_ID_BOOK));
                book.ifPresent(books::add);
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: SELECT ALL BOOK FOR USER: ", ex);
        }
        return books;
    }
}

