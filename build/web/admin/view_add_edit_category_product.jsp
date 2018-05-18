<%-- 
    Document   : view_add_edit_category_product
    Created on : Apr 5, 2018, 5:10:36 PM
    Author     : nguye
--%>

<%@page import="object.CategoryObject"%>
<%@page import="category.CategoryControl"%>
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
            CategoryControl cc = new CategoryControl(cp);
            CategoryObject co = new CategoryObject();
        %>
        <div class="container">
            <div class="row col-md-5 col-md-offset-3">
                <div class="panel panel-warning">
                    <div class="panel-heading"><%=session.getAttribute("name")%></div>
                    <div class="panel-body">
                        <form action="CategoryServlet" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="categoryID" value="<%=session.getAttribute("CATEGORY_ID")%>"/>
                            <div class="form-group">
                                <input class="form-control" type="text" name ="category_name" placeholder="Name" value="<%=session.getAttribute("CATEGORY_NAME")%>"/>
                            </div>
                            <div class="form-group">
                                <label style="width:120px;" for="" class="col-sm-2 control-label">Ảnh</label>
                                <input type="file" name="image" class="form-control" id="image">
                            </div>

                            <input type="hidden" name="command" value="addCategory"/>
                            <input type="submit" value="Upload" class="btn btn-primary"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
