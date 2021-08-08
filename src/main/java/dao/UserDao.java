package dao;

import modul.LibrarianBean;
import modul.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static int save(User bean){
        int status=0;
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("insert into e_user(name,email,password,phone) values(?,?,?,?)");
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getEmail());
            ps.setString(3,bean.getPassword());
            ps.setLong(4,bean.getPhone());
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static int update(User bean){
        int status=0;
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("update e_user set name=?,email=?,password=?,phone=? where iduser=?");
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getEmail());
            ps.setString(3,bean.getPassword());
            ps.setLong(4,bean.getPhone());
            ps.setInt(5,bean.getId());
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static List<User> view(){
        List<User> list=new ArrayList<User>();
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_user");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                User bean=new User();
                bean.setId(rs.getInt("iduser"));
                bean.setName(rs.getString("name"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setPhone(rs.getLong("phone"));
                list.add(bean);
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return list;
    }
    public static User viewById(int id){
        User bean=new User();
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_user where iduser=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setPhone(rs.getLong(5));
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return bean;
    }
    public static int delete(int id){
        int status=0;
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("delete from e_user where iduser=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }

    public static boolean authenticate(String email,String password){
        boolean status=false;
        try{
            Connection con=DB.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_user where email=? and password=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
}
