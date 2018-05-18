/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author nguye
 */
public class OrderDetailObject {
    private int orderdetail_id;
    private int orderdetail_product_id;
    private int orderdetail_quantity;
    private int orderdetail_price;
    private int orderdetail_order_id;

    public OrderDetailObject() {
    }

    public OrderDetailObject(int orderdetail_id, int orderdetail_product_id, int orderdetail_quantity, int orderdetail_price, int orderdetail_order_id) {
        this.orderdetail_id = orderdetail_id;
        this.orderdetail_product_id = orderdetail_product_id;
        this.orderdetail_quantity = orderdetail_quantity;
        this.orderdetail_price = orderdetail_price;
        this.orderdetail_order_id = orderdetail_order_id;
    }

    public int getOrderdetail_id() {
        return orderdetail_id;
    }

    public void setOrderdetail_id(int orderdetail_id) {
        this.orderdetail_id = orderdetail_id;
    }

    public int getOrderdetail_product_id() {
        return orderdetail_product_id;
    }

    public void setOrderdetail_product_id(int orderdetail_product_id) {
        this.orderdetail_product_id = orderdetail_product_id;
    }

    public int getOrderdetail_quantity() {
        return orderdetail_quantity;
    }

    public void setOrderdetail_quantity(int orderdetail_quantity) {
        this.orderdetail_quantity = orderdetail_quantity;
    }

    public int getOrderdetail_price() {
        return orderdetail_price;
    }

    public void setOrderdetail_price(int orderdetail_price) {
        this.orderdetail_price = orderdetail_price;
    }

    public int getOrderdetail_order_id() {
        return orderdetail_order_id;
    }

    public void setOrderdetail_order_id(int orderdetail_order_id) {
        this.orderdetail_order_id = orderdetail_order_id;
    }
    
    
}
