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
import java.util.*;
import cp.*;
import object.*;
public class UserControl {
    private UserModel um;
    public UserControl(ConnectionPool cp){
        this.um = new UserModel(cp);
    }
    
    //Phương thức dọn dẹp đối tượng
    protected void finalize()throws Throwable{
        this.um = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.um.getCP();
    }
    
    public void releaseConnection(){
        this.um.releaseConnection();
    }
    
    //Chuyển các điều khiển cho các phương thức thêm, sửa, xóa
    public boolean addUser(UserObject item){
        return this.um.addUser(item);
    }
    public boolean editUser(UserObject item){
        return this.um.editUser(item);
    }
    public boolean delUser(UserObject item){
        return this.um.delUser(item);
    }
    
    //Lấy ra bản ghi đối tượng
    public UserObject getUserObject(int id){
        return this.um.getUserObject(id);
    }
    
    //Phương thức đăng nhập
    public UserObject getUserObject(String username, String userpass){
        return this.um.getUserObject(username, userpass);
    }
    
    //Lấy danh sách đối tượng
    public ArrayList getUserObject(UserObject similar, short page, byte total){
        return this.um.getUserObject(similar, page, total);
    }
    //Đếm số lượng bản ghi trong bảng
    public int getUserCount(){
        return this.um.getUserCount();
    }
    
    //Trình bày view
    public String viewUser(UserObject similar, short page, byte total){
        //Danh sách đối tượng
        ArrayList items = this.um.getUserObject(similar, page, total);
        return UserLibrary.viewUser(items);
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionPool cp = new ConnectionPoolImpl();
        UserControl uc = new UserControl(cp);
        System.out.println(uc.getUserCount());
        String view = uc.viewUser(null, (short)1, (byte) 4);
        System.out.println(view);
    }
}
