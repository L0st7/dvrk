<%-- 
    Document   : view_cart_emtry
    Created on : Apr 27, 2018, 12:52:25 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng của bạn - DVRK CLOTHING</title>
    </head>
    <body>
        <jsp:include page="header_menu.jsp"></jsp:include>
        <jsp:include page="left_menu.jsp"></jsp:include>

        <div id="ContentOuter" style="margin-bottom: 50px;">

            <section id="Cart">
                <h3 class="section_title">Giỏ hàng</h3>
                <p>Giỏ hàng của bạn đang trống.</p>
                <p><strong><a href="view_store.jsp">Tiếp tục mua sắm &raquo;</a></strong></p>
            </section>


        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
