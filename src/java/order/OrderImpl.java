/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import basic.*;
import cp.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author nguye
 */
public class OrderImpl extends BasicImpl implements Order{
    
    public OrderImpl(ConnectionPool cp) throws Throwable{
        super(cp,"Order");
    }

    @Override
    public boolean addOrder(OrderObject item) {
        String sql = "INSERT INTO tblorder( ";
        sql += "order_title, order_address, ";
        sql += "order_date, order_note, ";
        sql += "order_customer_id, ";
        sql += "order_price, order_fullname_customer, ";
        sql += "order_phone,order_payments,  ";
        sql += "order_status,order_email,order_delivery_date )";
        sql += "VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getOrder_title());
            pre.setString(2, item.getOrder_address());
            pre.setString(3, item.getOrder_date());
            pre.setString(4, item.getOrder_note());
            pre.setInt(5, item.getOrder_customer_id());
            pre.setInt(6, item.getOrder_price());
            pre.setString(7, item.getOrder_fullname_customer());
            pre.setString(8, item.getOrder_phone());
            pre.setString(9, item.getOrder_payments());
            pre.setBoolean(10, item.isOrder_status());
            pre.setString(11, item.getOrder_email());
            pre.setString(12, item.getOrder_delivery_date());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editOrder(OrderObject item) {
        String sql = "UPDATE tblorder SET ";
        sql += "order_status=?,order_delivery_date=? ";
        sql += "WHERE order_id=?";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setBoolean(1, item.isOrder_status());
            pre.setString(2, item.getOrder_delivery_date());
            
            pre.setInt(3, item.getOrder_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delOrder(OrderObject item) {
        String sql = "DELETE FROM tblorder WHERE order_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getOrder_id());
            
            return this.del(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int getOrderCount() {
        int count = 0;
        String sql = "SELECT count(order_id) FROM tblorder";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public ResultSet getOrder(int id) {
        String sql = "SELECT * FROM tblorder WHERE order_id=?";
        return this.get(sql, id);
    }
    
    @Override
    public ResultSet getOrder(String order_date){
        String sql = "SELECT * FROM tblorder WHERE order_date=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, order_date);
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override 
    public ResultSet getOrderPrice(int price){
        String sql = "SELECT * FROM tblorder WHERE order_price=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, price);
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getOrders(OrderObject similar, short at, byte total) {
        String sql = "SELECT * FROM tblorder ";
        sql += "ORDER BY order_fullname_customer ASC ";
        sql += "LIMIT "+at+", "+total;
        return this.gets(sql);
    }
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        Order o = new OrderImpl(cp);
        OrderObject oo = new OrderObject();
        
        System.out.println(o.getOrderCount());
        
        oo.setOrder_id(40);
        oo.setOrder_title("Order");
        oo.setOrder_address("Ha noi");
        oo.setOrder_date("12/5/1996");
        oo.setOrder_note("test");
        oo.setOrder_price(200000);
        oo.setOrder_fullname_customer("Nguyen Van Hiep");
        oo.setOrder_phone("0964896938");
        oo.setOrder_payments("Chuyen khoan");
        oo.setOrder_status(true);
        oo.setOrder_email("nguyenhiep96vn@gmail.com");
        
        boolean Result = o.delOrder(oo);
        if(!Result){
            System.out.println("Không thành công");
        }else{
            System.out.println("Thành công");
        }
        
        ResultSet rs = o.getOrderPrice(13940000);
        
        String row = null;
        if(rs!=null){
            while(rs.next()){
                row = "ID: "+ rs.getInt("order_id");
                row += "\tTITLE: "+rs.getString("order_title");
                row += "\tADDRESS: "+rs.getString("order_address");
                row += "\tDATE: "+rs.getString("order_date");
                row += "\tNOTE: "+rs.getString("order_note");
                row += "\tPRICE: "+rs.getInt("order_price");
                row += "\tNAME: "+rs.getString("order_fullname_customer");
                row += "\tPHONE: "+rs.getString("order_phone");
                row += "\tPAYMENTS: "+rs.getString("order_payments");
                row += "\tSTATUS: "+rs.getBoolean("order_status");
                row += "\tEMAIL: " +rs.getString("order_email");
                
                System.out.println(row);
            }
        }
    }
}
