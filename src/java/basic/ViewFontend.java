/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class ViewFontend extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String category_name= request.getParameter("category_name");
        switch(command){
            case "view":
                if(category_name.equals("JEAN")){
                    response.sendRedirect("view_jean.jsp");
                }
                if(category_name.equals("OUTERWEAR")){
                    response.sendRedirect("view_outerwear.jsp");
                }
                if(category_name.equals("PANTS")){
                    response.sendRedirect("view_pants.jsp");
                }
                if(category_name.equals("SALE")){
                    response.sendRedirect("view_sale.jsp");
                }
                if(category_name.equals("SHORTS")){
                    response.sendRedirect("view_short.jsp");
                }
                if(category_name.equals("T-SHIRT")){
                    response.sendRedirect("view_t-shirt.jsp");
                }
                if(category_name.equals("TOPWEAR")){
                    response.sendRedirect("view_topwear.jsp");
                }
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
