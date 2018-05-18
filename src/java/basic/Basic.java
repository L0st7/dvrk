/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

/**
 *
 * @author nguye
 */
import cp.ShareControl;
import java.sql.*;
public interface Basic extends ShareControl{
    //Các chức năng cập nhập dữ liệu
    public boolean add(PreparedStatement pre);
    public boolean edit(PreparedStatement pre);
    public boolean del(PreparedStatement pre);
    
    //Các chức năng lấy dữ liệu
    public ResultSet get(String sql, int value);
    public ResultSet get(String sql, String name, String pass);
    public ResultSet gets(String sql);
    public ResultSet[] gets(String[] sqls);
}
