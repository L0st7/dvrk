/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import basic.BasicImpl;
import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import object.OrderDetailObject;
import object.OrderObject;
import order.Order;
import order.OrderImpl;

/**
 *
 * @author nguye
 */
public class OrderDetailImpl extends BasicImpl implements OrderDetail{

    public OrderDetailImpl(ConnectionPool cp) throws Throwable{
        super(cp,"OrderDetail");
    }
    @Override
    public boolean addOrderDetail(OrderDetailObject item) {
        String sql = "INSERT INTO tblorderdetail( ";
        sql += "orderdetail_product_id, orderdetail_quantity, ";
        sql += "orderdetail_price, orderdetail_order_id )";
        sql += "VALUE(?,?,?,?)";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getOrderdetail_product_id());
            pre.setInt(2, item.getOrderdetail_quantity());
            pre.setInt(3, item.getOrderdetail_price());
            pre.setInt(4, item.getOrderdetail_order_id());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editOrderDetail(OrderDetailObject item) {
        String sql = "UPDATE tblorderdetail SET ";
        sql += "orderdetail_product_id=?, orderdetail_quantity=?, ";
        sql += "orderdetail_price=?, orderdetail_order_id=? ";
        sql += "WHERE orderdetail_id=?";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getOrderdetail_product_id());
            pre.setInt(2, item.getOrderdetail_quantity());
            pre.setInt(3, item.getOrderdetail_price());
            pre.setInt(4, item.getOrderdetail_order_id());
            
            pre.setInt(5, item.getOrderdetail_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delOrderDetail(OrderDetailObject item) {
        String sql = "DELETE FROM tblorderdetail WHERE orderdetail_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getOrderdetail_id());
            
            return this.del(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int getOrderDetailCount() {
        int count = 0;
        String sql = "SELECT count(orderdetail_id) FROM tblorderdetail";
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
    public ResultSet getOrderDetail(int id) {
        String sql = "SELECT * FROM tblorderdetail WHERE orderdetail_id=?";
        return this.get(sql, id);
    }

    @Override
    public ResultSet getOrdersDetail(OrderDetailObject similar, short at, byte total) {
        String sql = "SELECT * FROM tblorderdetail ";
        sql += "ORDER BY orderdetail_product_id ASC ";
        sql += "LIMIT "+at+", "+total;
        return this.gets(sql);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        OrderDetail od = new OrderDetailImpl(cp);
        OrderDetailObject odo = new OrderDetailObject();
        
        System.out.println(od.getOrderDetailCount());
        
        odo.setOrderdetail_id(1);
        odo.setOrderdetail_product_id(63);
        odo.setOrderdetail_quantity(2);
        odo.setOrderdetail_price(1000000);
        odo.setOrderdetail_order_id(23);
        
        boolean Result = od.addOrderDetail(odo);
        if(!Result){
            System.out.println("Không thành công");
        }else{
            System.out.println("Thành công");
        }
        
        ResultSet rs = od.getOrdersDetail(null, (short)0, (byte)10);
        
        String row = null;
        if(rs!=null){
            while(rs.next()){
                row = "ID: "+ rs.getInt("orderdetail_id");
                row += "\tPRODUCT_ID: "+rs.getInt("orderdetail_product_id");
                row += "\tSOLUONG: "+rs.getInt("orderdetail_quantity");
                row += "\tGIA: "+rs.getInt("orderdetail_price");
                row += "\tORDER_ID: "+rs.getInt("orderdetail_order_id");
                
                System.out.println(row);
            }
        }
    }
    
}
