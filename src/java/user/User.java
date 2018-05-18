/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author nguye
 */
import basic.Basic;
import cp.*;
import object.*;
import java.sql.*;
public interface User extends ShareControl{
    //Các chức năng cập nhập
    public boolean addUser(UserObject item);
    public boolean editUser(UserObject item);
    public boolean delUser(UserObject item);
    
    //Các chức năng lấy dữ liệu
    public int getUserCount();
    public ResultSet getUser(int id);
    public ResultSet getUser(String username, String userpass);
    public ResultSet getUsers(UserObject similar, int at, byte total);
}
