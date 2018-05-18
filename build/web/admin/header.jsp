<%-- 
    Document   : header
    Created on : Apr 5, 2018, 4:09:27 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <%
                                if(session.getAttribute("UserName")!=null){
                            %>
                            <a class="navbar-brand" href="#">Xin chào! <%=session.getAttribute("UserName")%></a>
                            <%}%>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse navbar-ex1-collapse">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="UserLogin?command=logout">Logout</a></li>
                                <li><a href="ViewBackend?command=dashboard">Dashboard</a></li>
                                <li><a href="ViewBackend?command=user">User</a></li>
                                <li><a href="ViewBackend?command=category">Category</a></li>
                                <li><a href="ViewBackend?command=products">Products</a></li>
                                <li><a href="ViewBackend?command=lookbook">LookBook</a></li>
                                <li><a href="ViewBackend?command=news">News</a></li>
                                <li><a href="ViewBackend?command=list">List order</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </nav>
            </div>
        </div>
    </body>
</html>
