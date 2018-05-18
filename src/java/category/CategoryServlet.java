/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category;

import cp.ConnectionPool;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        String category_id = request.getParameter("category_id");
        
        ServletContext application = getServletConfig().getServletContext();
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        
        CategoryObject co = new CategoryObject();
        CategoryControl cc = new CategoryControl(cp);
        switch(command){
            case "delete":
                co.setCategory_id(Integer.parseInt(category_id));
                cc.delCategory(co);
                
                response.sendRedirect("view_category_product.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        //Lấy thông tin trên giao diện
        //String command = request.getParameter("command");
        //String category_name = request.getParameter("category_name");
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();

        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
        CategoryControl cc = new CategoryControl(cp);
        CategoryObject co = new CategoryObject();

        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
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
                Hashtable<String, String> params = new Hashtable<>();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));
                    } else {
                        try {
                            String itemName = "ci" + item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path =" + fileName);
                            String realPath = getServletContext().getRealPath("/") + "images/upload/" + fileName;
                            System.out.println("Rpath =" + realPath);
                            File saveFile = new File(realPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //out.print("<img src='../images/upload/" + fileName + "'>");
                String url = "../images/upload/" + fileName;
                String command = (String) params.get("command");
                String category_name = (String) params.get("category_name");
                String category_id = (String) params.get("categoryID");
                int a = 0;
                switch (command) {
                    case "addCategory":
                        co.setCategory_name(category_name);
                        co.setCategory_image(url);
                        cc.addCategory(co);

                        response.sendRedirect("view_category_product.jsp");

                        break;
                    case "editCategory":
                        co.setCategory_id(Integer.parseInt(category_id));
                        co.setCategory_name(category_name);
                        co.setCategory_image(url);
                        cc.editCategory(co);

                        response.sendRedirect("view_category_product.jsp");
                        break;
                }
            }
        }
    }

}
