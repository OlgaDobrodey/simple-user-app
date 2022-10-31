package by.dobrodey.user_app.dao.impl;

import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.model.Role;
import by.dobrodey.user_app.model.User;
import by.dobrodey.user_app.proxy.UserProxy;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class consist methods for work model User
 */
@AllArgsConstructor
@Deprecated
public class UserDaoImpl implements UserDao {

    private static final String ID_USER_COLUMN = "id";
    private static final String FIRST_NAME_USER_COLUMN = "first_name";
    private static final String LAST_NAME_USER_COLUMN = "last_name";
    private static final String EMAIL_COLUMN = "email";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String ROLE_USER_COLUMN = "role_id";
    private static final String ROLE_NAME_TABLE_ROLE = "role_name";

    private static final String SELECT_ALL_QUERY =
            "SELECT users.id, users.first_name, users.last_name, users.email ,users.date_of_birth, users.role_id, role.role_name " +
                    "FROM users join role on role.id = users.role_id";
    private static final String SELECT_USER_BY_ID_QUERY = SELECT_ALL_QUERY + " WHERE  users.id = ";
    private static final String INSERT_USER_QUERY = "INSERT INTO users(first_name, last_name, email, date_of_birth, role_id) VALUES (?,?,?,?,?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String DELETE_USER_BOOK_QUERY = "DELETE FROM users_book WHERE users_id = ?";
    private static final String DELETE_ALL_QUERY = "DELETE FROM users";
    private static final String DELETE_ALL_USERS_FROM_USERS_BOOK = "DELETE FROM users_book";

    private final DataSource dataSource;

    @Override
    public User save(User user) throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            if (user.getRole() == null) {
                user.setRole(Role.getDefaultRole());
            }
            preparedStatement.setInt(5, user.getRole().getId());

            int effectiveRows = preparedStatement.executeUpdate();
            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(ID_USER_COLUMN));
                    }
                } catch (SQLException ex) {
                    throw new SQLException("ERROR: INSERT INTO USER - " + user + ": ", ex);
                }
            }
        }
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) throws SQLException {
        User user = null;
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_USER_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                user = getUser(resultSet);
                if (resultSet.next()) {
                    throw new SQLIntegrityConstraintViolationException("Count users more one");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: SELECT USER BY ID: ", ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                User user = getUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: SELECT ALL USERS: ", ex);
        }
        return users;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement psUserTable = conn.prepareStatement(DELETE_USER_QUERY);
                     PreparedStatement psUsersBookTable = conn.prepareStatement(DELETE_USER_BOOK_QUERY)) {
                    removeUsers(id, psUsersBookTable);
                    removeUsers(id, psUserTable);

                    psUserTable.executeUpdate();
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new SQLException("TRANSACTION ROLLBACK: " + ex);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: REMOVE_USER - " + id + ": ", ex);
        }
    }

    private static void removeUsers(Integer userId, PreparedStatement psBookUsersTable) throws SQLException {
        psBookUsersTable.setInt(1, userId);
        psBookUsersTable.executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (Statement statement = conn.createStatement();
                     Statement stUserBookTable = conn.createStatement()) {
                    statement.executeUpdate(DELETE_ALL_USERS_FROM_USERS_BOOK);
                    stUserBookTable.executeUpdate(DELETE_ALL_QUERY);
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new SQLException("TRANSACTION ROLLBACK: " + ex);
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR: REMOVE_ALL_USER: ", ex);
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new UserProxy().contextInitialized();
        user.setId(resultSet.getInt(ID_USER_COLUMN));
        user.setFirstName(resultSet.getString(FIRST_NAME_USER_COLUMN));
        user.setLastName(resultSet.getString(LAST_NAME_USER_COLUMN));
        user.setEmail(resultSet.getString(EMAIL_COLUMN));
        user.setDateOfBirth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate());
        Role role = Role.builder()
                .id(resultSet.getInt(ROLE_USER_COLUMN))
                .roleName(resultSet.getString(ROLE_NAME_TABLE_ROLE))
                .build();
        user.setRole(role);

        return user;
    }
}
