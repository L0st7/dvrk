/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author nguye
 */
import cp.*;
import object.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserModel {
    private User u;
    
    public UserModel(ConnectionPool cp){
        try {
            this.u = new UserImpl(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    //Phương thức dọn dẹp đối tượng
    protected void finalize()throws Throwable{
        this.u = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.u.getCP();
    }
    
    public void releaseConnection(){
        this.u.releaseConnection();
    }
    
    //Chuyển các điều khiển cho phương thức thêm, sửa, xóa
    public boolean addUser(UserObject item){
        return this.u.addUser(item);
    }
    public boolean editUser(UserObject item){
        return this.u.editUser(item);
    }
    public boolean delUser(UserObject item){
        return this.u.delUser(item);
    }
      
    //Ánh xạ giả lập hibernate
    public UserObject getUserObject(int id){
        UserObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.u.getUser(id);
        if(rs!=null){
            try {
                if(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new UserObject();
                    item.setUser_id(rs.getInt("user_id"));
                    item.setUser_name(rs.getString("user_name"));
                    item.setUser_pass(rs.getString("user_pass"));
                    item.setUser_fullname(rs.getString("user_fullname"));
                    item.setUser_email(rs.getString("user_email"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Phương thức đăng nhập
    public UserObject getUserObject(String username, String userpass){
        UserObject item = null;
        //Lấy bản ghi đối tượng theo tên đăng nhập và mật khẩu
        ResultSet rs = this.u.getUser(username, userpass);
        if(rs!=null){
            try {
                if(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new UserObject();
                    item.setUser_id(rs.getInt("user_id"));
                    item.setUser_name(rs.getString("user_name"));
                    item.setUser_pass(rs.getString("user_pass"));
                    item.setUser_fullname(rs.getString("user_fullname"));
                    item.setUser_email(rs.getString("user_email"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Lấy về nhiều đối tượng
    public ArrayList getUserObject(UserObject similar, short page, byte total){
        ArrayList items = new ArrayList();
        UserObject item = null;
        //Lấy bản ghi đối tượng
        int at = (short)((page-1)*total);
        ResultSet rs = this.u.getUsers(similar,at,total);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new UserObject();
                    item.setUser_id(rs.getInt("user_id"));
                    item.setUser_name(rs.getString("user_name"));
                    item.setUser_pass(rs.getString("user_pass"));
                    item.setUser_fullname(rs.getString("user_fullname"));
                    item.setUser_email(rs.getString("user_email"));
                    
                    //Đưa đối tượng vào danh sách
                    items.add(item);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }
    
    //Đếm số lượng bản ghi trong bảng
    public int getUserCount(){
        return this.u.getUserCount();
    }
    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionPool cp = new ConnectionPoolImpl();
        UserModel um = new UserModel(cp);
        UserObject uo = um.getUserObject("Lost7", "123456");
        if(uo!=null){
            System.out.println("Thành công");
        }else{
            System.out.println("Thất bại");
        }
    }
}
