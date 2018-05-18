<%-- 
    Document   : view_cart
    Created on : Apr 27, 2018, 1:19:26 PM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="object.AddProductObject"%>
<%@page import="object.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng của bạn - DVRK CLOTHING</title>
    </head>
    <body>
        <%
            session.setAttribute("DISPLAY", "none");
        %>
        <jsp:include page="header_menu.jsp"></jsp:include>
        <jsp:include page="left_menu.jsp"></jsp:include>
        <%
            
            CartObject co = (CartObject) session.getAttribute("CART");
            DecimalFormat df = new DecimalFormat("###,###,###");
            if(co!=null){
        %>
        <div id="ContentOuter" style="margin-top: 27px; opacity: 1; margin-right: 20px;width: 85%;">

            <section id="Cart">
                <h3 class="section_title">Giỏ hàng</h3>
                <div class="hr"></div>
                <form action="OrderServlet" method="POST" id="CartForm" class="clearfix">
                    <div>
                        <table id="CartTable">
                            <thead>
                                <tr class="top_row">
                                    <th class="empty cart_img_main"></th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá tiền</th>
                                    <th>Số lượng</th>
                                    <th class="empty cart_remove">&nbsp;</th>
                                    <th class="cart_total">Tổng cộng</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                                int a=0;
                                for(int i=0;i<co.getCountProduct();i++){
                                    AddProductObject apo = co.getProduct(i);
                            %>
                                <tr class="item repairer-denim-vintage-black">
                                    <td class="cart_img_main">
                                        <a href="#">
                                            <img src="<%=apo.getProduct_img()%>" alt="" width="100" height="100">
                                        </a>
                                    </td>
                                    <td class="left" style="text-transform: uppercase;">
                                        <a href="#">
                                            <%=apo.getProduct_name()%> - <%=apo.getProduct_size()%>

                                        </a>
                                    </td>
                                    <td>
                                        <span class="money"><%=df.format(apo.getProduct_price())%>₫</span>
                                    </td>
                                    <td>
                                        <input class="text quantity" size="4" id="updates_1025587187" name="soluong" onfocus="this.select();" value="<%=apo.getProduct_count()%>" autocomplete="off" type="text">
                                    </td>
                                    <td class="cart_remove">
                                        <a href="OrderServlet?command=delCart&&i=<%=a++%>" class="remove_from_cart">Xóa</a>
                                    </td>
                                    <td class="cart_total">
                                        <span class="money"><%=df.format(apo.getProduct_price()*apo.getProduct_count())%>₫</span>
                                    </td>
                                </tr>
                            <%}%>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td class="empty cart_img_main"></td>
                                    <td colspan="2" class="empty">&nbsp;</td>
                                    <td>
                                        <input type="hidden" name="command" value="updateCart"/>
                                        <input class="btn update_cart" name="update" value="Cập nhật" type="submit">
                                    </td>
                                    <td colspan="2" class="empty cart_total">&nbsp;</td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </form>
                
                <form action="PayServlet" method="POST">
                    <div>
                        <div class="cart_info_container clearfix">
                            <h3>
                                <span>Tổng cộng:</span>
                                <span class="money"><%=df.format(co.priceTotal())%>₫</span>
                            </h3>
                            <div id="CheckoutNote">
                                <label for="note">Nếu bạn có bất kỳ hướng dẫn cho người bán, hãy để chúng ở đây:</label>
                                <br>
                                <textarea id="note" name="note"></textarea>
                            </div>
                            <div id="CheckoutProceed">
                                <input type="hidden" name="command" value="thanhtoan"/>
                                <input class="btn large_btn" id="Checkout" name="checkout" value="Tiến hành thanh toán" type="submit">
                            </div>
                        </div>
                    </div>
                </form>
            </section>


        </div>
        <%}else{%>
        <div id="ContentOuter" style="margin-bottom: 50px;">

            <section id="Cart">
                <h3 class="section_title">Giỏ hàng</h3>
                <p>Giỏ hàng của bạn đang trống.</p>
                <p><strong><a href="view_store.jsp">Tiếp tục mua sắm &raquo;</a></strong></p>
            </section>


        </div>
        <%}%>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
