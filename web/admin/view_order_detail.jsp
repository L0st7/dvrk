<%-- 
    Document   : view_order_detail
    Created on : Apr 6, 2018, 9:11:37 AM
    Author     : nguye
--%>

<%@page import="object.OrderObject"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="object.ProductObject"%>
<%@page import="product.ProductControl"%>
<%@page import="orderdetail.OrderDetailControl"%>
<%@page import="object.OrderDetailObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="order.OrderControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="backend/css/checkouts.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            String order_id = request.getParameter("order_id");
            ConnectionPool cp = new ConnectionPoolImpl();
            OrderDetailControl odc = new OrderDetailControl(cp);
            OrderControl oc = new OrderControl(cp);
            OrderObject oo = oc.getOrderObject(Integer.parseInt(order_id));
            int total = odc.getOrderDetailCount();
            ArrayList<OrderDetailObject> items = odc.getOrderDetailObject(null, (short)1, (byte)total);
            DecimalFormat df = new DecimalFormat("###,###,###");
        %>
        <div class="container">
            <div class="row col-md-10 col-md-offset-1">
                
                <div class="panel panel-success">
                    
                    <div class="panel-heading">Đơn hàng mã #<%=order_id%></div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>Mã sản phẩm</th>
                                    <th>Ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Size</th>
                                    <th>Đơn giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            <%
                                for(OrderDetailObject odo : items){
                                    if(odo.getOrderdetail_order_id()==Integer.parseInt(order_id)){
                                        ProductControl pc = new ProductControl(cp);
                                        ProductObject po = pc.getProductObject(odo.getOrderdetail_product_id());
                            %>
                            <tbody>

                                <tr>
                                    <td><%=po.getProduct_id()%></td>
                                    <td><img src="<%=po.getProduct_image()%>" style="width:100px;"></td>
                                    <td><%=po.getProduct_name()%></td>
                                    <td><%=po.getProduct_size()%></td>
                                    <td><%=df.format(po.getProduct_price())%>đ</td>
                                    <td><%=odo.getOrderdetail_quantity()%></td>
                                    <td><%=df.format(odo.getOrderdetail_price())%></td>
                                </tr>

                            </tbody>
                            <%}%>
                            <%}%>
                        </table>
                    </div>
                    <div class="total-line-table-footer" style="margin-bottom: 10px;float: right;">
                        <span class="payment-due-label-total" style="font-size: 20px;">Tổng cộng: </span>
                        <span class="payment-due-price" data-checkout-payment-due-target="73000000" style="font-size: 25px;">
                            <%=df.format(oo.getOrder_price())%>₫
                        </span>
                    </div>
                    <div style="clear: both;"></div>
                    <form style="float: right;" action="OrderDetailServlet" method="POST">
                        <input type="hidden" name="order_id" value="<%=order_id%>"/>
                        <%
                            if (!oo.isOrder_status()) {
                        %>
                        <div style="margin-top: 20px;">
                            <input type="hidden" name="command" value="status"/>
                            <button type="submit" class="btn btn-primary">Giao hàng</button>&nbsp;
                        </div>
                        <%} else {%>
                        <div style="margin-top: 20px;">
                            <input type="hidden" name="command" value="status"/>
                            <button type="submit" class="btn btn-primary" disabled="true">Đã giao hàng</button>&nbsp;
                        </div>
                        <%}%>
                    </form>

                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
