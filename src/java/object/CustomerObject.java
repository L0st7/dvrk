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
public class CustomerObject {
    private int customer_id;
    private String customer_fullname;
    private String customer_address;
    private String customer_email;
    private String customer_mobile;
    private String customer_account;
    private String customer_password;

    public CustomerObject() {
    }

    public CustomerObject(int customer_id, String customer_fullname, String customer_address, String customer_email, String customer_mobile, String customer_account, String customer_password) {
        this.customer_id = customer_id;
        this.customer_fullname = customer_fullname;
        this.customer_address = customer_address;
        this.customer_email = customer_email;
        this.customer_mobile = customer_mobile;
        this.customer_account = customer_account;
        this.customer_password = customer_password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_fullname() {
        return customer_fullname;
    }

    public void setCustomer_fullname(String customer_fullname) {
        this.customer_fullname = customer_fullname;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer_account(String customer_account) {
        this.customer_account = customer_account;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    @Override
    public String toString() {
        return "CustomerObject{" + "customer_id=" + customer_id + ", customer_fullname=" + customer_fullname + ", customer_address=" + customer_address + ", customer_email=" + customer_email + ", customer_mobile=" + customer_mobile + ", customer_account=" + customer_account + ", customer_password=" + customer_password + '}';
    }

    
    
}
