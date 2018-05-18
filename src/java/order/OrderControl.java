/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import cp.ConnectionPool;
import java.util.ArrayList;
import object.OrderObject;
import object.ProductObject;

/**
 *
 * @author nguye
 */
public class OrderControl {
    private OrderModel om;
    public OrderControl(ConnectionPool cp){
        this.om = new OrderModel(cp);
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.om =null;
        super.finalize();
    }
    //Chia sẻ bộ quản lí két nối
    public ConnectionPool getCP(){
        return this.om.getCP();
    }
    
    public void releaseConnection(){
        this.om.releaseConnection();
    }
    
    //Chuyển các điều khiển cho các phương thức thêm, sửa xóa
    public boolean addOrder(OrderObject item){
        return this.om.addOrder(item);
    }
    public boolean editOrder(OrderObject item){
        return this.om.editOrder(item);
    }
    public boolean delOrder(OrderObject item){
        return this.om.delOrder(item);
    }
    
    //Lấy số lượng bản ghi
    public int getOrderCount(){
        return this.om.getOrderCount();
    }
    
    //Lấy bản ghi đối tượng theo id
    public OrderObject getOrderObject(int id){
        return this.om.getOrderObject(id);
    }
    
    public OrderObject getOrderObject(String order_date){
        return this.om.getOrderObject(order_date);
    }
    
    public OrderObject getOrderObjectPrice(int price){
        return this.om.getOrderObjectPrice(price);
    }
    
    //Lấy danh sách bản ghi
    public ArrayList getOrderObject(OrderObject similar,short page, byte total){
        return this.om.getOrderObject(similar, page, total);
    }
}
