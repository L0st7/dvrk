/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp;

/**
 *
 * @author nguye
 */
import java.sql.*;
public interface ConnectionPool {
    //Cung cấp kết nối cho một đối tượng nào đó
    public Connection getConnection(String objectName) throws Throwable;
    
    //Yêu cầu các đối tượng trả lại kết nối
    public void releaseConnection(Connection con, String objectName) throws Throwable;
}
