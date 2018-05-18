<%-- 
    Document   : left_menu
    Created on : Apr 16, 2018, 11:25:40 AM
    Author     : nguye
--%>

<%@page import="object.CategoryObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="category.CategoryControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>left menu</title>
    </head>
    <body>
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            CategoryControl cc = new CategoryControl(cp);
            int total = cc.getCategoryCount();
            int pages = 1;
            if(request.getParameter("pages")!=null){
                pages=Integer.parseInt(request.getParameter("pages"));
            }
            ArrayList<CategoryObject> items = cc.getCategoryObject(null, (short)pages, (byte)total);
        %>
        <section id="LeftMenu" class="desktop-view left-menu-desktop ">
            <div id="site-logo" class="hide-on-mobile">
                <a href="/" id="Logo">
                    <img src="theme.hstatic.net/1000178923/1000285707/14/logo.png?v=19" alt="DVRK CLOTHING">
                </a>
            </div>
            <nav id="MainNavOuter" class="clearfix">
                <ul id="MainNav">

                    <li class="">
                        <div class="accordion">
                            <div class="accord-header">
                                <a href="view_store.jsp">ALL</a>
                            </div>
                        </div>
                    </li>
                    <%
                        for(CategoryObject co : items){
                    %>
                    <li class="">
                        <div class="accordion">
                            <div class="accord-header">
                                <a href="ViewFontend?command=view&&category_name=<%=co.getCategory_name()%>"><%=co.getCategory_name()%></a>
                            </div>
                        </div>
                    </li>
                    <%}%>
                </ul>
                <div class="social_media">
                    <a class="ss-icon ss-social-regular" href="header" target="_blank">&#xF610;</a>
                    <a class="ss-icon ss-social-regular" href="header" target="_blank">&#xF611;</a>
                    <a class="ss-icon ss-social-regular" href="header" target="_blank">&#xF641;</a>
                </div>
            </nav>
        </section>
        <header id="Header">
                    </header>
    </body>
</html>
