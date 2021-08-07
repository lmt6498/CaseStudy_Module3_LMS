package service;

import dao.BookDao;
import dao.ManagerKhachHang;
import modul.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangService {
    public ArrayList<Book> listBookKH = new ArrayList<>();

    public KhachHangService() {
        try {
            getlistKH();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getlistKH() throws SQLException, ClassNotFoundException {
        listBookKH = (ArrayList<Book>) ManagerKhachHang.showBookKH();
    }

    public ArrayList<Book> findByNameKH(String name) throws SQLException {
        return ManagerKhachHang.findByNameKH(name);

    }
}
