/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import cp.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.*;

/**
 *
 * @author nguye
 */
public class CategoryControl {
    private CategoryModel cm;
    public CategoryControl(ConnectionPool cp){
        this.cm = new CategoryModel(cp);
    }
    //Phương thức dọn dẹp đối tượng
    public void finalize()throws Throwable{
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
    //Chuyển các điều khiển cho các phương thức thêm , sửa, xóa;
    public boolean addCategory(CategoryObject item){
        return this.cm.addCategory(item);
    }
    public boolean editCategory(CategoryObject item){
        return this.cm.editCategory(item);
    }
    public boolean delCategory(CategoryObject item){
        return this.cm.delCategory(item);
    }
    
    //Lấy ra bản ghi đối tượng theo id
    public CategoryObject getCategoryObject(int id){
        return this.cm.getCategoryObject(id);
    }
    //Lấy ra bản ghi đối tượng theo tên
    public CategoryObject getCategoryObject(String categoryName){
        return this.cm.getCategoryObject(categoryName);
    }
    //Lấy tổng số bản ghi
    public int getCategoryCount(){
        return this.cm.getCategoryCount();
    }
    
    //Lấy ra danh sách bản ghi
    public ArrayList getCategoryObject(CategoryObject similar, short page, byte total){
        return this.cm.getCategoryObject(similar, page, total);
    }
}
