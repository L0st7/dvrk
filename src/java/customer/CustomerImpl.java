/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import basic.BasicImpl;
import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import object.CustomerObject;

/**
 *
 * @author nguye
 */
public class CustomerImpl extends BasicImpl implements Customer{

    public CustomerImpl(ConnectionPool cp) throws Throwable {
        super(cp, "Customer");
    }

    @Override
    public boolean addCustomer(CustomerObject item) {
        String sql = "INSERT INTO tblcustomer(";
        sql += "customer_fullname, customer_address, ";
        sql += "customer_email,customer_mobile,customer_account, ";
        sql += "customer_password) ";
        sql += "VALUE(?,?,?,?,?,md5(?))";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCustomer_fullname());
            pre.setString(2, item.getCustomer_address());
            pre.setString(3, item.getCustomer_email());
            pre.setString(4, item.getCustomer_mobile());
            pre.setString(5, item.getCustomer_account());
            pre.setString(6, item.getCustomer_password());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editCustomer(CustomerObject item) {
        String sql = "UPDATE tblcustomer SET ";
        sql += "customer_fullname=?, customer_address=?, ";
        sql += "customer_email=?, customer_mobile=?, customer_account=?, ";
        sql += "customer_password=md5(?) ";
        sql += "WHERE customer_id=?";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCustomer_fullname());
            pre.setString(2, item.getCustomer_address());
            pre.setString(3, item.getCustomer_email());
            pre.setString(4, item.getCustomer_mobile());
            pre.setString(5, item.getCustomer_account());
            pre.setString(6, item.getCustomer_password());
            
            pre.setInt(7, item.getCustomer_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delCustomer(CustomerObject item) {
        String sql = "DELETE FROM tblcustomer WHERE customer_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getCustomer_id());
            
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
    
    @Override
    public ResultSet getCustomer(String email, String pass){
        String sql = "SELECT * FROM tblcustomer WHERE (";
        sql += "(customer_email=?) AND (customer_password=md5(?))";
        sql += ")";
        return this.get(sql, email, pass);
    }

    @Override
    public ResultSet getCustomer(String customerName) {
        String sql = "SELECT * FROM tblcustomer WHERE customer_account=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, customerName);
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getCustomer(int id) {
        String sql = "SELECT * FROM tblcustomer WHERE customer_id=?";
        return this.get(sql, id);
    }

    @Override
    public int getCoutCustomer() {
        int count = 0;
        String sql = "SELECT count(customer_id) FROM tblcustomer";
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
    
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        Customer c = new CustomerImpl(cp);
        CustomerObject co = new CustomerObject();
        
        System.out.println(c.getCoutCustomer());
        
        co.setCustomer_fullname("Nguyễn Văn Hiệp");
        co.setCustomer_address("Hà Nội");
        co.setCustomer_email("nguyenhiep96vn@gmail.com");
        co.setCustomer_mobile("0964896938");
        co.setCustomer_account("Lost7");
        co.setCustomer_password("123456");

        //Thực hiện thêm mới
        boolean result = c.addCustomer(co);
        //Kiểm tra
        if (!result) {
            System.out.println("Không thành công");
        } else {
            System.out.println("Thêm thành công");
        }
        
        ResultSet rs = c.getCustomer("Lost7");
        //Duyệt và hiển thị kết quả
            if(rs!=null){
                while(rs.next()){
                    
                    System.out.println(co.toString());
                }
            }
    }
    
}
