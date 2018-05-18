/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import cp.ShareControl;
import java.sql.ResultSet;
import object.CustomerObject;

/**
 *
 * @author nguye
 */
public interface Customer extends ShareControl{
    public boolean addCustomer(CustomerObject item);
    public boolean editCustomer(CustomerObject item);
    public boolean delCustomer(CustomerObject item);
    
    public ResultSet getCustomer(String customerName);
    public ResultSet getCustomer(int id);
    public int getCoutCustomer();
    public ResultSet getCustomer(String email, String pass);
}
