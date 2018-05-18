/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import cp.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author nguye
 */
public class ProductModel {
    private Product p;
    public ProductModel(ConnectionPool cp){
        try {
            this.p = new ProductImpl(cp);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.p =null;
        super.finalize();
    }
    //Chia sẻ bộ quản lí két nối
    public ConnectionPool getCP(){
        return this.p.getCP();
    }
    
    public void releaseConnection(){
        this.p.releaseConnection();
    }
    
    //Chuyển các điều khiển cho các phương thức thêm, sửa, xóa
    public boolean addProduct(ProductObject item){
        return this.p.addProduct(item);
    }
    public boolean editProduct(ProductObject item){
        return this.p.editProduct(item);
    }
    public boolean delProduct(ProductObject item){
        return this.p.delProduct(item);
    }
    
    //Lấy số lượng bản ghi
    public int getProductCount(){
        return this.p.getProductCount();
    }
    
    //Lấy bản ghi đối tượng theo id
    public ProductObject getProductObject(int id){
        ProductObject item = null;
        ResultSet rs = this.p.getProduct(id);
        if(rs!=null){
            try {
                while(rs.next()){
                    //khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new ProductObject();
                    item.setProduct_id(rs.getInt("product_id"));
                    item.setProduct_name(rs.getString("product_name"));
                    item.setProduct_image(rs.getString("product_image"));
                    item.setProduct_price(rs.getInt("product_price"));
                    item.setProduct_visited(rs.getInt("product_visited"));
                    item.setProduct_total(rs.getInt("product_total"));
                    item.setProduct_intro(rs.getString("product_intro"));
                    item.setProduct_size(rs.getString("product_size"));
                    item.setProduct_category_id(rs.getInt("product_category_id"));
                    item.setProduct_image2(rs.getString("product_image2"));
                    item.setProduct_image3(rs.getString("product_image3"));
                    item.setProduct_image4(rs.getString("product_image4"));
                    item.setProduct_image5(rs.getString("product_image5"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    
    //Lấy bản ghi đối tượng theo tên sp
     public ProductObject getProductObject(String productName){
        ProductObject item = null;
        ResultSet rs = this.p.getProduct(productName);
        if(rs!=null){
            try {
                while(rs.next()){
                    //khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new ProductObject();
                    item.setProduct_id(rs.getInt("product_id"));
                    item.setProduct_name(rs.getString("product_name"));
                    item.setProduct_image(rs.getString("product_image"));
                    item.setProduct_price(rs.getInt("product_price"));
                    item.setProduct_visited(rs.getInt("product_visited"));
                    item.setProduct_total(rs.getInt("product_total"));
                    item.setProduct_intro(rs.getString("product_intro"));
                    item.setProduct_size(rs.getString("product_size"));
                    item.setProduct_category_id(rs.getInt("product_category_id"));
                    item.setProduct_image2(rs.getString("product_image2"));
                    item.setProduct_image3(rs.getString("product_image3"));
                    item.setProduct_image4(rs.getString("product_image4"));
                    item.setProduct_image5(rs.getString("product_image5"));
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
     
     public ArrayList searchproductObject(String productName){
         ArrayList items = new ArrayList();
         ProductObject item = null;
         ResultSet rs = this.p.searchProduct(productName);
        if(rs!=null){
            try {
                while(rs.next()){
                    //khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new ProductObject();
                    item.setProduct_id(rs.getInt("product_id"));
                    item.setProduct_name(rs.getString("product_name"));
                    item.setProduct_image(rs.getString("product_image"));
                    item.setProduct_price(rs.getInt("product_price"));
                    item.setProduct_visited(rs.getInt("product_visited"));
                    item.setProduct_total(rs.getInt("product_total"));
                    item.setProduct_intro(rs.getString("product_intro"));
                    item.setProduct_size(rs.getString("product_size"));
                    item.setProduct_category_id(rs.getInt("product_category_id"));
                    item.setProduct_image2(rs.getString("product_image2"));
                    item.setProduct_image3(rs.getString("product_image3"));
                    item.setProduct_image4(rs.getString("product_image4"));
                    item.setProduct_image5(rs.getString("product_image5"));
                    
                    items.add(item);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return items;
     }
    
    //Lấy về nhiều đối tượng theo tên sp
    public ArrayList getProductObject(ProductObject similar, short page, byte total){
        ArrayList items = new ArrayList();
        ProductObject item = null;
        short at = (short)((page-1)*total);
        ResultSet rs = this.p.getProducts(null, at, total);
        if(rs!=null){
            try {
                while(rs.next()){
                    //khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new ProductObject();
                    item.setProduct_id(rs.getInt("product_id"));
                    item.setProduct_name(rs.getString("product_name"));
                    item.setProduct_image(rs.getString("product_image"));
                    item.setProduct_price(rs.getInt("product_price"));
                    item.setProduct_visited(rs.getInt("product_visited"));
                    item.setProduct_total(rs.getInt("product_total"));
                    item.setProduct_intro(rs.getString("product_intro"));
                    item.setProduct_size(rs.getString("product_size"));
                    item.setProduct_category_id(rs.getInt("product_category_id"));
                    item.setProduct_image2(rs.getString("product_image2"));
                    item.setProduct_image3(rs.getString("product_image3"));
                    item.setProduct_image4(rs.getString("product_image4"));
                    item.setProduct_image5(rs.getString("product_image5"));
                    
                    items.add(item);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }
    
}
