package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.User;

import java.util.List;
import java.util.Optional;

import static by.dobrodey.user_app.data.AllUsers.getAllUsers;

/**
 * Implementation UserDao class
 * @author Olga Dobrodey
 */
public class UserDaoImpl implements UserDao {


    /**
     * Add new user
     *
     * @param user
     * @return user
     */
    @Override
    public User save(User user) {
        Integer idUser = createUserId();
        user.setId(idUser);
        getAllUsers().add(user);
        return user;
    }

    private Integer createUserId() {
        return getAllUsers().size()+1;
    }

    /**
     * Find user by id
     *
     * @param id
     * @return user - optional of user
     */
    @Override
    public Optional<User> findById(Integer id) {
        return getAllUsers().stream().filter(user -> user.getId().equals(id))
                .findAny();
    }

    /**
     * Find all users
     *
     * @return list of users
     */
    @Override
    public List<User> findAll() {
        return getAllUsers();
    }

    /**
     * Delete user by id
     *
     * @param id - user's id to be deleted
     */
    @Override
    public void deleteById(Integer id) {
        Optional<User> anyUser = getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
        anyUser.ifPresent(user -> getAllUsers().remove(user));
    }

    /**
     * Delete all users
     */
    @Override
    public void deleteAll() {
        getAllUsers().clear();
    }
}
