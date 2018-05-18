<%-- 
    Document   : view_order
    Created on : Apr 27, 2018, 10:56:25 PM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="product.ProductControl"%>
<%@page import="object.ProductObject"%>
<%@page import="orderdetail.OrderDetailControl"%>
<%@page import="object.OrderDetailObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.OrderObject"%>
<%@page import="order.OrderControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        session.setAttribute("DISPLAY", "none");
        String order_id = request.getParameter("order_id");
        ConnectionPool cp = new ConnectionPoolImpl();
        OrderControl oc = new OrderControl(cp);
        OrderDetailControl odc = new OrderDetailControl(cp);
  
        OrderObject oo = oc.getOrderObject(Integer.parseInt(order_id));
        int total = odc.getOrderDetailCount();
        ArrayList<OrderDetailObject> items = odc.getOrderDetailObject(null, (short)1, (byte)total);
        DecimalFormat df = new DecimalFormat("###,###,###");
        int price = 0;
    %>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="theme.hstatic.net/1000178923/1000285707/14/favicon.png?v=19" type="image/png">
        <title>
            DVRK CLOTHING - Đơn hàng #<%=oo.getOrder_id()%>
        </title>

        <link href="css/checkouts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/check_out.css" rel="stylesheet" type="text/css" media="all">
        <script src="css/jquery_002.js" type="text/javascript"></script>
        <script src="css/jquery.js" type="text/javascript"></script>

    </head>
    <body>
        <div class="content">

            <div class="wrap">
                <div class="sidebar">
                    <div class="sidebar-content">
                        <div class="order-summary order-summary-is-collapsed">
                            <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                            <div class="order-summary-sections">
                                <div class="order-summary-section order-summary-section-product-list" data-order-summary-section="line-items">
                                    <table class="product-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Hình ảnh</span></th>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Số lượng</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <%
                                            for(OrderDetailObject odo:items){
                                                if(odo.getOrderdetail_order_id()==Integer.parseInt(order_id)){
                                                    ProductControl pc = new ProductControl(cp);
                                                    ProductObject po = pc.getProductObject(odo.getOrderdetail_product_id());
                                                    price += po.getProduct_price()*odo.getOrderdetail_quantity();
                                        %>
                                        <tbody>

                                            <tr class="product" data-product-id="1012157224" data-variant-id="1023372814">
                                                <td class="product-image">
                                                    <div class="product-thumbnail">
                                                        <div class="product-thumbnail-wrapper">
                                                            <img class="product-thumbnail-image" alt="KILL FASTER TEE" src="<%=po.getProduct_image()%>">
                                                        </div>
                                                            <span class="product-thumbnail-quantity" aria-hidden="true"><%=odo.getOrderdetail_quantity()%></span>
                                                    </div>
                                                </td>
                                                <td class="product-description">
                                                    <span class="product-description-name order-summary-emphasis" style="text-transform: uppercase;"><%=po.getProduct_name()%></span>

                                                    <span class="product-description-variant order-summary-small-text" style="text-transform: uppercase;">
                                                        <%=po.getProduct_name()%> / <%=po.getProduct_size()%>
                                                    </span>

                                                </td>
                                                <td class="product-quantity visually-hidden"><%=odo.getOrderdetail_quantity()%></td>
                                                <td class="product-price">
                                                    <span class="order-summary-emphasis"><%=df.format(po.getProduct_price())%>₫</span>
                                                </td>
                                            </tr>

                                        </tbody>
                                        <%}%>
                                        <%}%>
                                    </table>
                                </div>

                                <div class="order-summary-section order-summary-section-total-lines" data-order-summary-section="payment-lines">
                                    <table class="total-line-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="total-line total-line-subtotal">
                                                <td class="total-line-name">Tạm tính</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-subtotal-price-target="69000000">
                                                        <%=df.format(price)%>₫
                                                    </span>
                                                </td>
                                            </tr>
                                            <%
                                                if(price<1000000){
                                            %>
                                            <tr class="total-line total-line-shipping">
                                                <td class="total-line-name">Phí vận chuyển</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-total-shipping-target="4000000">



                                                        40,000₫



                                                    </span>
                                                </td>
                                            </tr>
                                        </tbody>
                                        <tfoot class="total-line-table-footer">
                                            <tr class="total-line">
                                                <td class="total-line-name payment-due-label">
                                                    <span class="payment-due-label-total">Tổng cộng</span>
                                                </td>
                                                <td class="total-line-name payment-due">
                                                    <span class="payment-due-currency">VND</span>
                                                    <span class="payment-due-price" data-checkout-payment-due-target="73000000">
                                                        <%=df.format(price+40000)%>₫
                                                    </span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                        <%}else{%>
                                        <tr class="total-line total-line-shipping">
                                                <td class="total-line-name">Phí vận chuyển</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-total-shipping-target="4000000">



                                                        Miễn phí



                                                    </span>
                                                </td>
                                            </tr>
                                        </tbody>
                                        <tfoot class="total-line-table-footer">
                                            <tr class="total-line">
                                                <td class="total-line-name payment-due-label">
                                                    <span class="payment-due-label-total">Tổng cộng</span>
                                                </td>
                                                <td class="total-line-name payment-due">
                                                    <span class="payment-due-currency">VND</span>
                                                    <span class="payment-due-price" data-checkout-payment-due-target="73000000">
                                                        <%=df.format(price)%>₫
                                                    </span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                        <%}%>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-header">
                        <a href="view_home_page.jsp" class="logo">
                            <h1 class="logo-text">DVRK CLOTHING</h1>
                        </a>

                    </div>
                    <div class="main-content">


                        <div>
                            <div class="section">
                                <div class="section-header os-header">

                               

                                    <div class="os-header-heading">
                                        <h2 class="os-header-title">

                                            Đặt hàng thành công

                                        </h2>
                                        <span class="os-order-number">
                                            Mã đơn hàng #<%=oo.getOrder_id()%>
                                        </span>

                                        <span class="os-description">
                                            Cám ơn bạn đã mua hàng!
                                        </span>

                                    </div>
                                </div>
                            </div>

                            <div class="section thank-you-checkout-info">
                                <div class="section-content">
                                    <div class="content-box">
                                        <div class="content-box-row content-box-row-padding content-box-row-no-border">
                                            <h2>Thông tin đơn hàng</h2>
                                        </div>
                                        <div class="content-box-row content-box-row-padding">
                                            <div class="section-content">
                                                <div class="section-content-column">

                                                    <h3>Thông tin giao hàng</h3>
                                                    <p>

                                                        Tên khách hàng:
                                                        <br>


                                                        <%=oo.getOrder_fullname_customer()%>
                                                        <br>


                                                        Địa chỉ:
                                                        <br>


                                                        <%=oo.getOrder_address()%>
                                                        <br>


                                                        Email:
                                                        <br>


                                                        <%=oo.getOrder_email()%>
                                                        <br>
                                                        
                                                        Số điện thoại:
                                                        <br>


                                                        <%=oo.getOrder_phone()%>
                                                        <br>

                                                    </p>



                                                    <h3>Phương thức thanh toán</h3>
                                                    <p>

                                                        <%=oo.getOrder_payments()%>

                                                    </p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="step-footer">

                                <a href="view_store.jsp" class="step-footer-continue-btn btn">
                                    <span class="btn-content">Tiếp tục mua hàng</span>
                                </a>

                                <p class="step-footer-info">
                                    <i class="icon icon-os-question"></i>
                                    <span>


                                        Cần hỗ trợ? <a href="mailto:nguyenhiep96vn@gmail.com">Liên hệ chúng tôi</a>
                                    </span>
                                </p>
                            </div>
                        </div>


                    </div>
                    <div class="main-footer">

                    </div>
                </div>
            </div>

        </div>




    </body>
</html>
