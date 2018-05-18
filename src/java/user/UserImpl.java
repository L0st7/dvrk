/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.*;
import object.*;
import cp.*;
import basic.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 *
 * @author nguye
 */
public class UserImpl extends BasicImpl implements User{
    
    public UserImpl(ConnectionPool cp) throws Throwable{
        super(cp, "User");
    }

    @Override
    public boolean addUser(UserObject item) {
        //Kiểm tra sự tồn tại
        if(this.isExisting(item)){
            return false;
        }
        String sql = "INSERT INTO tbluser(";
        sql += "user_name, user_pass, ";
        sql += "user_fullname, ";
        sql += "user_email) ";
        sql += "VALUE(?,md5(?),?,?)";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getUser_name());
            pre.setString(2, item.getUser_pass());
            pre.setString(3, item.getUser_fullname());
            pre.setString(4, item.getUser_email());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    //Kiểm tra tính duy nhất
    private boolean isExisting(UserObject item){
        boolean flag = false;
        String sql = "SELECT user_id FROM tbluser WHERE ";
        sql += "user_name='"+item.getUser_name()+"' ";
        
        ResultSet rs = this.gets(sql);
        if(rs!= null){
            try {
                if(rs.next()){
                    flag = true;
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean editUser(UserObject item) {
        String sql = "UPDATE tbluser SET ";
        sql += "user_name=?, user_pass=md5(?), ";
        sql += "user_fullname=?, ";
        sql += "user_email=? ";
        sql += "WHERE user_id=?";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getUser_name());
            pre.setString(2, item.getUser_pass());
            pre.setString(3, item.getUser_fullname());
            pre.setString(4, item.getUser_email());
            
            pre.setInt(5, item.getUser_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delUser(UserObject item) {
        String sql = "DELETE FROM tbluser WHERE user_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getUser_id());
            
            return this.del(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    //Lấy ra thông tin một người dùng
    @Override
    public ResultSet getUser(int id) {
        String sql = "SELECT * FROM tbluser WHERE user_id=?";
        return this.get(sql, id);
    }

    //Kiểm tra đăng nhập
    @Override
    public ResultSet getUser(String username, String userpass) {
        String sql = "SELECT * FROM tbluser WHERE (";
        sql += "(user_name=?) AND (user_pass=md5(?))";
        sql += ")";
        return this.get(sql, username, userpass);
    }

    //Lấy danh sách người dùng
    @Override
    public ResultSet getUsers(UserObject similar, int at, byte total) {
        String sql = "SELECT * FROM tbluser ";
        sql += "";
        sql += "";
        sql += "";
        sql += "";
        sql += "ORDER BY user_name ASC ";
        sql += "LIMIT "+at+", "+total;
        return this.gets(sql);
    }
    
    //Đếm số lượng bản ghi trong bảng
    @Override
    public int getUserCount(){
        String sql = "SELECT count(user_id) FROM tbluser";
        int count =0;
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static void main(String[] args) throws Throwable {
            //Tạo bộ quản lí kết nối
            ConnectionPool cp = new ConnectionPoolImpl();
            
            //Tạo đối tượng thực thi chức năng
            User u = new UserImpl(cp);
            
            int count = u.getUserCount();
            System.out.println(count);
            
            //Tạo đối tượng lưu trữ thông tin
            UserObject nUser = new UserObject();
            nUser.setUser_id(5);
            nUser.setUser_name("Long");
            nUser.setUser_pass("123456");
            nUser.setUser_fullname("Nguyễn Văn Hiệp");
            nUser.setUser_email("nguyenhiep96vn@gmail.com");
            
            //Thực hiện thêm mới
            boolean result = u.addUser(nUser);
            //Kiểm tra
            if(!result){
                System.out.println("Không thành công");
            }else{
                System.out.println("Thêm thành công");
            }
            
            //Lấy danh sách bản ghi người sử dụng
            ResultSet rs = u.getUsers(null, (short)0, (byte)15);
            
            //Duyệt và hiển thị kết quả
            String row;
            if(rs!=null){
                while(rs.next()){
                    row = "ID: "+rs.getInt("user_id");
                    row += "\tUSERNAME: "+rs.getString("user_name");
                    row += "\tUSERPASS: "+rs.getString("user_pass");
                    row += "\tFULLNAME: "+rs.getString("user_fullname");
                    row += "\tEMAIL: "+rs.getString("user_email");
                    
                    System.out.println(row);
                }
            }

    }
}
