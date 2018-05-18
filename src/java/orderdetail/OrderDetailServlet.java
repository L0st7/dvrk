/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.OrderDetailObject;
import object.OrderObject;
import object.ProductObject;
import order.OrderControl;
import product.ProductControl;

/**
 *
 * @author nguye
 */
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String order_id = request.getParameter("order_id");
        ServletContext application = getServletConfig().getServletContext();
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        OrderControl oc = new OrderControl(cp);
        OrderObject oo = new OrderObject();
        switch(command){
            case "delorder":
                oo.setOrder_id(Integer.parseInt(order_id));
                oc.delOrder(oo);
                response.sendRedirect("view_order.jsp");
                break;
            case "detailorder":
                response.sendRedirect("view_order_detail.jsp?order_id="+order_id);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String order_id = request.getParameter("order_id");
        System.out.println(order_id);
        ServletContext application = getServletConfig().getServletContext();
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        OrderControl oc = new OrderControl(cp);
        OrderObject oo = new OrderObject();
        ProductControl pc = new ProductControl(cp);
        ProductObject po = new ProductObject();
        OrderDetailControl odc = new OrderDetailControl(cp);
        int total = odc.getOrderDetailCount();
        ArrayList<OrderDetailObject> items = odc.getOrderDetailObject(null, (short)1, (byte)total);
        
        Date date = new Date();
        switch (command) {
            case "status":
                oo.setOrder_id(Integer.parseInt(order_id));
                oo.setOrder_status(true);
                oo.setOrder_delivery_date(date.toString());
                oc.editOrder(oo);
                for (OrderDetailObject odo : items) {
                    if (odo.getOrderdetail_order_id() == Integer.parseInt(order_id)) {
                        ProductObject po1 = pc.getProductObject(odo.getOrderdetail_product_id());
                        po.setProduct_id(po1.getProduct_id());
                        po.setProduct_name(po1.getProduct_name());
                        po.setProduct_image(po1.getProduct_image());
                        po.setProduct_image2(po1.getProduct_image2());
                        po.setProduct_image3(po1.getProduct_image3());
                        po.setProduct_image4(po1.getProduct_image4());
                        po.setProduct_image5(po1.getProduct_image5());
                        po.setProduct_price(po1.getProduct_price());
                        po.setProduct_visited(po1.getProduct_visited());
                        po.setProduct_total(po1.getProduct_total() - odo.getOrderdetail_quantity());
                        po.setProduct_intro(po1.getProduct_intro());
                        po.setProduct_size(po1.getProduct_size());
                        po.setProduct_category_id(po1.getProduct_category_id());

                        pc.editProduct(po);
                    }
                }
                response.sendRedirect("view_order.jsp");
                break;
        }
    }

}
