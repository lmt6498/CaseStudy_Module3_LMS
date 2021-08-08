package controller;

import dao.UserDao;
import modul.LibrarianBean;
import dao.LibrarianDao;
import modul.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/account","/"})
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action ="";
        }
        switch (action){
            case "admin":
                req.getRequestDispatcher("loginadminform.jsp").forward(req,resp);
                break;
            case "librarian":
                req.getRequestDispatcher("loginlibrarionform.jsp").forward(req,resp);
                break;
            case "addlibrarian":
                req.getRequestDispatcher("addlibrarianform.jsp").forward(req,resp);
                break;
            case "user":
                req.getRequestDispatcher("loginuser.jsp").forward(req,resp);
            case "adduser":
                req.getRequestDispatcher("adduser.jsp").forward(req,resp);
                break;
            case "deletelib":
                String sid=req.getParameter("id");
                int id=Integer.parseInt(sid);
                LibrarianDao.delete(id);
                resp.sendRedirect("viewlibrarian.jsp");
                break;
            case "editlib":
                String sid1=req.getParameter("id");
                int id1=Integer.parseInt(sid1);
                LibrarianBean bean=LibrarianDao.viewById(id1);
                req.setAttribute("lib",bean);
                req.getRequestDispatcher("editlibrarianform.jsp").forward(req,resp);
                break;
            case "showlib":
                List<LibrarianBean> list=LibrarianDao.view();
                req.setAttribute("listLib", list);
                req.getRequestDispatcher("viewlibrarian.jsp").forward(req,resp);
                break;
            case "showuser":
                List<User> list2=UserDao.view();
                req.setAttribute("listUser", list2);
                req.getRequestDispatcher("viewuser.jsp").forward(req,resp);
                break;
            case "logout":
                req.getSession().invalidate();
                resp.sendRedirect("index.jsp");
                break;
            default:
                HttpSession session = req.getSession(false);
                if (session.getAttribute("admin") != null) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                }else if(session.getAttribute("email") != null){
                    RequestDispatcher dispatcher = req.getRequestDispatcher("loginlib.jsp");
                    dispatcher.forward(req, resp);
                }else {
                    String message = "Please login fisrt";
                    req.setAttribute("messagelog", message);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null){
            action ="";
        }
        switch (action){
            case "admin":
                String email=req.getParameter("email");
                String password=req.getParameter("password");
                if(email.equals("admin@gmail.com")&&password.equals("123456")){
                    HttpSession session=req.getSession();
                    session.setAttribute("admin","true");

                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }else{
                    String message = "Invalid email/password";
                    req.setAttribute("messagelogin", message);
                }
                req.getRequestDispatcher("loginadminform.jsp").forward(req,resp);
                break;
            case "librarian":
                String email3=req.getParameter("email");
                String password3=req.getParameter("password");
                if(LibrarianDao.authenticate(email3, password3)){
                    HttpSession session=req.getSession();
                    session.setAttribute("email",email3);
                    req.getRequestDispatcher("loginlib.jsp").forward(req,resp);
                }else{
                    String message = "Invalid email/password";
                    req.setAttribute("messageloginlib", message);
                }
                req.getRequestDispatcher("loginlibrarionform.jsp").forward(req,resp);
                break;
            case "addlibrarian":
                String name1=req.getParameter("name");
                String email1=req.getParameter("email");
                String password1=req.getParameter("password");
                String smobile1=req.getParameter("mobile");
                long mobile=Long.parseLong(smobile1);
                LibrarianBean bean=new LibrarianBean(name1, email1, password1, mobile);
                LibrarianDao.save(bean);
                String message = "Successfully added librarian";
                req.setAttribute("messageaddlib", message);
                req.getRequestDispatcher("addlibrarianform.jsp").forward(req,resp);
                break;
            case "adduser":
                String name4=req.getParameter("name");
                String email4=req.getParameter("email");
                String password4=req.getParameter("password");
                String sphone4=req.getParameter("phone");
                long phone=Long.parseLong(sphone4);
                User bean4=new User(name4, email4, password4, phone);
                UserDao.save(bean4);
                String message2 = "Register Successfully! Click User for back to Login User Form";
                req.setAttribute("messageadduserres", message2);
                req.getRequestDispatcher("adduser.jsp").forward(req,resp);
                break;
            case "user":
                String email5=req.getParameter("email");
                String password5=req.getParameter("password");
                if(UserDao.authenticate(email5, password5)){
                    HttpSession session=req.getSession();
                    session.setAttribute("user",email5);
                    req.getRequestDispatcher("homeKH.jsp").forward(req,resp);
                }else{
                    String message3 = "Invalid email/password";
                    req.setAttribute("messageloginuser", message3);
                }
                req.getRequestDispatcher("loginuser.jsp").forward(req,resp);
                break;
            case "editlib":
                String sid=req.getParameter("id");
                int id2=Integer.parseInt(sid);
                String name2=req.getParameter("name");
                String email2=req.getParameter("email");
                String password2=req.getParameter("password");
                String smobile2=req.getParameter("mobile");
                long mobile2=Long.parseLong(smobile2);
                LibrarianBean bean2=new LibrarianBean(id2,name2, email2, password2, mobile2);
                LibrarianDao.update(bean2);
                resp.sendRedirect("viewlibrarian.jsp");
                break;
        }
    }
}
