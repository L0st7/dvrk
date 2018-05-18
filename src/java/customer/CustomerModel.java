/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import cp.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import object.CustomerObject;
import object.UserObject;
import user.User;
import user.UserImpl;

/**
 *
 * @author nguye
 */
public class CustomerModel {
    private Customer c;
    
    public CustomerModel(ConnectionPool cp){
        try {
            this.c = new CustomerImpl(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    //Phương thức dọn dẹp đối tượng
    protected void finalize()throws Throwable{
        this.c = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.c.getCP();
    }
    
    public void releaseConnection(){
        this.c.releaseConnection();
    }
    
    //Chuyển các điều khiển cho phương thức thêm, sửa, xóa
    public boolean addCustomer(CustomerObject item){
        return this.c.addCustomer(item);
    }
    public boolean editCustomer(CustomerObject item){
        return this.c.editCustomer(item);
    }
    public boolean delCustomer(CustomerObject item){
        return this.c.delCustomer(item);
    }
    
    public CustomerObject getCustomerObject(int id){
        CustomerObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.c.getCustomer(id);
        if(rs!=null){
            try {
                if(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new CustomerObject();
                    item.setCustomer_id(rs.getInt("customer_id"));
                    item.setCustomer_fullname(rs.getString("customer_fullname"));
                    item.setCustomer_address(rs.getString("customer_address"));
                    item.setCustomer_email(rs.getString("customer_email"));
                    item.setCustomer_mobile(rs.getString("customer_mobile"));
                    item.setCustomer_account(rs.getString("customer_account"));
                    item.setCustomer_password(rs.getString("customer_password"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    public CustomerObject getCustomerObject(String customerName){
        CustomerObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.c.getCustomer(customerName);
        if(rs!=null){
            try {
                if(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new CustomerObject();
                    item.setCustomer_id(rs.getInt("customer_id"));
                    item.setCustomer_fullname(rs.getString("customer_fullname"));
                    item.setCustomer_address(rs.getString("customer_address"));
                    item.setCustomer_email(rs.getString("customer_email"));
                    item.setCustomer_mobile(rs.getString("customer_mobile"));
                    item.setCustomer_account(rs.getString("customer_account"));
                    item.setCustomer_password(rs.getString("customer_password"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    public CustomerObject getCustomerObject(String email,String pass){
        CustomerObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.c.getCustomer(email,pass);
        if(rs!=null){
            try {
                if(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new CustomerObject();
                    item.setCustomer_id(rs.getInt("customer_id"));
                    item.setCustomer_fullname(rs.getString("customer_fullname"));
                    item.setCustomer_address(rs.getString("customer_address"));
                    item.setCustomer_email(rs.getString("customer_email"));
                    item.setCustomer_mobile(rs.getString("customer_mobile"));
                    item.setCustomer_account(rs.getString("customer_account"));
                    item.setCustomer_password(rs.getString("customer_password"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    public int getCountCustomer(){
        return this.c.getCoutCustomer();
    }
}
