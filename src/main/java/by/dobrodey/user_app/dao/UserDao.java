package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Class User Dao
 * @author Olga Dobrodey
 */
public interface UserDao {

    User save(User user) throws SQLException;
    Optional<User> findById(Integer id) throws SQLException;
    List<User> findAll() throws SQLException;
    void deleteById(Integer id) throws SQLException;
    void deleteAll() throws SQLException;

}
