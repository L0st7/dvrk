/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import cp.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author nguye
 */
public class CategoryModel {
    private Category c;
    public CategoryModel(ConnectionPool cp){
        try {
            this.c = new CategoryImpl(cp);
        } catch (Throwable ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
        this.c = null;
        super.finalize();
    }
    
    //Chia sẻ bộ quản lí kết nối
    public ConnectionPool getCP(){
        return this.c.getCP();
    }
    
    public void releaseConnection(){
        this.c.releaseConnection();
    }
    
    //Chuyển các điều khiển cho phương thức thêm, sửa, xóa
    public boolean addCategory(CategoryObject item){
        return this.c.addCategory(item);
    }
    public boolean editCategory(CategoryObject item){
        return this.c.editCategory(item);
    }
    public boolean delCategory(CategoryObject item){
        return this.c.delCategory(item);
    }
    //Đếm số bản ghi
    public int getCategoryCount(){
        return this.c.getCategoryCount();
    }
    
    //Lấy bản ghi đối tượng theo id
    public CategoryObject getCategoryObject(int id){
        CategoryObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.c.getCategory(id);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new CategoryObject();
                    item.setCategory_id(rs.getInt("category_id"));
                    item.setCategory_name(rs.getString("category_name"));
                    item.setCategory_image(rs.getString("category_image"));
                    item.setCategory_notes(rs.getString("category_notes"));
                    
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Lấy bản ghi đối tượng theo tên
    public CategoryObject getCategoryObject(String categoryName){
        CategoryObject item = null;
        //Lấy bản ghi đối tượng theo id
        ResultSet rs = this.c.getCategory(categoryName);
        if(rs!=null){
            try {
                while(rs.next()){
                    //Khởi tạo bộ nhớ cho đối tượng lưu trữ
                    item = new CategoryObject();
                    item.setCategory_id(rs.getInt("category_id"));
                    item.setCategory_name(rs.getString("category_name"));
                    item.setCategory_image(rs.getString("category_image"));
                    item.setCategory_notes(rs.getString("category_notes"));
                    
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return item;
    }
    
    //Lấy về nhiều đối tượng
    public ArrayList getCategoryObject(CategoryObject similar, short page, byte total){
        ArrayList items = new ArrayList();
        CategoryObject item = null;
        short at = (short)((page-1)*total);
        ResultSet rs = this.c.getCategorys(null, at, total);
        if(rs!=null){
            try {
                while(rs.next()){
                    item = new CategoryObject();
                    item.setCategory_id(rs.getInt("category_id"));
                    item.setCategory_name(rs.getString("category_name"));
                    item.setCategory_image(rs.getString("category_image"));
                    item.setCategory_notes(rs.getString("category_notes"));
                    
                    items.add(item);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }
}
