/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import basic.BasicImpl;
import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.CategoryObject;

/**
 *
 * @author nguye
 */
public class CategoryImpl extends BasicImpl implements Category{
    
    public CategoryImpl(ConnectionPool cp) throws Throwable{
        super(cp,"Category");
    }
    @Override
    public boolean addCategory(CategoryObject item) {
        if(this.isExisting(item)){
            return false;
        }
        String sql = "INSERT INTO tblcategory( ";
        sql += "category_name, category_image, ";
        sql += "category_notes) ";
        sql += "VALUE(?,?,?)";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCategory_name());
            pre.setString(2, item.getCategory_image());
            pre.setString(3, item.getCategory_notes());
            
            return this.add(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    //Kiểm tra tính duy nhất
    public boolean isExisting(CategoryObject item){
        boolean flag = false;
        String sql = "SELECT category_id FROM tblcategory WHERE ";
        sql += "category_name='"+item.getCategory_name()+"'";
        ResultSet rs = this.gets(sql);
        if(rs!=null){
            try {
                if(rs.next()){
                    flag = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }
    @Override
    public boolean editCategory(CategoryObject item) {
        String sql = "UPDATE tblcategory SET ";
        sql += "category_name=?, category_image=?, ";
        sql += "category_notes=? ";
        sql += "WHERE category_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, item.getCategory_name());
            pre.setString(2, item.getCategory_image());
            pre.setString(3, item.getCategory_notes());
            pre.setInt(4, item.getCategory_id());
            
            return this.edit(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delCategory(CategoryObject item) {
        String sql = "DELETE FROM tblcategory WHERE category_id=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getCategory_id());
            
            return this.del(pre);
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int getCategoryCount() {
        int count =0;
        String sql = "SELECT count(category_id) FROM tblcategory";
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
    public ResultSet getCategory(int id) {
        String sql = "SELECT * FROM tblcategory WHERE category_id=?";
        return this.get(sql, id);
    }
    
    @Override
    public ResultSet getCategory(String categoryName){
        String sql = "SELECT * FROM tblcategory WHERE category_name=?";
        try {
            //Biên dịch
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, categoryName);
            
            return pre.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getCategorys(CategoryObject similar, short at, byte total) {
        String sql = "SELECT * FROM tblcategory ";
        sql += "ORDER BY category_name ASC ";
        sql += "LIMIT "+at+", "+total;
        return this.gets(sql);
    }
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        Category ca = new CategoryImpl(cp);
        
        System.out.println(ca.getCategoryCount());
        
        CategoryObject cao = new CategoryObject();
        //cao.setCategory_id(71);
        cao.setCategory_name("alssss");
        cao.setCategory_image("");
        cao.setCategory_notes("Chú ý");
        
        boolean Result = ca.addCategory(cao);
        if(!Result){
            System.out.println("Không thành công");
        }else{
            System.out.println("Thành công");
        }
        //Lấy danh sách category
        ResultSet rs = ca.getCategory("SHORTS");
        
        //Duyệt và hiển thị
        String row;
        if(rs!=null){
            while(rs.next()){
                row = "ID: " + rs.getInt("category_id");
                row += "\tCATEGORYNAME: " + rs.getString("category_name");
                row += "\tIMAGE: " + rs.getString("category_image");
                row += "\tNOTES: " + rs.getString("category_notes");
                
                System.out.println(row);
            }
        }
    }
    
}
