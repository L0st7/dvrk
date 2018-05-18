/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp;

import java.sql.Connection;

/**
 *
 * @author nguye
 */
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectionPoolImpl implements ConnectionPool{

    //Trình điều khiển làm việc với mysql
    private String driver;
    
    //Đường dẫn thực thi
    private String url;
    
    //Tài khoản làm việc với mysql
    private String username;
    private String userpass;
    
    //Đối tượng lưu trữ kết nối
    private Stack pool;
    
    public ConnectionPoolImpl() throws ClassNotFoundException{
        //Xác định trình điều khiển
        this.driver = "com.mysql.jdbc.Driver";
        
        //nạp trình điều khiển
        this.loadDriver();
        
        //Xác định đường dẫn thực thi
        this.url = "jdbc:mysql://localhost:3306/dvrk.com?useUnicode=yes&characterEncoding=UTF-8";
        
        //Xác định tài khoản làm việc
        this.username = "root";
        this.userpass = "123456";
        
        //Khởi tạo bộ nhớ cho đối tượng lưu trữ kết nối
        this.pool = new Stack();
    }
    
    //Nạp trình điều khiển
    public void loadDriver() throws ClassNotFoundException{
        try {
            Class.forName(this.driver).newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public Connection getConnection(String objectName) throws Throwable {
        if(this.pool.isEmpty()){
            System.out.println(objectName+ " khởi tạo kết nối");
            return DriverManager.getConnection(this.url, this.username, this.userpass);
        }else{
            System.out.println(objectName+ " đã có kết nối");
            return (Connection) this.pool.pop();
        }
    }

    @Override
    public void releaseConnection(Connection con, String objectName) throws Throwable {
        System.out.println(objectName+ " trả lại kết nối");
        this.pool.push(con);
    }
    
    //Hủy các kết nối
    protected void finalize() throws Throwable{
        this.driver = null;
        this.url = null;
        this.username = null;
        this.userpass = null;
        this.pool.clear();
        this.pool = null;
        
        super.finalize();
        System.out.println("ConnectionPool đã đóng");
    }
    public static void main(String[] args) throws ClassNotFoundException, Throwable {
        ConnectionPool cp = new ConnectionPoolImpl();
        cp.getConnection("Login");
    }
}
