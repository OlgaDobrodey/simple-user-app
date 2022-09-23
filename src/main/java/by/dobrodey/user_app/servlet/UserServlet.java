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
    final private String USER_ID = "userId";
    final private String USER_NO_FOUND_MESSAGE = "User not found";
    final private String JSP_PAGE = "/index.jsp";
    final private String DELETE_ALL_ACTION = "/deleteAll";
    final private String LIST_OF_USERS_ACTION = "/users";
    final private String ADD_USER_ACTION = "/add";
    final private String DELETE_USER_ACTION = "/delete";
    final private String GET_USER_ACTION = "/get";

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
    }

    /**
     * Performs actions on the user (delete all and show a list of all users)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case DELETE_ALL_ACTION:
                deleteAll(request);
                break;
            case LIST_OF_USERS_ACTION:
                findAll(request);
                break;
        }

        getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
    }

    private void findAll(HttpServletRequest req) {
        List<User> allUsers = userDao.findAll();
        req.setAttribute("listUsers", allUsers);
    }

    private void deleteAll(HttpServletRequest req) {
        userDao.deleteAll();
        findAll(req);
    }


    /**
     * Performs actions on the user
     * (add user, get and delete user by id)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case ADD_USER_ACTION:
                create(request);
                break;
            case GET_USER_ACTION:
                getUser(request);
                break;
            case DELETE_USER_ACTION:
                deleteUser(request);
                break;
        }
        getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
    }

    private void getUser(HttpServletRequest req) {
        Optional<User> user = userDao.findById(Integer.parseInt(req.getParameter(USER_ID)));

        if (user.isPresent()) req.setAttribute("user", user.get());
        else req.setAttribute("user", USER_NO_FOUND_MESSAGE);
    }

    private void create(HttpServletRequest req) {
        User user = new User(req.getParameter(FIRST_NAME), req.getParameter(LAST_NAME));
        userDao.save(user);
        findAll(req);
    }

    private void deleteUser(HttpServletRequest req) {
        userDao.deleteById(Integer.parseInt(req.getParameter(USER_ID)));
        findAll(req);
    }
}
