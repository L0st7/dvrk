/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.AddProductObject;
import object.CartObject;
import object.OrderDetailObject;
import object.OrderObject;
import object.ProductObject;
import order.OrderControl;
import orderdetail.OrderDetailControl;
import product.ProductControl;

/**
 *
 * @author nguye
 */
public class PayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        String customer_name = request.getParameter("customer_name");
        String customer_email = request.getParameter("customer_email");
        String customer_phone = request.getParameter("customer_phone");
        String customer_address = request.getParameter("customer_address");
        String payment = request.getParameter("payment");
        
         HttpSession session = request.getSession();
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();
        
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        
        OrderControl oc = new OrderControl(cp);
        OrderObject oo = new OrderObject();
        CartObject co = (CartObject) session.getAttribute("CART");
        ProductControl pc = new ProductControl(cp);
        OrderDetailControl odc = new OrderDetailControl(cp);
        OrderDetailObject odo = new OrderDetailObject();
        Date date = new Date();
        
        switch(command){
            case "thanhtoan":
                response.sendRedirect("view_pay.jsp");
                break;
            case "addOrder":
                oo.setOrder_fullname_customer(customer_name);
                oo.setOrder_email(customer_email);
                oo.setOrder_phone(customer_phone);
                oo.setOrder_address(customer_address);
                oo.setOrder_payments(payment);
                if(co.priceTotal()<1000000){
                oo.setOrder_price((int) co.priceTotal() + 40000);
                }
                if(co.priceTotal()>=1000000){
                    oo.setOrder_price((int)co.priceTotal());
                }
                oo.setOrder_status(false);
                oo.setOrder_date(date.toString());
                oc.addOrder(oo);
                for (int i = 0; i < co.getCountProduct(); i++) {
                    AddProductObject apo = co.getProduct(i);
                    ProductObject po = pc.getProductObject(apo.getProduct_name());
                    OrderObject oo1 = oc.getOrderObject(oo.getOrder_date());
                    odo.setOrderdetail_product_id(po.getProduct_id());
                    odo.setOrderdetail_quantity(apo.getProduct_count());
                    odo.setOrderdetail_price(apo.getProduct_count()*apo.getProduct_price());
                    odo.setOrderdetail_order_id(oo1.getOrder_id());
                    odc.addOrderDetail(odo);
                    
                }
                OrderObject oo1 = oc.getOrderObject(oo.getOrder_date());
                session.removeAttribute("CART");
                
                response.sendRedirect("view_order.jsp?order_id="+oo1.getOrder_id());
                break;
        }
    }

}
