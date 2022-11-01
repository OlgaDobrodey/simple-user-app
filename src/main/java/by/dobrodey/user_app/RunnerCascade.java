package by.dobrodey.user_app;

import by.dobrodey.user_app.dao.AddressDao;
import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.dao.impl.hibernate.AddressDaoHibernateImpl;
import by.dobrodey.user_app.dao.impl.hibernate.UserDaoHibernateImpl;
import by.dobrodey.user_app.data.BaseConnection;

import java.sql.SQLException;

public class RunnerCascade {
    public static void main(String[] args) throws SQLException {
        System.out.println("==================ABOUT USER==============================");
        UserDao userRepository = new UserDaoHibernateImpl(BaseConnection.getInstanceHibernate());
        AddressDao addressDao = new AddressDaoHibernateImpl(BaseConnection.getInstanceHibernate());
        userRepository.deleteById(4);
        System.out.println("After deleted User by id = "+ 4+": "+ userRepository.findAll());
        System.out.println("All addresses :"+ addressDao.findAll());
    }
}
