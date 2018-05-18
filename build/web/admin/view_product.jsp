<%-- 
    Document   : view_product
    Created on : Apr 5, 2018, 4:58:05 PM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="object.CategoryObject"%>
<%@page import="category.CategoryControl"%>
<%@page import="object.ProductObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.ProductControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            ProductControl pc = new ProductControl(cp);
            CategoryControl cc = new CategoryControl(cp);
            int stt =1;
            int pages =1;
            int total = pc.getProductCount();
            DecimalFormat df = new DecimalFormat("###,###,###");
            if(request.getParameter("pages")!=null){
                pages = Integer.parseInt(request.getParameter("pages"));
            }
            ArrayList<ProductObject> items = pc.getProductObject(null, (short)pages, (byte)10);
        %>
        <div class="container">
            <div class="row col-md-10 col-md-offset-1" style="">
                <a href="ProductServlet?command=add_product">ADD</a>
                <div class="panel panel-warning">
                    <div class="panel-heading">Products</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th style="width: 100px;">STT</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Hình ảnh</th>
                                    <th>Giá</th>
                                    <th>Số lượng sản phẩm</th>
                                    <th>Mô tả</th>
                                    <th>Size</th>
                                    <th>Danh mục</th>
                                    <th>Mã sản phẩm</th>
                                    <th style="width: 100px;"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(ProductObject po : items){
                                %>
                                <tr>
                                    <td><%=stt++%></td>
                                    <td style="text-transform: uppercase"><%=po.getProduct_name()%></td>
                                    <td>
                                        <img src="<%=po.getProduct_image()%>" width="50" height="50">
                                    </td>
                                    <td><%=df.format(po.getProduct_price())%></td>
                                    <td><%=po.getProduct_total()%></td>
                                    <td><%=po.getProduct_intro()%></td>
                                    <td><%=po.getProduct_size()%></td>
                                    <%
                                        CategoryObject co = cc.getCategoryObject(po.getProduct_category_id());
                                    %>
                                    <td><%=co.getCategory_name()%></td>
                                    <td><%=po.getProduct_id()%></td>
                                    <td><a href="ProductServlet?command=edit_product&&product_id=<%=po.getProduct_id()%>">Edit</a>
                                        <a onclick="return window.confirm('Are you sure?');" href="ProductServlet?command=delete_product&&product_id=<%=po.getProduct_id()%>">Delete</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <ul class="pagination">
                            <%
                                int back = 0;
                                if(pages == 0||pages == 1){
                                    back =1;
                                }else{
                                    back = pages - 1;
                                }
                            %>
                            <li><a href="view_product.jsp?pages=<%=back%>">&laquo;</a></li>
                            <%
                                int loop = 0, num =0;
                                if((total/10)%2 == 0){
                                    num = total/10;
                                }else{
                                    num = (total+1)/10;
                                }
                                //Nếu total lẻ thêm 1
                                if(total % 2 != 0){
                                    loop = (total/10)+1;
                                }else{
                                    //Nếu total chẵn nhỏ hơn fullpage và #fullpage thì thêm 1
                                    if(total < (num*10)+10 && total != num*10){
                                        loop = (total/10)+1;
                                    }else{
                                        loop = (total/10);
                                    }
                                }
                                for(int i=1;i<=loop;i++){
                                    if(pages == i){
                            %>
                            <li><a href="view_product.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}else{%>
                            <li><a href="view_product.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}%>
                            <%}%>
                            <%
                                int next = 0;
                                //Nếu total lẻ
                                if(total %10 !=0){
                                    if(pages == (total /10)+1){
                                        next = pages;
                                    }else{
                                        next = pages + 1;
                                    }
                                }else{
                                    if(total < (num * 10)+10 && total != num * 10){
                                        if(pages == (total / 10)+1){
                                            next = pages;
                                        }else{
                                            next = pages +1;
                                        }
                                    }else{
                                        if(pages == (total/10)){
                                            next = pages;
                                        }else{
                                            next = pages + 1;
                                        }
                                    }
                                }
                            %>
                            <li><a href="view_product.jsp?pages=<%=next%>">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
