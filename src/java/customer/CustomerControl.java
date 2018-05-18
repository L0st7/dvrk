/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import object.CustomerObject;

/**
 *
 * @author nguye
 */
public class CustomerControl {
    private CustomerModel cm;
    
    public CustomerControl(ConnectionPool cp){
        try {
            this.cm = new CustomerModel(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    //Phương thức dọn dẹp đối tượng
    protected void finalize()throws Throwable{
        this.cm = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.cm.getCP();
    }
    
    public void releaseConnection(){
        this.cm.releaseConnection();
    }
    
    //Chuyển các điều khiển cho phương thức thêm, sửa, xóa
    public boolean addCustomer(CustomerObject item){
        return this.cm.addCustomer(item);
    }
    public boolean editUser(CustomerObject item){
        return this.cm.editCustomer(item);
    }
    public boolean delUser(CustomerObject item){
        return this.cm.delCustomer(item);
    }
    
    public CustomerObject getCustomerObject(int id){
        return this.cm.getCustomerObject(id);
    }
    
    public CustomerObject getCustomerObject(String customerName){
        return this.cm.getCustomerObject(customerName);
    }
    
    public CustomerObject getCustomerObject(String email, String pass){
        return this.cm.getCustomerObject(email, pass);
    }
    public int getCountCustomer(){
        return this.cm.getCountCustomer();
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionPool cp = new ConnectionPoolImpl();
        CustomerControl cc = new CustomerControl(cp);
        CustomerObject co = cc.getCustomerObject("nguyenhiep96vn@gmail.com", "123456");
        System.out.println(co.toString());
    }
}
