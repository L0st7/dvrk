<%-- 
    Document   : view_edit_user
    Created on : Apr 30, 2018, 9:43:09 AM
    Author     : nguye
--%>

<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page import="user.UserControl"%>
<%@page import="object.UserObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <div class="container">
            <div class="row col-md-6 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading"><%=session.getAttribute("name")%></div>
                
                    <div class="panel-body">
                        <form action="UserServlet" method="POST" role="form">

                            <div class="form-group">
                                <input type="hidden" class="form-control" id="" placeholder="Username" name="userid" value="<%=session.getAttribute("USERID")%>" >
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="" placeholder="Username" name="username" value="<%=session.getAttribute("USERNAME")%>" >
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="" placeholder="Password" name="password" value="<%=session.getAttribute("USERPASS")%>">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="" placeholder="Fullname" name="fullname"  value="<%=session.getAttribute("FULLNAME")%>" >
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" id="" placeholder="Email" name="email"  value="<%=session.getAttribute("EMAIL")%>" >
                            </div>

                            <input type="hidden" name="command" value="edit"/>
                            <input type="submit" class="btn btn-primary" value="Submit"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
                     
    </body>
</html>
