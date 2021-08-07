package service;

import dao.BookDao;
import modul.Book;
import modul.IssueBook;

import java.sql.SQLException;
import java.util.ArrayList;

public class IssueBookService {
    public ArrayList<IssueBook> listIssueBook= new ArrayList<>();

    public IssueBookService() {
        try {
            getlistTL();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getlistTL() throws SQLException, ClassNotFoundException {
        listIssueBook = (ArrayList<IssueBook>) BookDao.viewIssuedBooks();
    }
    public int saveBook(IssueBook issueBook) throws SQLException, ClassNotFoundException {
        BookDao.issueBook(issueBook);
        listIssueBook= (ArrayList<IssueBook>) BookDao.viewIssuedBooks();
        return 0;
    }

    public ArrayList<IssueBook> findByNameIb(String studentname) throws SQLException {
        return BookDao.findByNameIb(studentname);

    }
}
