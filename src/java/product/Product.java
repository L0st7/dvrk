/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import cp.ShareControl;
import java.sql.*;
import object.*;

/**
 *
 * @author nguye
 */
public interface Product extends ShareControl{
    //Các chức năng cập nhật
    public boolean addProduct(ProductObject item);
    public boolean editProduct(ProductObject item);
    public boolean delProduct(ProductObject item);
    
    //Các chức năng lấy dữ liệu
    public int getProductCount();
    public ResultSet searchProduct(String productName);
    public ResultSet getProduct(String productName);
    public ResultSet getProduct(int id);
    public ResultSet getProducts(ProductObject similar, short at, byte total);
}
