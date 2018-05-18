/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import cp.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import object.OrderDetailObject;
import object.OrderObject;
import order.Order;
import order.OrderImpl;

/**
 *
 * @author nguye
 */
public class OrderDetailModel {
    private OrderDetail od;
    public OrderDetailModel(ConnectionPool cp){
        try {
            this.od = new OrderDetailImpl(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.od = null;
        super.finalize();
    }
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.od.getCP();
    }
    
    public void releaseConnection(){
        this.od.releaseConnection();
    }
    //Chuyển các điều khiển cho các phương thức thêm, sửa xóa
    public boolean addOrderDetail(OrderDetailObject item){
        return this.od.addOrderDetail(item);
    }
    public boolean editOrderDetail(OrderDetailObject item){
        return this.od.editOrderDetail(item);
    }
    public boolean delOrderDetail(OrderDetailObject item){
        return this.od.delOrderDetail(item);
    }
    
    //Lấy số lượng bản ghi
    public int getOrderDetailCount(){
        return this.od.getOrderDetailCount();
    }
    
    //Lấy bản ghi theo id
    public OrderDetailObject getOrderDetailObject(int id){
        OrderDetailObject item = null;
        ResultSet rs = this.od.getOrderDetail(id);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new OrderDetailObject();
                    item.setOrderdetail_id(rs.getInt("orderdetail_id"));
                    item.setOrderdetail_product_id(rs.getInt("orderdetail_product_id"));
                    item.setOrderdetail_quantity(rs.getInt("orderdetail_quantity"));
                    item.setOrderdetail_price(rs.getInt("orderdetail_price"));
                    item.setOrderdetail_order_id(rs.getInt("orderdetail_order_id"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Lấy nhiều bản ghi
    public ArrayList getOrderObject(OrderDetailObject similar, short page, byte total){
        ArrayList<OrderDetailObject> items = new ArrayList<>();
        OrderDetailObject item = null;
        short at =(short)((page-1)*total);
        ResultSet rs = this.od.getOrdersDetail(null, at, total);
        if(rs!=null){
            try {
                while(rs.next()){
                    item = new OrderDetailObject();
                    item.setOrderdetail_id(rs.getInt("orderdetail_id"));
                    item.setOrderdetail_product_id(rs.getInt("orderdetail_product_id"));
                    item.setOrderdetail_quantity(rs.getInt("orderdetail_quantity"));
                    item.setOrderdetail_price(rs.getInt("orderdetail_price"));
                    item.setOrderdetail_order_id(rs.getInt("orderdetail_order_id"));
                    
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
