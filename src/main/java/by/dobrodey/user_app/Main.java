package by.dobrodey.user_app;

import by.dobrodey.user_app.model.User;
import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.dao.UserDaoImpl;

public class Main {

    public static void main(String[] args) {
        final UserDao userRepository = new UserDaoImpl();
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findById(1));
        System.out.println(userRepository.findById(8));
        System.out.println(userRepository.save(new User("Olga","Dobrodey")));
        userRepository.deleteById(1);
        System.out.println(userRepository.findAll());
        userRepository.deleteById(9);
        System.out.println(userRepository.findAll());
        userRepository.deleteAll();
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.save(new User("Olga","Dobrodey")));
    }
}
