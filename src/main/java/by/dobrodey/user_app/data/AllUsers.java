package by.dobrodey.user_app.data;

import by.dobrodey.user_app.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The class stores users
 */
public class AllUsers {

    private static List<User> AllUsers;

    static {
        getAllUsers().add(new User(1, "Ivan", "Ivanov"));
        getAllUsers().add(new User(2, "Petr", "Petrov"));
        getAllUsers().add(new User(3, "Sergey", "Sigorov"));
    }

    private AllUsers() {
    }

    /**
     * Giving list of all users
     *
     * @return list of users
     */
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
