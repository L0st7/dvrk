/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.UserObject;

/**
 *
 * @author nguye
 */
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String userID = request.getParameter("user_id");
        String add_username = request.getParameter("username");
        String add_password = request.getParameter("password");
        String add_fullname = request.getParameter("fullname");
        String add_email = request.getParameter("email");
        String eduserID = request.getParameter("userid");
        
        HttpSession session = request.getSession();
        
        //Tham chiếu ngữ cảnh ứng dụng(Không gian bộ nhớ để chạy cho các đối tượng)
        ServletContext application = getServletConfig().getServletContext();
                
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tạo đối tượng thực thi chức năng
        UserControl uc = new UserControl(cp);
        UserObject nUser = new UserObject();
        
        switch(command){
            case "logout":
                
                session.removeAttribute("UserName");
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserObject nUser = new UserObject();
        
        //Lấy thông tin đăng nhập trên giao diện
        String eduserID = request.getParameter("userid");
        String userID = request.getParameter("user_id");
        String username = request.getParameter("UserName");
        String userpass = request.getParameter("Password");
        String command = request.getParameter("command");
        String add_username = request.getParameter("username");
        String add_password = request.getParameter("password");
        String add_fullname = request.getParameter("fullname");
        String add_email = request.getParameter("email");
        //Tham chiếu ngữ cảnh ứng dụng(Không gian bộ nhớ để chạy cho các đối tượng)
        ServletContext application = getServletConfig().getServletContext();
                
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tạo đối tượng thực thi chức năng
        UserControl uc = new UserControl(cp);
        //Kiểm tra sự tồn tại của tham số
        UserObject uo = new UserObject();
        
        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession(true);
        if(username!=null && userpass!=null){
            //Cắt bỏ khoảng trắng
            username = username.trim();
            userpass = userpass.trim();
            
            //Kiểm tra sự tồn tại của giá trị
            if(!username.equalsIgnoreCase("") && !userpass.equalsIgnoreCase("")){

                if(cp==null){
                    //Hỏi xin lại basic
                    application.setAttribute("CPool", uc.getCP());
                }
                //Thực hiện đăng nhập
                UserObject user = uc.getUserObject(username, userpass);
                            
                //Trả về kết nối
                uc.releaseConnection();
                
                //Kiểm tra kết quả đăng nhập
                if(user!=null){

                    //Đưa thông tin đăng nhập vào phiên làm việc
                    session.setAttribute("userLogin", user);
                    session.setAttribute("UserName", username);
                              
                    //Chuyển về giao diện chính
                    response.sendRedirect("view_layout.jsp");
                }else{
                    response.sendRedirect("index.jsp?err=notok");
                }
                
            }else{
                response.sendRedirect("index.jsp?err=value");
            }
        }else{
            //Trở về giao diện đăng nhập và báo lỗi tham số
            response.sendRedirect("index.jsp?err=param");
        }
    }
    
}
