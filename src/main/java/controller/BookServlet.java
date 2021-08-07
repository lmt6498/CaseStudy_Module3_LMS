package controller;

import dao.BookDao;
import modul.Book;
import service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "BookServlet", value = "/book")
public class BookServlet extends HttpServlet {
    BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "ViewBook":
                request.setAttribute("listBook", bookService.listBook);
                RequestDispatcher requestDis = request.getRequestDispatcher("viewBook.jsp");
                requestDis.forward(request, response);
                break;
            case "AddBookForm":
                request.getRequestDispatcher("addbookform.jsp").forward(request, response);
                break;
            case "DeleteBook":
                deleteBook(request,response);
                break;
            case "ReturnBookForm":
                request.getRequestDispatcher("returnbookform.jsp").include(request, response);
                break;
            case "Edit":
                showEdit(request,response);
                break;
            default:
                request.getRequestDispatcher("viewBook.jsp").include(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "homeKH":
                response.sendRedirect("homeKH.jsp");
                break;
            case "AddBook":
                PrintWriter out = response.getWriter();
                String callno = request.getParameter("callno");
                String name = request.getParameter("name");
                String author = request.getParameter("author");
                String image = request.getParameter("image");
                String publisher = request.getParameter("publisher");
                String squantity = request.getParameter("quantity");
                int quantity = Integer.parseInt(squantity);
                Book book = new Book(callno, name, author,image, publisher, quantity);
                int i = 0;
                try {
                    i = bookService.saveBook(book);
                    request.setAttribute("listBook", bookService.listBook);
                    RequestDispatcher requestDis = request.getRequestDispatcher("navlibrarian.jsp");
                    requestDis.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (i > 0) {
                    out.println("<h3>Book saved successfully</h3>");
                }
            case "ReturnBook":
                PrintWriter outp=response.getWriter();
                String callnoRB=request.getParameter("callno");
                int studentid= Integer.parseInt(request.getParameter("studentid"));
                int x=BookDao.returnBook(callnoRB,studentid);
                if(x>=1 ){
                    request.getRequestDispatcher("navlibrarian.jsp").include(request, response);
                }else {
                    outp.println("<h3>Sorry, unable to return book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
                }
                break;
            case "find":
                findBook(request,response);
                break;
            case "edit":
                edit(request,response);
                break;
        }

    }
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int index = Integer.parseInt(request.getParameter("index"));
        try {
            bookService.delete(index);
            response.sendRedirect("book");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void findBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        try {
            request.setAttribute("listBook", bookService.findByName(findName));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBook.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String callno = request.getParameter("callno");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String image = request.getParameter("image");
        String publisher = request.getParameter("publisher");
        int squantity = Integer.parseInt(request.getParameter("quantity"));

        Book bookEdit = new Book(callno,name,author,image,publisher,squantity);
        try {
            bookService.edit(bookEdit);
            request.setAttribute("listBook", bookService.listBook);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBook.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int indexEdit = Integer.parseInt(request.getParameter("index"));
        request.setAttribute("listBook", bookService.listBook.get(indexEdit));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editBook.jsp");
        requestDispatcher.forward(request, response);
    }
}
