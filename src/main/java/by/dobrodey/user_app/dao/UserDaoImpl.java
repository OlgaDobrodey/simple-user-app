package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.User;

import java.util.List;
import java.util.Optional;

import static by.dobrodey.user_app.data.AllUsers.getAllUsers;

public class UserDaoImpl implements UserDao {

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

    @Override
    public Optional<User> findById(Integer id) {
        return getAllUsers().stream().filter(user -> user.getId().equals(id))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return getAllUsers();
    }

    @Override
    public void deleteById(Integer id) {
        Optional<User> anyUser = getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
        anyUser.ifPresent(user -> getAllUsers().remove(user));
    }

    @Override
    public void deleteAll() {
        getAllUsers().clear();
    }
}
