/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import basic.*;
import cp.*;
import java.sql.*;
import object.*;

/**
 *
 * @author nguye
 */
public interface Category extends ShareControl{
    //Các chức năng cập nhật
    public boolean addCategory(CategoryObject item);
    public boolean editCategory(CategoryObject item);
    public boolean delCategory(CategoryObject item);
    
    //Các chức năng lấy dữ liệu
    public int getCategoryCount();
    public ResultSet getCategory(String categoryName);
    public ResultSet getCategory(int id);
    public ResultSet getCategorys(CategoryObject similar,short at, byte total);
}
