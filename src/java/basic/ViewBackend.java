/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import category.CategoryControl;
import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.CategoryObject;
import object.UserObject;
import user.UserControl;

/**
 *
 * @author nguye
 */
public class ViewBackend extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String command = request.getParameter("command");
            ConnectionPool cp = new ConnectionPoolImpl();
            
            switch(command){
                case "dashboard":
                    response.sendRedirect("view_dashboard.jsp");
                    break;
                case "user":
                    response.sendRedirect("view_user.jsp");
                    break;
                case "category":
                    response.sendRedirect("view_category_product.jsp");
                    break;
                case "products":
                    response.sendRedirect("view_product.jsp");
                    break;
                case "lookbook":
                    response.sendRedirect("view_category_lookbook.jsp");
                    break;
                case "news":
                    response.sendRedirect("view_news.jsp");
                    break;
                case "list":
                    response.sendRedirect("view_order.jsp");
                    break;
                case "add_user":
                    session.setAttribute("name", "ADD");
                    session.removeAttribute("USERID");
                    session.removeAttribute("USERNAME");
                    session.removeAttribute("USERPASS");
                    session.removeAttribute("FULLNAME");
                    session.removeAttribute("EMAIL");
                    
                    session.setAttribute("USERNAME", "");
                    session.setAttribute("USERPASS", "");
                    session.setAttribute("FULLNAME", "");
                    session.setAttribute("EMAIL", "");
                    response.sendRedirect("view_add_edit_user.jsp");
                    break;
                case "edit_user":
                        cp = new ConnectionPoolImpl();
                        UserControl uc = new UserControl(cp);
                        int uID = Integer.parseInt(request.getParameter("user_id"));
                        UserObject user = uc.getUserObject(uID);
                        session.setAttribute("USERID", user.getUser_id());
                        session.setAttribute("USERNAME", user.getUser_name());
                        session.setAttribute("USERPASS", user.getUser_pass());
                        session.setAttribute("FULLNAME", user.getUser_fullname());
                        session.setAttribute("EMAIL", user.getUser_email());
                        session.setAttribute("name", "EDIT");
                        response.sendRedirect("view_edit_user.jsp");
                    
                    break;
                case "add_category":
                    session.setAttribute("name", "ADD");
                    session.removeAttribute("CATEGORY_NAME");
                    session.removeAttribute("CATEGORY_IMG");
                    
                    session.setAttribute("CATEGORY_NAME", "");
                    
                    response.sendRedirect("view_add_edit_category_product.jsp");
                    break;
                case "edit_category":
                    session.setAttribute("name", "EDIT");
                    CategoryControl cc = new CategoryControl(cp);
                    CategoryObject co = cc.getCategoryObject(Integer.parseInt(request.getParameter("category_id")));
                    
                    session.setAttribute("CATEGORY_ID", co.getCategory_id());
                    session.setAttribute("CATEGORY_NAME", co.getCategory_name());
                    
                    response.sendRedirect("view_edit_category_product.jsp");
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
