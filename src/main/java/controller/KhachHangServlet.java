package controller;

import service.KhachHangService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "KhachHangServlet", value = "/khachhang")
public class KhachHangServlet extends HttpServlet {
    KhachHangService khachHangService = new KhachHangService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action){

            default:
                request.setAttribute("listBookKH", khachHangService.listBookKH);
                requestDispatcher = request.getRequestDispatcher("homeKH.jsp");
                requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action){
            case "find":
                findbookKH(request,response);
                break;
        }
    }


    protected void findbookKH(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        try {
            request.setAttribute("listBookKH", khachHangService.findByNameKH(findName));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("homeKH.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
