package by.dobrodey.user_app.dao;

import by.dobrodey.user_app.model.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoleDao {
    List<Role> findAll() throws SQLException;
    Optional<Role> findById(Integer id) throws SQLException;
}
