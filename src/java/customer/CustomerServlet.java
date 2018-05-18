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

/**
 *
 * @author nguye
 */
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String customer_name = request.getParameter("customer_name");
        String customer_account = request.getParameter("customer_account");
        String customer_mobile = request.getParameter("customer_mobile");
        String customer_address = request.getParameter("customer_address");
        String customer_email = request.getParameter("customer_email");
        String customer_password = request.getParameter("customer_password");
        
        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession();

        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();

        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        
        CustomerControl ctc = new CustomerControl(cp);
        CustomerObject cto = new CustomerObject();
        switch(command){
            case "addCustomer":
                cto.setCustomer_fullname(customer_name);
                cto.setCustomer_account(customer_account);
                cto.setCustomer_mobile(customer_mobile);
                cto.setCustomer_address(customer_address);
                cto.setCustomer_email(customer_email);
                cto.setCustomer_password(customer_password);
                ctc.addCustomer(cto);
                
                response.sendRedirect("login.jsp");
                break;
        }
    }
}
