/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import basic.*;
import cp.*;
import java.sql.*;
import object.*;

/**
 *
 * @author nguye
 */
public interface Order extends ShareControl{
    //Các chức năng cập nhật dữ liệu
    public boolean addOrder(OrderObject item);
    public boolean editOrder(OrderObject item);
    public boolean delOrder(OrderObject item);
    
    //Các chức năng lấy dữ liệu
    public int getOrderCount();
    public ResultSet getOrder(int id);
    public ResultSet getOrder(String order_date);
    public ResultSet getOrderPrice(int price);
    public ResultSet getOrders(OrderObject similar,short at, byte total);
}
