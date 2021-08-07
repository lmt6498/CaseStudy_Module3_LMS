package controller;

import modul.IssueBook;
import service.IssueBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "IssueBookServlet", value = "/issuebook")
public class IssueBookServlet extends HttpServlet {
    IssueBookService issueBookService = new IssueBookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "ViewIssuedBook":
                request.setAttribute("listIssueBook", issueBookService.listIssueBook);
                RequestDispatcher requestDis = request.getRequestDispatcher("viewIssuedBook.jsp");
                requestDis.forward(request, response);
                break;
            case "IssueBookForm":
                request.getRequestDispatcher("issuebookform.jsp").include(request, response);
                break;
            default:
                request.getRequestDispatcher("navlibrarian.jsp").include(request, response);
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
            case "IssueBook":
                PrintWriter out=response.getWriter();
                String callno=request.getParameter("callno");
                String studentid=request.getParameter("studentid");
                String studentname=request.getParameter("studentname");
                String sstudentmobile=request.getParameter("studentmobile");
                long studentmobile=Long.parseLong(sstudentmobile);

                IssueBook issueBook=new modul.IssueBook(callno,studentid,studentname,studentmobile);
                int i= 0;
                try {
                    i = issueBookService.saveBook(issueBook);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                request.setAttribute("listIssueBook", issueBookService.listIssueBook);
                RequestDispatcher requestDis = request.getRequestDispatcher("viewIssuedBook.jsp");
                requestDis.forward(request, response);
                if(i>0){
                    out.println("<h3>Book issued successfully</h3>");
                }else{
                    out.println("<h3>Sorry, unable to issue book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
                }
                request.getRequestDispatcher("navlibrarian.jsp").include(request, response);
                break;
            case "findIBook":
                findIBook(request,response);
                break;
        }

    }
    protected void findIBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        try {
            request.setAttribute("listIssueBook", issueBookService.findByNameIb(findName));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewIssuedBook.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
