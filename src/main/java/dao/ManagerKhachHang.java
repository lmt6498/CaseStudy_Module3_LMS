package dao;

import modul.Book;
import modul.IssueBook;
import modul.LibrarianBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ManagerKhachHang {
    static Connection connection= DB.getCon();

    public static List<Book> view(){

        List<Book> list=new ArrayList<Book>();
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book bean = new Book();
            bean.setCallno(rs.getString("callno"));
                 bean.setName(rs.getString("name"));
               bean.setAuthor(rs.getString("author"));
                bean.setImage(rs.getString("image"));
               bean.setQuantity(rs.getInt("quantity"));
                list.add(bean);
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return list;
    }
    public static List<Book> showBookKH() throws SQLException, ClassNotFoundException {
        String select = "select callno,name,author,image,quantity from e_book";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(select);
        ArrayList<Book> listBookKH = new ArrayList<>();
        while (rs.next()) {
            String callno = (rs.getString("callno"));
            String name = rs.getString("name");
            String author = rs.getString("author");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            listBookKH.add(new Book(callno, name, author, image,quantity));

        }
        return listBookKH;
    }

    public static ArrayList<Book> findByNameKH(String findName) throws SQLException {
        Connection con = DB.getCon();
        ArrayList<Book> findListKH = new ArrayList<>();
        String findByName = "select * from e_book where name like '%" + findName + "%'";
        PreparedStatement preparedStatement = con.prepareStatement(findByName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String callno = (rs.getString("callno"));
            String name = rs.getString("name");
            String author = rs.getString("author");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            findListKH.add(new Book(callno, name, author, image, quantity));
        }
        return findListKH;
    }


}
