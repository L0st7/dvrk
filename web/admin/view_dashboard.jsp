<%-- 
    Document   : view_dashboard
    Created on : Apr 5, 2018, 4:47:49 PM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="object.OrderObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="order.OrderControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
        <script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            OrderControl oc = new OrderControl(cp);
            int total = oc.getOrderCount();
            ArrayList<OrderObject> items = oc.getOrderObject(null, (short) 1, (byte) total);
            DecimalFormat df = new DecimalFormat("###,###,###");
            if(total!=0){
        %>
        <div class="row">
            <div class="col-md-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Tổng doanh thu</div>
                    <div class="panel-body">
                        <%
                            int doanhthu = 0;
                            int price[] = new int[total];
                            for (OrderObject oo : items) {
                                doanhthu += oo.getOrder_price();
                                for (int i = 0; i < items.size(); i++) {
                                    OrderObject oo1 = items.get(i);
                                    price[i] = oo1.getOrder_price();
                                }

                            }
                            System.out.println(price.length);
                        %>  
                        <%=df.format(doanhthu)%>₫

                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Tổng đơn hàng</div>
                    <div class="panel-body">
                        <%=total%> đơn hàng
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Đơn hàng có giá trị lớn nhất</div>
                    <%
                        int max = price[0];
                        for (int i = 0; i < price.length; i++) {
                            if (max < price[i]) {
                                max = price[i];
                            } else {
                                max = max;
                            }
                        }
                        System.out.println(max);
                        OrderObject ooMax = oc.getOrderObjectPrice(max);
                    %>
                    <div class="panel-body">
                        Mã đơn hàng: #<%=ooMax.getOrder_id()%>
                        <br>
                        Tổng giá trị đơn hàng: <%=df.format(ooMax.getOrder_price())%>₫
                        <br>
                        Tên khách hàng: <%=ooMax.getOrder_fullname_customer()%>
                        <br>
                        Số điện thoại: <%=ooMax.getOrder_phone()%>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Đơn hàng có giá trị nhỏ nhất</div>
                    <%
                        int min = price[0];
                        for (int i = 0; i < price.length; i++) {
                            if (min > price[i]) {
                                min = price[i];
                            } else {
                                min = min;
                            }
                        }
                        System.out.println(min);
                        OrderObject ooMin = oc.getOrderObjectPrice(min);
                    %>
                    <div class="panel-body">
                        Mã đơn hàng: #<%=ooMin.getOrder_id()%>
                        <br>
                        Tổng giá trị đơn hàng: <%=df.format(ooMin.getOrder_price())%>₫
                        <br>
                        Tên khách hàng: <%=ooMin.getOrder_fullname_customer()%>
                        <br>
                        Số điện thoại: <%=ooMin.getOrder_phone()%>
                    </div>
                </div>
            </div>
            
        </div>
                    <%}else{%>
                    <h3 style="text-align: center">Không có dữ liệu để thống kê! </h3>
                    <%}%>

            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
