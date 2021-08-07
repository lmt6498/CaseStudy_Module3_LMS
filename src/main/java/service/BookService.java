package service;

import dao.BookDao;
import modul.Book;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookService {
    public ArrayList<Book> listBook = new ArrayList<>();

    public BookService() {
        try {
            getlistTL();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getlistTL() throws SQLException, ClassNotFoundException {
        listBook = (ArrayList<Book>) BookDao.view();
    }

    public int saveBook(Book book) throws SQLException, ClassNotFoundException {
        BookDao.save(book);
        listBook = (ArrayList<Book>) BookDao.view();
        return 0;
    }

    public void edit(Book book) throws SQLException, ClassNotFoundException {
        BookDao.editBook(book);
        listBook = (ArrayList<Book>) BookDao.view();
    }

    public void delete(int index) throws SQLException, ClassNotFoundException {
        BookDao.delete(listBook.get(index).getCallno());
        listBook = (ArrayList<Book>) BookDao.view();
    }

    public ArrayList<Book> findByName(String name) throws SQLException {
        return BookDao.findByName(name);

    }


}
