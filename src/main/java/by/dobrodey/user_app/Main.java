package by.dobrodey.user_app;

import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.dao.impl.UserDaoImpl;
import by.dobrodey.user_app.data.BaseConnection;
import by.dobrodey.user_app.model.User;
import by.dobrodey.user_app.service.FlywayService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws SQLException {
//        UserDao userRepository = new UserDaoImpl();
        FlywayService flywayService = new FlywayService();
//        flywayService.clean();
        flywayService.migrate();
        final UserDao userRepository = new UserDaoImpl(BaseConnection.getInstance());
        System.out.println("Find All " + userRepository.findAll());
        System.out.println("Find by id = 1 " + userRepository.findById(1));
        System.out.println("Find by id = 100 " + userRepository.findById(100));

        System.out.println(userRepository.save(User.builder()
                .firstName("Olga")
                .lastName("Dobrodey")
                .email("1olga@mail.ru")
                .dateOfBirth(LocalDate.of(2004, 03, 15))
                .build()));
        System.out.println("After add new user" + userRepository.findAll());
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
}
