package by.dobrodey.user_app.dao.impl;

import by.dobrodey.user_app.dao.RoleDao;
import by.dobrodey.user_app.model.Role;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class consist methods for model Role
 */
@AllArgsConstructor
@Deprecated
public class RoleDaoImpl implements RoleDao {

    private static final String ID_ROLE_COLUMN = "id";
    private static final String NAME_ROLE_COLUMN = "role_name";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM role";
    private static final String SELECT_ROLE_BY_ID_QUERY = "SELECT * FROM role WHERE id = ";

    private final DataSource dataSource;

    @Override
    public List<Role> findAll() throws SQLException {
        List<Role> roles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_QUERY)
        ) {
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new SQLException("ERROR: SELECT ALL ROLES: ", e);
        }
        return roles;
    }

    @Override
    public Optional<Role> findById(Integer id) throws SQLException {
        Role role = null;

        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ROLE_BY_ID_QUERY + id)) {

            if (resultSet.next()) {
                role = getRole(resultSet);
                if (resultSet.next()) {
                    throw new SQLIntegrityConstraintViolationException("ERROR: SELECT ROLE BY ID: Count roles more one");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: SELECT ROLE BY ID: ", ex);
        }
        return Optional.ofNullable(role);
    }


    private Role getRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt(ID_ROLE_COLUMN));
        role.setRoleName(resultSet.getString(NAME_ROLE_COLUMN));
        return role;
    }
}
