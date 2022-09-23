package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Class User Dao
 * @author Olga Dobrodey
 */
public interface UserDao {

    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAll();
    void deleteById(Integer id);
    void deleteAll();
}
