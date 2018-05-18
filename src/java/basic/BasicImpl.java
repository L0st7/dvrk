/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import cp.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class BasicImpl implements Basic{
    
    //Bộ quản lí kết nối của riêng basic
    private ConnectionPool cp;
    
    //Kết nối để basic làm việc với csdl
    protected Connection con;
    
    //Đối tượng làm việc với basic
    private String objectName;
    
    public BasicImpl(ConnectionPool cp, String objectName) throws Throwable{
        //Xác định đối tượng làm việc với basic
        this.objectName = objectName;
        
        //Xác định bộ quản lí kết nối cho basic
        if(cp==null){
            try {
                this.cp = new ConnectionPoolImpl();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }else{
            this.cp = cp;
        }
        
        try {
            //Xin kết nối để làm việc với csdl
            this.con = this.cp.getConnection(objectName);
            //Kiểm tra chế độ thực thi
            if(this.con.getAutoCommit()){
                //Chấm dứt chế độ thực thi tự động
                this.con.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized boolean exe(PreparedStatement pre){
        boolean flag = false;
        if(pre!= null){
            try {
                int result = pre.executeUpdate();
                //Kiểm tra kết quả
                if(result == 0){
                    this.con.rollback();
                }else{
                    //Xác nhận thực thi sau cùng
                    this.con.commit();
                    flag = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                
                try {
                    //Trở lại trạng thái an toàn
                    this.con.rollback();
                } catch (SQLException ex1) {
                    ex.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean add(PreparedStatement pre) {
        return this.exe(pre);
    }

    @Override
    public boolean edit(PreparedStatement pre) {
        return this.exe(pre);
    }

    @Override
    public boolean del(PreparedStatement pre) {
        return this.exe(pre);
    }

    @Override
    public ResultSet get(String sql, int value) {
        try {
            //Biên dịch câu lệnh
            PreparedStatement preGet = this.con.prepareStatement(sql);
            if(value>0){
                preGet.setInt(1, value);
            }
            return preGet.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ResultSet get(String sql, String name, String pass) {
        try {
            //Biên dịch câu lệnh
            PreparedStatement preGet = this.con.prepareStatement(sql);
            preGet.setString(1, name);
            preGet.setString(2, pass);
            return preGet.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ResultSet gets(String sql) {
        return this.get(sql, 0);
    }

    @Override
    public ResultSet[] gets(String[] sqls) {
        ResultSet[] tmp = new ResultSet[sqls.length];
        for(int i=0;i<sqls.length;i++){
            tmp[i] = this.get(sqls[i], 0);
        }
        return tmp;
    }

    @Override
    public ConnectionPool getCP() {
        return this.cp;
    }

    @Override
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, this.objectName);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
}
