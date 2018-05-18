/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import cp.*;
import java.util.*;
import object.ProductObject;

/**
 *
 * @author nguye
 */
public class ProductControl {
    private ProductModel pm;
    public ProductControl(ConnectionPool cp){
        this.pm = new ProductModel(cp);
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.pm = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.pm.getCP();
    }
    
    public void releaseConnection(){
        this.pm.releaseConnection();
    }
    
    //Chuyển các điều khiển cho các phương thức thêm, sửa, xóa
    public boolean addProduct(ProductObject item){
        return this.pm.addProduct(item);
    }
    public boolean editProduct(ProductObject item){
        return this.pm.editProduct(item);
    }
    public boolean delProduct(ProductObject item){
        return this.pm.delProduct(item);
    }
    
    //Lấy số lượng bản ghi
    public int getProductCount(){
        return this.pm.getProductCount();
    }
    //Lấy bản ghi đối tượng theo id
    public ProductObject getProductObject(int id){
        return this.pm.getProductObject(id);
    }
    
    
    //Lấy bản ghi đối tượng theo tên
    public ProductObject getProductObject(String productName){
        return this.pm.getProductObject(productName);
    }
    
    public ArrayList searchProductObject(String productName){
        return this.pm.searchproductObject(productName);
    }
    //Lấy danh sách bản ghi
    public ArrayList getProductObject(ProductObject similar, short page, byte total){
        return this.pm.getProductObject(similar, page, total);
    }
    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionPool cp = new ConnectionPoolImpl();
        ProductControl pc = new ProductControl(cp);
        ArrayList<ProductObject> items = pc.searchProductObject("hoodie");
        for(ProductObject po : items){
            System.out.println(po.getProduct_name());
        }
    }
}
