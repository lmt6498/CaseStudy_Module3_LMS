package dao;

import modul.Book;
import modul.IssueBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDao {

    public static int save(Book bean) {
        int status = 0;
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("insert into e_book values(?,?,?,?,?,?,?)");
            ps.setString(1, bean.getCallno());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getAuthor());
            ps.setString(4, bean.getImage());
            ps.setString(5, bean.getPublisher());
            ps.setInt(6, bean.getQuantity());
            ps.setInt(7, 0);
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static List<Book> view() throws SQLException {
        ArrayList<Book> listBook = new ArrayList<Book>();
        Connection con = DB.getCon();
        PreparedStatement ps = con.prepareStatement("select * from e_book");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String callno = (rs.getString("callno"));
            String name = rs.getString("name");
            String author = rs.getString("author");
            String image = rs.getString("image");
            String publisher = rs.getString("publisher");
            int quantity = rs.getInt("quantity");
            int issued = rs.getInt("issued");
            listBook.add(new Book(callno, name, author, image, publisher, quantity, issued));
        }
        return listBook;

    }

    public static int delete(String callno) {
        int status = 0;
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("delete from e_book where callno=?");
            ps.setString(1, callno);
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    public static int getIssued(String callno) {
        int issued = 0;
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1, callno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                issued = rs.getInt("issued");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return issued;
    }

    public static boolean checkIssue(String callno) {
        boolean status = false;
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("select * from e_book where callno=? and quantity>issued");
            ps.setString(1, callno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    public static int issueBook(IssueBook bean) {
        String callno = bean.getCallno();
        boolean checkstatus = checkIssue(callno);
        System.out.println("Check status: " + checkstatus);
        if (checkstatus) {
            int status = 0;
            try {
                Connection con = DB.getCon();
                PreparedStatement ps = con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
                ps.setString(1, bean.getCallno());
                ps.setString(2, bean.getStudentid());
                ps.setString(3, bean.getStudentname());
                ps.setLong(4, bean.getStudentmobile());
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                ps.setDate(5, currentDate);
                ps.setString(6, "no");

                status = ps.executeUpdate();
                if (status > 0) {
                    PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
                    ps2.setInt(1, getIssued(callno) + 1);
                    ps2.setString(2, callno);
                    status = ps2.executeUpdate();
                }
                con.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            return status;
        } else {
            return 0;
        }
    }

    public static int returnBook(String callno, int studentid) {
        int status = 0;
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
            ps.setString(1, callno);
            ps.setInt(2, studentid);

            status = ps.executeUpdate();
            if (status > 0) {
                PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
                ps2.setInt(1, getIssued(callno) - 1);
                ps2.setString(2, callno);
                status = ps2.executeUpdate();
            }else {
                System.out.println("sách đã đủ");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }

    public static ArrayList<IssueBook> viewIssuedBooks() {
        ArrayList<IssueBook> listIssueBook = new ArrayList<IssueBook>();
        try {
            Connection con = DB.getCon();
            PreparedStatement ps = con.prepareStatement("select * from e_issuebook order by issueddate desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IssueBook bean = new IssueBook();
                bean.setCallno(rs.getString("callno"));
                bean.setStudentid(rs.getString("studentid"));
                bean.setStudentname(rs.getString("studentname"));
                bean.setStudentmobile(rs.getLong("studentmobile"));
                bean.setIssueddate(rs.getDate("issueddate"));
                bean.setReturnstatus(rs.getString("returnstatus"));
                listIssueBook.add(bean);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return listIssueBook;
    }


    public static ArrayList<Book> findByName(String findName) throws SQLException {
        Connection con = DB.getCon();
        ArrayList<Book> findList = new ArrayList<>();
        String findByName = "select * from e_book where name like '%" + findName + "%'";
        PreparedStatement preparedStatement = con.prepareStatement(findByName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String callno = (rs.getString("callno"));
            String name = rs.getString("name");
            String author = rs.getString("author");
            String image = rs.getString("image");
            String publisher = rs.getString("publisher");
            int quantity = rs.getInt("quantity");
            int issued = rs.getInt("issued");
            findList.add(new Book(callno, name, author, image, publisher, quantity, issued));
        }
        return findList;
    }


    public static void editBook(Book book) throws SQLException {
        Connection con = DB.getCon();
        String sqledit = "update e_book set name=?,author=?,image=?," +
                "publisher=?,quantity=? where callno=?";
        PreparedStatement ps = con.prepareStatement(sqledit);
        ps.setString(1, book.getName());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getImage());
        ps.setString(4, book.getPublisher());
        ps.setInt(5, book.getQuantity());
        ps.setString(6, book.getCallno());
        ps.execute();
    }

    public static ArrayList<IssueBook> findByNameIb(String findName) throws SQLException {
        Connection con = DB.getCon();
        ArrayList<IssueBook> findListIb = new ArrayList<>();
        String findByName = "select * from e_issuebook where studentname like '%" + findName + "%'";
        PreparedStatement preparedStatement = con.prepareStatement(findByName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String callno = (rs.getString("callno"));
            String studentid = rs.getString("studentid");
            String studentname = rs.getString("studentname");
            long studentmobile = rs.getLong("studentmobile");
            Date issueddate = rs.getDate("issueddate");
            String returnstatus = rs.getString("returnstatus");
            findListIb.add(new IssueBook(callno,studentid,studentname,studentmobile, (java.sql.Date) issueddate,returnstatus));
        }
        return findListIb;
    }

}


//    public static int update(Librarian bean){
//        int status=0;
//        try{
//            Connection con=DB.getCon();
//            PreparedStatement ps=con.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
//            ps.setString(1,bean.getName());
//            ps.setString(2,bean.getEmail());
//            ps.setString(3,bean.getPassword());
//            ps.setLong(4,bean.getMobile());
//            ps.setInt(5,bean.getId());
//            status=ps.executeUpdate();
//            con.close();
//
//        }catch(Exception e){System.out.println(e);}
//
//        return status;
//    }

//	public static Librarian viewById(int id){
//		Librarian bean=new Librarian();
//		try{
//			Connection con=DB.getCon();
//			PreparedStatement ps=con.prepareStatement("select * from e_librarian where id=?");
//			ps.setInt(1,id);
//			ResultSet rs=ps.executeQuery();
//			if(rs.next()){
//				bean.setId(rs.getInt(1));
//				bean.setName(rs.getString(2));
//				bean.setPassword(rs.getString(3));
//				bean.setEmail(rs.getString(4));
//				bean.setMobile(rs.getLong(5));
//			}
//			con.close();
//
//		}catch(Exception e){System.out.println(e);}
//
//		return bean;
//	}


