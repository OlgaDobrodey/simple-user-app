package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    List<Role> findAll();
    Optional<Role> findById(Integer id);
}
