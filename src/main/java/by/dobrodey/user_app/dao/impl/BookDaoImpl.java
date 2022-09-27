package by.dobrodey.user_app.dao.impl;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAllBookByUserId(Integer idUser) {
        return null;
    }
}
