package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll() throws SQLException;
    Optional<Book> findById(Integer id) throws SQLException;
    List<Book> findAllBookByUserId(Integer idUser) throws SQLException;


}
