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
import java.util.*;
import object.*;
public class UserLibrary {
    public UserLibrary(){
        
    }
    
    public static String viewUser(ArrayList<UserObject> items){
        String tmp ="<table cellspacing=0>";
        tmp += "<tr>";
        tmp += "<th>STT</th>";
        tmp += "<th>Tên đăng nhập</th>";
        tmp += "<th>Mật khẩu</th>";
        tmp += "<th>Họ và tên</th>";
        tmp += "<th>Email</th>";
        tmp += "<th>ID</th>";
        tmp += "</tr>";
        tmp += "\n";


        int NO=0;
        for(UserObject item: items){
            ++NO; //Tang bien thu tu
            tmp += "<tr>";
            tmp += "<td>"+NO+"</td>";
            tmp += "<td>"+item.getUser_name()+"</td>";
            tmp += "<td>"+item.getUser_pass()+"</td>";
            tmp += "<td>"+item.getUser_fullname()+"</td>";
            tmp += "<td>"+item.getUser_email()+"</td>";
            tmp += "<td>Sửa</td>";
            tmp += "<td>Xóa</td>";
            tmp += "<td>"+item.getUser_id()+"</td>";
            tmp += "</tr>";

            tmp += "\n";
        }

        tmp += "</table>";
        return tmp;
    }
}
