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
public interface ShareControl {
    //Chia sẻ bộ quản lí kết nối giữa các basic
    public ConnectionPool getCP();
    
    //Ra lệnh các đối tượng trả lại kết nối
    public void releaseConnection();
}
