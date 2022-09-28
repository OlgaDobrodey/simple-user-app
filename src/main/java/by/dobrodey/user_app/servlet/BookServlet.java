package by.dobrodey.user_app.servlet;

import by.dobrodey.user_app.dao.BookDao;
import by.dobrodey.user_app.dao.impl.BookDaoImpl;
import by.dobrodey.user_app.data.BaseConnection;
import by.dobrodey.user_app.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "bookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {

    final private String BOOK_ID = "userId";
    final private String BOOK_NO_FOUND_MESSAGE = "Book not found";
    final private String BOOK_JSP_PAGE = "/book.jsp";

    private BookDao bookDao;

    @Override
    public void init() throws ServletException {
        bookDao = new BookDaoImpl(BaseConnection.getInstance());
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listBooks", bookDao.findAll());

        getServletContext().getRequestDispatcher(BOOK_JSP_PAGE).forward(request, response);
    }

    /**
     * Performs actions on the book
     * (get book by id)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Book> book = bookDao.findById(Integer.parseInt(request.getParameter(BOOK_ID)));

        if (book.isPresent()) request.setAttribute("book", book.get());
        else request.setAttribute("book", BOOK_NO_FOUND_MESSAGE);
        getServletContext().getRequestDispatcher(BOOK_JSP_PAGE).forward(request, response);
    }
}