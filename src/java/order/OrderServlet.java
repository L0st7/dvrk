/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.AddProductObject;
import object.CartObject;
import object.OrderObject;

/**
 *
 * @author nguye
 */
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        CartObject co = (CartObject) session.getAttribute("CART");
        String i = request.getParameter("i");
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tham chiếu phiên làm việc
        switch (command) {
            case "delCart":
                ArrayList<AddProductObject> items = co.getCart();
                items.remove(Integer.parseInt(i));
                if (co.getCount() == 0) {
                    session.removeAttribute("CART");
                }
                response.sendRedirect("view_cart.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
        CartObject co = (CartObject) session.getAttribute("CART");
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tham chiếu phiên làm việc
        switch (command) {
            case "thanhtoan":
                response.sendRedirect("view_store.jsp");
                break;
            case "updateCart":
                String quantity = request.getParameter("soluong");
                ArrayList<AddProductObject> items = co.getCart();
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(quantity);
                }
                response.sendRedirect("view_cart.jsp");
                break;
        }
    }

}
