/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UserServlet extends HttpServlet {

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
            case "delete":
                
                nUser.setUser_id(Integer.parseInt(userID));
                uc.delUser(nUser);
                
                RequestDispatcher rd = request.getRequestDispatcher("view_user.jsp");
                rd.forward(request, response);
                break;
                
            case "add":
                nUser.setUser_name(add_username);
                System.out.println(nUser.getUser_name());
                nUser.setUser_pass(add_password);
                nUser.setUser_fullname(add_fullname);
                nUser.setUser_email(add_email);
                uc.addUser(nUser);
                
                response.sendRedirect("view_user.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
            case "add":
                nUser.setUser_name(add_username);
                System.out.println(nUser.getUser_name());
                nUser.setUser_pass(add_password);
                nUser.setUser_fullname(add_fullname);
                nUser.setUser_email(add_email);
                uc.addUser(nUser);
                
                response.sendRedirect("view_user.jsp");
                break;
            case "edit":
                int uID = Integer.parseInt(eduserID);
                nUser.setUser_id(uID);
                nUser.setUser_name(add_username);
                nUser.setUser_pass(add_password);
                nUser.setUser_email(add_email);
                nUser.setUser_fullname(add_fullname);
                uc.editUser(nUser);
                
                response.sendRedirect("view_user.jsp");
                break;
        }
    }

}
