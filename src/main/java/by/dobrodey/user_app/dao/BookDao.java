package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll();
    Optional<Book> findById(Integer id);
    List<Book> findAllBookByUserId(Integer idUser);


}
