package by.dobrodey.user_app.servlet;

import by.dobrodey.user_app.dao.UserDao;
import by.dobrodey.user_app.dao.UserDaoImpl;
import by.dobrodey.user_app.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "userServlet", urlPatterns = "/")
public class UserServlet extends HttpServlet {

    final private String FIRST_NAME = "firstName";
    final private String LAST_NAME = "lastName";

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/deleteAll":
                deleteAll(request);
                break;
            case "/users":
                findAll(request);
                break;
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void findAll(HttpServletRequest req) {
        List<User> allUsers = userDao.findAll();
        req.setAttribute("listUsers", allUsers);
    }

    private void deleteAll(HttpServletRequest req) {
        userDao.deleteAll();
        findAll(req);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/add":
                create(request);
                break;
            case "/get":
                getUser(request);
                break;
            case "/delete":
                deleteUser(request);
                break;
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void getUser(HttpServletRequest req) {
        Optional<User> user = userDao.findById(Integer.parseInt(req.getParameter("userId")));

        if (user.isPresent()) req.setAttribute("user", user.get());
        else req.setAttribute("user", "User not found");
    }

    private void create(HttpServletRequest req) {
        User user = new User(req.getParameter(FIRST_NAME), req.getParameter(LAST_NAME));
        userDao.save(user);
        findAll(req);
    }

    private void deleteUser(HttpServletRequest req) {
        userDao.deleteById(Integer.parseInt(req.getParameter("userId")));
        findAll(req);
    }
}
