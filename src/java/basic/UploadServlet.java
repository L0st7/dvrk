/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import category.CategoryControl;
import cp.ConnectionPool;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.CategoryObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author nguye
 */
public class UploadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                String fileName = null;
                ArrayList<String> anh = new ArrayList<>();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    try {
                        String itemName = item.getName();
                        fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        System.out.println("path =" + fileName);
                        String realPath = getServletContext().getRealPath("/") + "images/upload/" + fileName;
                        System.out.println("Rpath =" + realPath);
                        File saveFile = new File(realPath);
                        item.write(saveFile);
                        anh.add(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String anh1 = null;
                String anh2 = null;
                String anh3 = null;
                String anh4 = null;
                String anh5 = null;
                for(int i=0;i<anh.size();i++){
                    if(i==0){
                        anh1 = "../images/upload/" + fileName;
                    }
                    if(i==1&&!anh.get(i).equals(fileName)){
                        anh2 = anh.get(i);
                    }
                    if(i==2&&!anh.get(i).equals(fileName)){
                        anh3 = "../images/upload/" + anh.get(i);
                    }
                    if(i==3&&!anh.get(i).equals(fileName)){
                        anh4 = "../images/upload/" + anh.get(i);
                    }
                    if(i==4&&!anh.get(i).equals(fileName)){
                        anh5 = "../images/upload/" + anh.get(i);
                    }
                }
                
                System.out.println(anh1);
                System.out.println(anh2);
                System.out.println(anh3);
                System.out.println(anh4);
                System.out.println(anh5);
                
                out.print("<img src='../images/upload/" + anh2 + "'>");

                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
