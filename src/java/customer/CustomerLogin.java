/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.CustomerObject;
import object.UserObject;

/**
 *
 * @author nguye
 */
public class CustomerLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        HttpSession session = request.getSession();
        switch(command){
            case "logout":
                session.removeAttribute("CustomerLogin");
                response.sendRedirect("view_home_page.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customer_email = request.getParameter("customer_email");
        String customer_pass = request.getParameter("customer_pass");
        //Tham chiếu ngữ cảnh ứng dụng(Không gian bộ nhớ để chạy cho các đối tượng)
        ServletContext application = getServletConfig().getServletContext();
                
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tạo đối tượng thực thi chức năng
        
        CustomerControl ctc = new CustomerControl(cp);
        
        HttpSession session = request.getSession();
        if(customer_email!=null && customer_pass!=null){
            //Cắt bỏ khoảng trắng
            customer_email = customer_email.trim();
            customer_pass = customer_pass.trim();
            
            //Kiểm tra sự tồn tại của giá trị
            if(!customer_email.equalsIgnoreCase("") && !customer_pass.equalsIgnoreCase("")){

                if(cp==null){
                    //Hỏi xin lại basic
                    application.setAttribute("CPool", ctc.getCP());
                }
                //Thực hiện đăng nhập
                CustomerObject cto = ctc.getCustomerObject(customer_email, customer_pass);
                            
                //Trả về kết nối
                ctc.releaseConnection();
                
                //Kiểm tra kết quả đăng nhập
                if(cto!=null){

                    //Đưa thông tin đăng nhập vào phiên làm việc
                    session.setAttribute("CustomerLogin", cto);
                    session.setAttribute("CustomerAccount", cto.getCustomer_account());
                              
                    //Chuyển về giao diện chính
                    response.sendRedirect("view_home_page.jsp");
                }else{
                    response.sendRedirect("login?err=notok");
                }
                
            }else{
                response.sendRedirect("login.jsp?err=value");
            }
        }else{
            //Trở về giao diện đăng nhập và báo lỗi tham số
            response.sendRedirect("login.jsp?err=param");
        }
    }

}
