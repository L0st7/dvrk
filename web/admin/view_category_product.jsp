<%-- 
    Document   : view_category_product
    Created on : Apr 5, 2018, 5:08:43 PM
    Author     : nguye
--%>

<%@page import="category.CategoryControl"%>
<%@page import="object.CategoryObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            CategoryControl uc = new CategoryControl(cp);
            int stt = 1;
            int pages = 1;
            int total = uc.getCategoryCount();
            if(request.getParameter("pages")!=null){
                pages = Integer.parseInt(request.getParameter("pages"));
            }
            ArrayList<CategoryObject> items = uc.getCategoryObject(null, (short)pages, (byte)10);
        %>
        <div class="container">
            <div class="row col-md-6 col-md-offset-3">
                <a href="ViewBackend?command=add_category" class="btn btn-warning" style="margin-bottom: 10px;">ADD</a>
                <div class="panel panel-success">
                    <div class="panel-heading">Category Product</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th style="width: 50px;">STT</th>
                                    <th>Tên danh mục</th>
                                    <th>Category ID</th>
                                    <th style="width: 100px;"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(CategoryObject item : items){
                                %>   
                                <tr>
                                    <td><%=stt++%></td>
                                    <td><%=item.getCategory_name()%></td>
                                    <td><%=item.getCategory_id()%></td>
                                    <td>
                                        <a href="ViewBackend?command=edit_category&&category_id=<%=item.getCategory_id()%>">Edit</a>
                                        <a onclick="return window.confirm('Are you sure?');" href="CategoryServlet?command=delete&&category_id=<%=item.getCategory_id()%>">Delete</a>
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
                            <li><a href="view_category_product.jsp?pages=<%=back%>">&laquo;</a></li>
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
                            <li><a href="view_category_product.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}else{%>
                            <li><a href="view_category_product.jsp?pages=<%=i%>"><%=i%></a></li>
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
                            <li><a href="view_category_product.jsp?pages=<%=next%>">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
