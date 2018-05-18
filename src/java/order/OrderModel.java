/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import cp.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.OrderObject;

/**
 *
 * @author nguye
 */
public class OrderModel {
    private Order o;
    public OrderModel(ConnectionPool cp){
        try {
            this.o = new OrderImpl(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.o = null;
        super.finalize();
    }
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.o.getCP();
    }
    
    public void releaseConnection(){
        this.o.releaseConnection();
    }
    //Chuyển các điều khiển cho các phương thức thêm, sửa xóa
    public boolean addOrder(OrderObject item){
        return this.o.addOrder(item);
    }
    public boolean editOrder(OrderObject item){
        return this.o.editOrder(item);
    }
    public boolean delOrder(OrderObject item){
        return this.o.delOrder(item);
    }
    
    //Lấy số lượng bản ghi
    public int getOrderCount(){
        return this.o.getOrderCount();
    }
    
    //Lấy bản ghi theo id
    public OrderObject getOrderObject(int id){
        OrderObject item = null;
        ResultSet rs = this.o.getOrder(id);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new OrderObject();
                    item.setOrder_id(rs.getInt("order_id"));
                    item.setOrder_title(rs.getString("order_title"));
                    item.setOrder_address(rs.getString("order_address"));
                    item.setOrder_date(rs.getString("order_date"));
                    item.setOrder_note(rs.getString("order_note"));
                    item.setOrder_customer_id(rs.getInt("order_customer_id"));
                    item.setOrder_price(rs.getInt("order_price"));
                    item.setOrder_fullname_customer(rs.getString("order_fullname_customer"));
                    item.setOrder_phone(rs.getString("order_phone"));
                    item.setOrder_payments(rs.getString("order_payments"));
                    item.setOrder_status(rs.getBoolean("order_status"));
                    item.setOrder_email(rs.getString("order_email"));
                    item.setOrder_delivery_date(rs.getString("order_delivery_date"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    public OrderObject getOrderObject(String order_date){
        OrderObject item = null;
        ResultSet rs = this.o.getOrder(order_date);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new OrderObject();
                    item.setOrder_id(rs.getInt("order_id"));
                    item.setOrder_title(rs.getString("order_title"));
                    item.setOrder_address(rs.getString("order_address"));
                    item.setOrder_date(rs.getString("order_date"));
                    item.setOrder_note(rs.getString("order_note"));
                    item.setOrder_customer_id(rs.getInt("order_customer_id"));
                    item.setOrder_price(rs.getInt("order_price"));
                    item.setOrder_fullname_customer(rs.getString("order_fullname_customer"));
                    item.setOrder_phone(rs.getString("order_phone"));
                    item.setOrder_payments(rs.getString("order_payments"));
                    item.setOrder_status(rs.getBoolean("order_status"));
                    item.setOrder_email(rs.getString("order_email"));
                    item.setOrder_delivery_date(rs.getString("order_delivery_date"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    public OrderObject getOrderObjectPrice(int price){
        OrderObject item = null;
        ResultSet rs = this.o.getOrderPrice(price);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new OrderObject();
                    item.setOrder_id(rs.getInt("order_id"));
                    item.setOrder_title(rs.getString("order_title"));
                    item.setOrder_address(rs.getString("order_address"));
                    item.setOrder_date(rs.getString("order_date"));
                    item.setOrder_note(rs.getString("order_note"));
                    item.setOrder_customer_id(rs.getInt("order_customer_id"));
                    item.setOrder_price(rs.getInt("order_price"));
                    item.setOrder_fullname_customer(rs.getString("order_fullname_customer"));
                    item.setOrder_phone(rs.getString("order_phone"));
                    item.setOrder_payments(rs.getString("order_payments"));
                    item.setOrder_status(rs.getBoolean("order_status"));
                    item.setOrder_email(rs.getString("order_email"));
                    item.setOrder_delivery_date(rs.getString("order_delivery_date"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Lấy nhiều bản ghi
    public ArrayList getOrderObject(OrderObject similar, short page, byte total){
        ArrayList<OrderObject> items = new ArrayList<>();
        OrderObject item = null;
        short at =(short)((page-1)*total);
        ResultSet rs = this.o.getOrders(null, at, total);
        if(rs!=null){
            try {
                while(rs.next()){
                    item = new OrderObject();
                    item.setOrder_id(rs.getInt("order_id"));
                    item.setOrder_title(rs.getString("order_title"));
                    item.setOrder_address(rs.getString("order_address"));
                    item.setOrder_date(rs.getString("order_date"));
                    item.setOrder_note(rs.getString("order_note"));
                    item.setOrder_customer_id(rs.getInt("order_customer_id"));
                    item.setOrder_price(rs.getInt("order_price"));
                    item.setOrder_fullname_customer(rs.getString("order_fullname_customer"));
                    item.setOrder_phone(rs.getString("order_phone"));
                    item.setOrder_payments(rs.getString("order_payments"));
                    item.setOrder_status(rs.getBoolean("order_status"));
                    item.setOrder_email(rs.getString("order_email"));
                    item.setOrder_delivery_date(rs.getString("order_delivery_date"));
                    
                    items.add(item);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }
}
