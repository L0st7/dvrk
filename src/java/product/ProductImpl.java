/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import basic.BasicImpl;
import cp.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author nguye
 */
public class ProductImpl extends BasicImpl implements Product{
    public ProductImpl(ConnectionPool cp) throws Throwable{
        super(cp,"Product");
    }

    @Override
    public boolean addProduct(ProductObject item) {
        String sql = "INSERT INTO tblproduct( ";
        sql += "product_name, product_image, ";
        sql += "product_price, product_visited, ";
        sql += "product_total, product_intro, ";
        sql += "product_size, ";
        sql += "product_category_id, ";
        sql += "product_image2, product_image3, ";
        sql += "product_image4, product_image5 )";
        sql += "VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getProduct_name());
            pre.setString(2, item.getProduct_image());
            pre.setInt(3, item.getProduct_price());
            pre.setInt(4, item.getProduct_visited());
            pre.setInt(5, item.getProduct_total());
            pre.setString(6, item.getProduct_intro());
            pre.setString(7, item.getProduct_size());
            pre.setInt(8, item.getProduct_category_id());
            pre.setString(9, item.getProduct_image2());
            pre.setString(10, item.getProduct_image3());
            pre.setString(11, item.getProduct_image4());
            pre.setString(12, item.getProduct_image5());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editProduct(ProductObject item) {
        String sql = "UPDATE tblproduct SET ";
        sql += "product_name=?, product_image=?, ";
        sql += "product_price=?, product_visited=?, ";
        sql += "product_total=?, product_intro=?, ";
        sql += "product_size=?, ";
        sql += "product_category_id=?, ";
        sql += "product_image2=?, product_image3=?, ";
        sql += "product_image4=?, product_image5=? ";
        sql += "WHERE product_id=?";
        
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getProduct_name());
            pre.setString(2, item.getProduct_image());
            pre.setInt(3, item.getProduct_price());
            pre.setInt(4, item.getProduct_visited());
            pre.setInt(5, item.getProduct_total());
            pre.setString(6, item.getProduct_intro());
            pre.setString(7, item.getProduct_size());
            pre.setInt(8, item.getProduct_category_id());
            pre.setString(9, item.getProduct_image2());
            pre.setString(10, item.getProduct_image3());
            pre.setString(11, item.getProduct_image4());
            pre.setString(12, item.getProduct_image5());
            
            pre.setInt(13, item.getProduct_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delProduct(ProductObject item) {
        String sql = "DELETE FROM tblproduct WHERE product_id = ?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getProduct_id());
            
            return this.del(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int getProductCount() {
        int count = 0;
        String sql = "SELECT count(product_id) FROM tblproduct";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public ResultSet getProduct(int id) {
        String sql = "SELECT * FROM tblproduct WHERE product_id=?";
        return this.get(sql, id);
    }
    
    @Override
    public ResultSet getProduct(String productName){
        String sql = "SELECT * FROM tblproduct WHERE product_name=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, productName);
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public ResultSet searchProduct(String productName){
        String sql = "SELECT * FROM tblproduct WHERE product_name LIKE ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%"+productName+"%");
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getProducts(ProductObject similar, short at, byte total) {
        String sql = "SELECT * FROM tblproduct ";
        sql += "ORDER BY product_name ASC ";
        sql += "LIMIT "+at+", "+total;
        return this.gets(sql);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        Product p = new ProductImpl(cp);
        ProductObject po = new ProductObject();
        
        System.out.println(p.getProductCount());
        
        po.setProduct_id(16);
        po.setProduct_image("../images/upload/11_5_1_master.jpg");
        po.setProduct_intro("");
        po.setProduct_price(2000000);
        po.setProduct_size("L");
        po.setProduct_total(10);
        po.setProduct_visited(0);
        po.setProduct_category_id(1);
        
        
        //Lấy danh sách
        ResultSet rs = p.searchProduct("hoodie");
        
        //Duyệt và hiển thị
        String row;
        if(rs!=null){
            while(rs.next()){
                row = "ID: " + rs.getInt("product_id");
                row += "\tPRODUCTNAME: " + rs.getString("product_name");
                row += "\tPRODUCTIMG: " + rs.getString("product_image");
                row += "\tGIA: " + rs.getInt("product_price");
                row += "\tLUOTXEM: "+ rs.getInt("product_visited");
                row += "\tTOTAL: "+ rs.getInt("product_total");
                row += "\tINTRO:"+rs.getString("product_intro");
                row += "\tSIZE:"+rs.getString("product_size");
                row += "\tCATEGORY_ID:"+rs.getInt("product_category_id");
                
                System.out.println(row);
            }
        }
        
    }
    
}
