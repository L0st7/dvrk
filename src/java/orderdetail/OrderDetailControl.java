/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import cp.ConnectionPool;
import java.util.ArrayList;
import object.OrderDetailObject;
import object.OrderObject;
import order.OrderModel;

/**
 *
 * @author nguye
 */
public class OrderDetailControl {
    private OrderDetailModel odm;
    public OrderDetailControl(ConnectionPool cp){
        this.odm = new OrderDetailModel(cp);
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.odm = null;
        super.finalize();
    }
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.odm.getCP();
    }
    
    public void releaseConnection(){
        this.odm.releaseConnection();
    }
    //Chuyển các điều khiển cho các phương thức thêm, sửa xóa
    public boolean addOrderDetail(OrderDetailObject item){
        return this.odm.addOrderDetail(item);
    }
    public boolean editOrderDetail(OrderDetailObject item){
        return this.odm.editOrderDetail(item);
    }
    public boolean delOrderDetail(OrderDetailObject item){
        return this.odm.delOrderDetail(item);
    }
    
    //Lấy số lượng bản ghi
    public int getOrderDetailCount(){
        return this.odm.getOrderDetailCount();
    }
    
    //Lấy bản ghi đối tượng theo id
    public OrderDetailObject getOrderDetailObject(int id){
        return this.odm.getOrderDetailObject(id);
    }

    
    //Lấy danh sách bản ghi
    public ArrayList getOrderDetailObject(OrderDetailObject similar,short page, byte total){
        return this.odm.getOrderObject(similar, page, total);
    }
}
