<%-- 
    Document   : view_search
    Created on : May 7, 2018, 2:30:19 PM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
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
        <title>Tìm kiếm &ndash; DVRK CLOTHING</title>
    </head>
    <body>
        <%
            session.setAttribute("DISPLAY", "none");
            ConnectionPool cp = new ConnectionPoolImpl();
            ProductControl pc = new ProductControl(cp);
            String productName = request.getParameter("product_name");
            ArrayList<ProductObject> items = pc.searchProductObject(productName);
            DecimalFormat df = new DecimalFormat("###,###,###");
        %>
        <jsp:include page="header_menu.jsp"></jsp:include>

        <jsp:include page="left_menu.jsp"></jsp:include>

            <div id="Wrapper">
                <div id="WrapInner">            
                    <div id="ContentOuter">

                        <section id="Collections">
                            <h3 class="section_title">Tất cả sản phẩm</h3>
                            <div class="hr"></div>
                            <ul class="js-collection-loop clearfix col fourths">
                            <%
                                    for (ProductObject po : items) {

                            %>
                            <li class="box-products">
                                <a href="CartServlet?product_id=<%=po.getProduct_id()%>" title="<%=po.getProduct_name()%>">
                                    <div class="img_outer">
                                        <div class="table_cell">
                                            <div class="sale_outer">
                                                <img class="lazy" src="<%=po.getProduct_image()%>" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="details_outer" style="display: none; opacity: 1;">
                                        <div class="details_inner">
                                            <div class="table_cell">
                                                <span class="product_title" style="text-transform: uppercase;"><%=po.getProduct_name()%></span><br>
                                                <span class="product_price">
                                                    <span class="price_lg">
                                                        <%
                                                            if (po.getProduct_total() == 0) {
                                                        %> 
                                                        <span class="money">
                                                            HẾT HÀNG
                                                        </span>
                                                        <%} else {%>

                                                        <span class="money">
                                                            <%=df.format(po.getProduct_price())%>₫
                                                        </span>
                                                        <%}%>
                                                    </span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <%}%>
                        </ul>
                        <div id="loadingbar"></div>
                    </section>

                </div>
            </div>
        </div>


        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
