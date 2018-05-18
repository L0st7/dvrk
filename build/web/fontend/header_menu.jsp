<%-- 
    Document   : header_menu
    Created on : Apr 16, 2018, 11:15:34 AM
    Author     : nguye
--%>

<%@page import="object.AddProductObject"%>
<%@page import="object.ProductObject"%>
<%@page import="product.ProductControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page import="object.CartObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="theme.hstatic.net/1000178923/1000285707/14/faviconce31.png?v=19" type="image/png" />
        <meta charset="utf-8" />

        <title>
            header menu
        </title>

        <link rel="stylesheet" type="text/css" href="css/phantrang.css">

        <script src='theme.hstatic.net/1000178923/1000285707/14/plugin-hrvce31.js?v=19' type='text/javascript'></script>


        <script src='theme.hstatic.net/1000178923/1000285707/14/jquery-ui.mince31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/ss-standardce31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/ss-socialce31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/jquery.magnific-popup.mince31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/initce31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/slick.mince31.js?v=19' type='text/javascript'></script>

        <!-- Latest compiled and minified JavaScript -->
        <link href='hstatic.net/0/0/global/design/plugins/font-awesome/4.6.3/css/font-awesome.min.css' rel='stylesheet' type='text/css'  media='all'  />
        <link href='theme.hstatic.net/1000178923/1000285707/14/stylesce31.css?v=19' rel='stylesheet' type='text/css'  media='all'  />

        <script src='theme.hstatic.net/1000178923/1000285707/14/jquery.tmpl.mince31.js?v=19' type='text/javascript'></script>
        <script src='theme.hstatic.net/1000178923/1000285707/14/jquery.productsce31.js?v=19' type='text/javascript'></script>
    </head>
    <body>
        <div id="headermenu" class="desktop-view">
            <div class="headermenu-inner">
                <ul class="hide-on-mobile headermenu-left">

                    <li>
                        <a href="view_store.jsp">Cửa hàng</a>
                    </li>

                    <li>
                        <a href="lookbook.jsp">Lookbook</a>
                    </li>

                </ul>
                <div id="header-logo">
                    <a href="view_home_page.jsp" id="headermenu-logo">
                        <img src="theme.hstatic.net/1000178923/1000285707/14/logoce31.png?v=19" alt="DVRK CLOTHING">
                    </a>
                </div>
                <div class="rightalign-header">
                    <div id="SiteSearch">
                        <form method="POST" action="SearchServlet">
                            <input type="hidden" name="command" value="search"/>
                            <span class="search_submit ss-icon searchdel">&#x1F50E;</span>
                            <input type="text" name="search" value="" placeholder=" Tìm kiếm" class="search_query" />
                            <button type="submit" class="submit ss-icon">&#x1F50E;</button>
                        </form>
                    </div>
                    <%
                        CartObject co = (CartObject) session.getAttribute("CART");
                        int count = 0;
                        DecimalFormat df = new DecimalFormat("###,###,###");
                        if (co != null) {
                    %>

                    <div id="CartCustomer">

                        <div class="cart_outer">
                            <nobr>
                                <a href="view_cart.jsp" id="CartButton">
                                    <span class="icon-bag">
                                        <img src="theme.hstatic.net/1000178923/1000285707/14/icon-bagce31.png?v=19">
                                    </span>
                                    (<span class="cart-count"><%=co.getCount()%></span>)</a>
                                <span class="mobile_separator"> &nbsp; | &nbsp; </span>
                            </nobr>
                            <div id="CartAddNotification" class="" style="display: <%=session.getAttribute("DISPLAY")%>;">
                                <div class="open-product">Bạn vừa thêm: 
                                    <%

                                        ArrayList<AddProductObject> items = co.getCart();
                                        for (AddProductObject apo : items) {
                                            System.out.println(apo.toString());
                                    %>
                                    <div class="open-product-inner clearfix" data-product-id="1025587187">
                                        <img src="<%=apo.getProduct_img()%>" alt="" width="50" height="50">
                                        <div class="product-info">
                                            <span class="product-title" style="text-transform: uppercase;"><%=apo.getProduct_name()%> - <%=apo.getProduct_size()%></span>
                                            <span class="product-quantity"><%=apo.getProduct_count()%></span> x
                                            <span class="money"><%=df.format(apo.getProduct_price())%>₫</span>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                                <span class="open-product-count">Tổng cộng: <%=co.getCount()%> Sản phẩm </span> /
                                <span class="open-product-cost">
                                    <span class="money">
                                        <span class="money">
                                            <span class="money"><%=df.format(co.priceTotal())%></span>

                                        </span>

                                    </span>

                                </span>
                                <a href="view_cart.jsp" class="view_cart_button btn">Tiếp tục vào giỏ hàng</a>
                            </div>
                        </div>

                    </div>

                    <%} else {%>
                    <div id="CartCustomer" style="z-index: 1; position: relative;">           
                        <div class="cart_outer">
                            <nobr>
                                <a href="view_cart_emtry.jsp" id="CartButton">
                                    <span class="icon-bag">
                                        <img src="theme.hstatic.net/1000178923/1000285707/14/icon-bagce31.png?v=19" />
                                    </span>
                                </a>
                                <span class="mobile_separator"> &nbsp; | &nbsp; </span>
                            </nobr>
                            <div id="CartAddNotification">
                                <div class="open-product">Giỏ hàng của bạn đang trống</div>
                                <span class="open-product-count"></span>
                                <span class="open-product-cost">

                                </span>
                                <a href="view_cart_emtry.jsp" class="view_cart_button btn">Tiếp tục vào giỏ hàng</a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <a class="mobile_menu icon-menu" href="javascript:void(0)">
                        <img src="theme.hstatic.net/1000178923/1000285707/14/icon-bagce31.png?v=19" />
                    </a>
                    <ul class="hide-on-mobile headermenu-right">										
                        <%
                            if (session.getAttribute("CustomerLogin") == null) {
                        %>
                        <li><a href="login.jsp" id="customer_login_link" title="Tài khoản">Đăng nhập</a></li>
                            <%} else {%>
                        <li><a href="CustomerLogin?command=logout" id="customer_login_link" title="Tài khoản">Hi, <%=session.getAttribute("CustomerAccount")%> !</a></li>
                        <%}%>
                        <li class="">
                            <a href="search.jsp">Tìm kiếm</a>
                        </li>

                        <li class="">
                            <a href="about-us.jsp">Giới thiệu</a>
                        </li>

                        <li class="">
                            <a href="term.jsp">Điều Khoản</a>
                        </li>

                        <li class="">
                            <a href="news.jsp">News</a>
                        </li>

                    </ul>

                    <ul class="hide-on-mobile headermenu-social">
                        <li>
                            <a class="ss-icon ss-social-regular" href="https://facebook.com/heythanghe" target="_blank">
                                <i class="fa fa-facebook"></i>
                            </a>
                        </li>
                        <li>
                            <a class="ss-icon ss-social-regular" href="https://twitter.com/heythanghe" target="_blank">
                                <i class="fa fa-twitter"></i>
                            </a>
                        </li>
                        <li>
                            <a class="ss-icon ss-social-regular" href="https://www.instagram.com/heythanghe/" target="_blank">
                                <i class="fa fa-instagram"></i>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </body>
</html>
