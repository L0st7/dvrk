/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import cp.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.AddProductObject;
import object.CartObject;
import object.ProductObject;
import product.ProductControl;

/**
 *
 * @author nguye
 */
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession();
        String product_id = request.getParameter("product_id");
        String command = request.getParameter("command");
        ProductControl pc = new ProductControl(cp);
        ProductObject po = pc.getProductObject(Integer.parseInt(product_id));
        AddProductObject apo = new AddProductObject();
        CartObject co = (CartObject) session.getAttribute("PRODUCTRELOAD");

        if (co == null||co.getCountProduct()==6) {
            co = new CartObject();
        }
        apo.setProduct_count(po.getProduct_id());
        apo.setProduct_img(po.getProduct_image());
        apo.setProduct_name(po.getProduct_name());
        apo.setProduct_price(po.getProduct_price());
        apo.setProduct_size(po.getProduct_size());
        co.addCartProduct(apo);

        session.setAttribute("PRODUCTRELOAD", co);

        response.sendRedirect("view_product_details.jsp?product_id=" + product_id);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String productID = request.getParameter("productID");
        String product_name = request.getParameter("product_name");
        String counts = request.getParameter("count");
        String size = request.getParameter("size");

        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();
        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession();
        ProductControl pc = new ProductControl(cp);
        ProductObject po = pc.getProductObject(Integer.parseInt(productID));
        AddProductObject apo = new AddProductObject();
        CartObject co = (CartObject) session.getAttribute("CART");
        switch (command) {
            case "addCart":

                int count = Integer.parseInt(counts);
                if (co == null) {
                    co = new CartObject();
                }
                if (count > 0) {
                    int price = po.getProduct_price();
                    apo.setProduct_name(product_name);
                    apo.setProduct_price(price);
                    apo.setProduct_count(count);
                    apo.setProduct_img(po.getProduct_image());
                    apo.setProduct_size(po.getProduct_size());
                    co.addCartProduct(apo);
                    
                    System.out.println(co.getCountProduct());
                    session.setAttribute("CART", co);
                    session.setAttribute("DISPLAY", "block");
                }

                response.sendRedirect("view_product_details.jsp?product_id=" + productID);
                break;
        }

    }
}
