package by.dobrodey.user_app.dao.impl;

import by.dobrodey.user_app.dao.RoleDao;
import by.dobrodey.user_app.model.Role;

import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }
}
