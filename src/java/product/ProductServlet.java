/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import category.CategoryControl;
import cp.ConnectionPool;
import cp.ConnectionPoolImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
import object.ProductObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author nguye
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();

        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");

        ProductControl pc = new ProductControl(cp);
        ProductObject po = new ProductObject();

        String command = request.getParameter("command");
        String product_id = request.getParameter("product_id");
        switch (command) {
            case "add_product":
                session.setAttribute("NAME", "ADD");
                session.setAttribute("PRODUCT_NAME", "");
                session.setAttribute("PRODUCT_PRICE", "");
                session.setAttribute("PRODUCT_TOTAL", "");
                session.setAttribute("PRODUCT_INTRO", "");

                response.sendRedirect("view_add_edit_product.jsp");
                break;
            case "edit_product":
                ProductObject pob = pc.getProductObject(Integer.parseInt(product_id));
                session.setAttribute("NAME", "EDIT");
                session.setAttribute("PRODUCTID", product_id);
                session.setAttribute("PRODUCT_NAME", pob.getProduct_name());
                session.setAttribute("PRODUCT_PRICE", pob.getProduct_price());
                session.setAttribute("PRODUCT_TOTAL", pob.getProduct_total());
                session.setAttribute("PRODUCT_INTRO", pob.getProduct_intro());
                session.setAttribute("PRODUCT_SIZE", pob.getProduct_size());
                session.setAttribute("PRODUCT_CATEGORY_ID", pob.getProduct_category_id());
                response.sendRedirect("view_edit_product.jsp");
                break;
            case "delete_product":
                po.setProduct_id(Integer.parseInt(product_id));
                pc.delProduct(po);
                response.sendRedirect("view_product.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("search");
        //Tham chiếu phiên làm việc
        HttpSession session = request.getSession();

        //Tham chiếu ngữ cảnh ứng dụng
        ServletContext application = getServletConfig().getServletContext();

        //Tìm bộ quản lí kết nối trong ứng dụng
        ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");

        ProductControl pc = new ProductControl(cp);
        ProductObject po = new ProductObject();
        CategoryControl cc = new CategoryControl(cp);
        CategoryObject co = new CategoryObject();

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
                ArrayList<String> listAnh = new ArrayList<>();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("path =" + fileName);
                            String realPath = getServletContext().getRealPath("/") + "images/upload/" + fileName;
                            System.out.println("Rpath =" + realPath);
                            File saveFile = new File(realPath);
                            item.write(saveFile);
                            listAnh.add("../images/upload/" + fileName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //out.print("<img src='../images/upload/" + fileName + "'>");
                String url1 = null;
                String url2 = null;
                String url3 = null;
                String url4 = null;
                String url5 = null;
                String url = "../images/upload/" + fileName;
                System.out.println(listAnh.indexOf("../images/upload/"+fileName));
                
                    for (int i = 0; i < listAnh.size(); i++) {
                        System.out.println(listAnh.get(i));
                        if (i == 0) {
                            url1 = listAnh.get(i);
                        }
                        if (i == 1) {
                            url2 = listAnh.get(i);
                        }
                        if (i == 2) {
                            url3 = listAnh.get(i);
                        }
                        if (i == 3) {
                            url4 = listAnh.get(i);
                        }
                        if (i == 4) {
                            url5 = listAnh.get(i);
                        }
                    }

                
                System.out.println(url);
                String command = (String) params.get("command");
                String product_name = (String) params.get("product_name");
                String product_category = (String) params.get("product_category");
                String product_price = (String) params.get("product_price");
                String product_total = (String) params.get("product_total");
                String product_size = (String) params.get("product_size");
                String product_intro = (String) params.get("product_intro");
                String product_id = (String) params.get("productID");
                switch (command) {
                    case "addProduct":
                        co = cc.getCategoryObject(product_category);
                        po.setProduct_name(product_name);
                        po.setProduct_image(url1);
                        po.setProduct_image2(url2);
                        po.setProduct_image3(url3);
                        po.setProduct_image4(url4);
                        po.setProduct_image5(url5);
                        po.setProduct_price(Integer.parseInt(product_price));
                        po.setProduct_total(Integer.parseInt(product_total));
                        po.setProduct_size(product_size);
                        po.setProduct_intro(product_intro);
                        po.setProduct_category_id(co.getCategory_id());
                        pc.addProduct(po);

                        response.sendRedirect("view_product.jsp");
                        break;
                    case "editProduct":
                        co = cc.getCategoryObject(product_category);
                        po.setProduct_id(Integer.parseInt(product_id));
                        po.setProduct_name(product_name);
                        po.setProduct_image(url1);
                        po.setProduct_image2(url2);
                        po.setProduct_image3(url3);
                        po.setProduct_image4(url4);
                        po.setProduct_image5(url5);
                        po.setProduct_price(Integer.parseInt(product_price));
                        po.setProduct_total(Integer.parseInt(product_total));
                        po.setProduct_size(product_size);
                        po.setProduct_intro(product_intro);
                        po.setProduct_category_id(co.getCategory_id());
                        pc.editProduct(po);

                        response.sendRedirect("view_product.jsp");
                        break;
                }
            }
        }
    }

}
