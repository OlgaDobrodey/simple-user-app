package by.dobrodey.user_app;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.dao.RoleDao;
import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.dao.impl.hibernate.BookDaoHibernateImpl;
import by.dobrodey.user_app.dao.impl.hibernate.RoleDaoHibernateImpl;
import by.dobrodey.user_app.dao.impl.hibernate.UserDaoHibernateImpl;
import by.dobrodey.user_app.data.BaseConnection;
import by.dobrodey.user_app.model.User;
import by.dobrodey.user_app.service.FlywayService;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws SQLException {
        FlywayService flywayService = new FlywayService();
////        flywayService.clean();
        flywayService.migrate();
        SessionFactory sessionFactory = BaseConnection.getInstanceHibernate();

        System.out.println("=================ABOUT ROLE==================");
        RoleDao roleDao = new RoleDaoHibernateImpl(BaseConnection.getInstanceHibernate());
        role(roleDao);

        System.out.println("==================ABOUT BOOK==============================");
        BookDao bookDao = new BookDaoHibernateImpl(BaseConnection.getInstanceHibernate());
        books(bookDao);
        System.out.println("==================ABOUT USER==============================");
        UserDao userRepository = new UserDaoHibernateImpl(BaseConnection.getInstanceHibernate());

        findUsers(userRepository);
        saveUsers(userRepository);
        deleteUsers(userRepository);
//

    }

    private static void books(BookDao bookDao) throws SQLException{
        System.out.println("FInd All roles: " + bookDao.findAll());
        System.out.println("Find book by id1: " + bookDao.findById(1));
        System.out.println("Find book by id20: " + bookDao.findById(20));
        System.out.println(bookDao.findAllBookByUserId(10));
        System.out.println(bookDao.findAllBookByUserId(100));
    }

    private static void role(RoleDao roleDao) throws SQLException {
        System.out.println("FInd All roles: " + roleDao.findAll());
        System.out.println("Find role by id1: " + roleDao.findById(1));
        System.out.println("Find role by id20: " + roleDao.findById(20));
    }

    private static void deleteUsers(UserDao userRepository) throws SQLException {
        userRepository.deleteById(2);
        System.out.println("After delete 2 user " + userRepository.findAll());
        userRepository.deleteById(100);
        System.out.println("After delete 100 user " + userRepository.findAll());
        userRepository.deleteAll();
        System.out.println("Delete all " + userRepository.findAll());
        System.out.println(userRepository.save(User.builder()
                .firstName("Olga")
                .lastName("Dobrodey")
                .email("11olga@mail.ru")
                .dateOfBirth(LocalDate.of(2004, 03, 15))
                .build()));
    }

    private static void saveUsers(UserDao userRepository) throws SQLException {
        System.out.println(userRepository.save(User.builder()
                .firstName("Olga")
                .lastName("Dobrodey")
                .email("166olga@mail.ru")
                .dateOfBirth(LocalDate.of(2004, 03, 15))
                .build()));
        System.out.println("After add new user" + userRepository.findAll());
    }

    private static void findUsers(UserDao userRepository)  throws SQLException {
        System.out.println("Find All " + userRepository.findAll());
        System.out.println("Find by id = 1 " + userRepository.findById(1));
        System.out.println("Find by id = 100 " + userRepository.findById(100));
    }
}
