package by.dobrodey.user_app.data;

import by.dobrodey.user_app.model.User;

import java.util.ArrayList;
import java.util.List;

public class AllUsers {

    private static List<User> AllUsers;

    static {
        getAllUsers().add(new User(1, "Ivan", "Ivanov"));
        getAllUsers().add(new User(2, "Petr", "Petrov"));
        getAllUsers().add(new User(3, "Sergey", "Sigorov"));
    }

    private AllUsers() {
    }

    public static List<User> getAllUsers() {
        if (AllUsers == null) {
            synchronized (AllUsers.class) {
                if (AllUsers == null) {
                    AllUsers = new ArrayList<>();
                }

            }
        }
        return AllUsers;
    }
}
