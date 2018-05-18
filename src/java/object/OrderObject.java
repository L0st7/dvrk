/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.sql.Date;

/**
 *
 * @author nguye
 */
public class OrderObject {
    private int order_id;
    private String order_title;
    private String order_address;
    private String order_date;
    private String order_note;
    private int order_customer_id;
    private int order_price;
    private String order_fullname_customer;
    private String order_phone;
    private String order_payments;
    private boolean order_status;
    private String order_email;
    private String order_delivery_date;

    public OrderObject() {
    }

    public OrderObject(int order_id, String order_title, String order_address, String order_date, String order_note, int order_customer_id, int order_price, String order_fullname_customer, String order_phone, String order_payments, boolean order_status, String order_email, String order_delivery_date) {
        this.order_id = order_id;
        this.order_title = order_title;
        this.order_address = order_address;
        this.order_date = order_date;
        this.order_note = order_note;
        this.order_customer_id = order_customer_id;
        this.order_price = order_price;
        this.order_fullname_customer = order_fullname_customer;
        this.order_phone = order_phone;
        this.order_payments = order_payments;
        this.order_status = order_status;
        this.order_email = order_email;
        this.order_delivery_date = order_delivery_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public int getOrder_customer_id() {
        return order_customer_id;
    }

    public void setOrder_customer_id(int order_customer_id) {
        this.order_customer_id = order_customer_id;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    public String getOrder_fullname_customer() {
        return order_fullname_customer;
    }

    public void setOrder_fullname_customer(String order_fullname_customer) {
        this.order_fullname_customer = order_fullname_customer;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_payments() {
        return order_payments;
    }

    public void setOrder_payments(String order_payments) {
        this.order_payments = order_payments;
    }

    public boolean isOrder_status() {
        return order_status;
    }

    public void setOrder_status(boolean order_status) {
        this.order_status = order_status;
    }

    public String getOrder_email() {
        return order_email;
    }

    public void setOrder_email(String order_email) {
        this.order_email = order_email;
    }

    public String getOrder_delivery_date() {
        return order_delivery_date;
    }

    public void setOrder_delivery_date(String order_delivery_date) {
        this.order_delivery_date = order_delivery_date;
    }

    @Override
    public String toString() {
        return "OrderObject{" + "order_id=" + order_id + ", order_title=" + order_title + ", order_address=" + order_address + ", order_date=" + order_date + ", order_note=" + order_note + ", order_customer_id=" + order_customer_id + ", order_price=" + order_price + ", order_fullname_customer=" + order_fullname_customer + ", order_phone=" + order_phone + ", order_payments=" + order_payments + ", order_status=" + order_status + ", order_email=" + order_email + ", order_delivery_date=" + order_delivery_date + '}';
    }

    
}
