/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import cp.ShareControl;
import java.sql.ResultSet;
import object.OrderDetailObject;
import object.OrderObject;

/**
 *
 * @author nguye
 */
public interface OrderDetail extends ShareControl{
    //Các chức năng cập nhật dữ liệu
    public boolean addOrderDetail(OrderDetailObject item);
    public boolean editOrderDetail(OrderDetailObject item);
    public boolean delOrderDetail(OrderDetailObject item);
    
    //Các chức năng lấy dữ liệu
    public int getOrderDetailCount();
    public ResultSet getOrderDetail(int id);
    public ResultSet getOrdersDetail(OrderDetailObject similar,short at, byte total);
}
