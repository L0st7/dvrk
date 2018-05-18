<%-- 
    Document   : view_order
    Created on : Apr 6, 2018, 9:09:28 AM
    Author     : nguye
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
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
        <title>Order</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            OrderControl oc = new OrderControl(cp);
            int pages =1;
            int total = oc.getOrderCount();
            if(request.getParameter("pages") != null){
                pages = (int) Integer.parseInt(request.getParameter("pages"));
            }
            ArrayList<OrderObject> items = oc.getOrderObject(null, (short)pages, (byte)10);
            DecimalFormat df = new DecimalFormat("###,###,###");
        %>
        <div class="container">
            <div class="row col-md-10 col-md-offset-1">
                <div class="panel panel-success">
                    <div class="panel-heading">List order</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>Mã đơn hàng</th>
                                        <th>Tên khách hàng</th>
                                        <th>Email</th>
                                        <th>Địa chỉ</th>
                                        <th>Điện thoại</th>
                                        <th>Ngày mua</th>
                                        <th>Ngày giao hàng</th>
                                        <th>Tổng tiền</th>
                                        <th>Payment</th>
                                        <th>Trạng thái</th>
                                        <th>Quản lí</th>
                                    </tr>
                                </thead>
                                <%
                                    for(OrderObject oo : items){
                                %>
                                <tbody>

                                    
                                    <tr>
                                        <td>#<%=oo.getOrder_id()%></td>
                                        <td><%=oo.getOrder_fullname_customer()%></td>
                                        <td><%=oo.getOrder_email()%></td>
                                        <td><%=oo.getOrder_address()%></td>
                                        <td><%=oo.getOrder_phone()%></td>
                                        <td><%=oo.getOrder_date()%></td>
                                        <td><%=oo.getOrder_delivery_date()%></td>
                                        <td><%=df.format(oo.getOrder_price())%></td>
                                        <td>
                                            <%=oo.getOrder_payments()%>
                                        </td>
                                        <%
                                            if(!oo.isOrder_status()){
                                        %>
                                        <td>
                                            <button type="submit" disabled="true" style="background: #338dbc;color: #FFF;border: none;">Đang chờ xử lí</button>&nbsp;
                                        </td>
                                        <%}else{%>
                                        <td>
                                            <button type="submit" disabled="true" style="background: #737373;color: #FFF;border: none;">Đã giao hàng</button>&nbsp;
                                        </td>
                                        <%}%>
                                        <td>
                                            <a href="OrderDetailServlet?command=detailorder&&order_id=<%=oo.getOrder_id()%>" style="color:red;">Chi tiết</a><hr>
                                            &nbsp;
                                            <a onclick="return window.confirm('Are you sure?')" href="OrderDetailServlet?command=delorder&&order_id=<%=oo.getOrder_id()%>" style="color:red;">Xóa</a>
                                        </td>
                                    </tr>	
                                </tbody>
                                <%}%>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <ul class="pagination">
                            <%
                                int back = 0;
                                if(pages == 0||pages == 1){
                                    back =1;
                                }else{
                                    back = pages - 1;
                                }
                            %>
                            <li><a href="view_order.jsp?pages=<%=back%>">&laquo;</a></li>
                            <%
                                int loop = 0, num =0;
                                if((total/10)%2 == 0){
                                    num = total/10;
                                }else{
                                    num = (total+1)/10;
                                }
                                //Nếu total lẻ thêm 1
                                if(total % 2 != 0){
                                    loop = (total/10)+1;
                                }else{
                                    //Nếu total chẵn nhỏ hơn fullpage và #fullpage thì thêm 1
                                    if(total < (num*10)+10 && total != num*10){
                                        loop = (total/10)+1;
                                    }else{
                                        loop = (total/10);
                                    }
                                }
                                for(int i=1;i<=loop;i++){
                                    if(pages == i){
                            %>
                            <li><a href="view_order.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}else{%>
                            <li><a href="view_order.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}%>
                            <%}%>
                            <%
                                int next = 0;
                                //Nếu total lẻ
                                if(total %10 !=0){
                                    if(pages == (total /10)+1){
                                        next = pages;
                                    }else{
                                        next = pages + 1;
                                    }
                                }else{
                                    if(total < (num * 10)+10 && total != num * 10){
                                        if(pages == (total / 10)+1){
                                            next = pages;
                                        }else{
                                            next = pages +1;
                                        }
                                    }else{
                                        if(pages == (total/10)){
                                            next = pages;
                                        }else{
                                            next = pages + 1;
                                        }
                                    }
                                }
                            %>
                            <li><a href="view_order.jsp?pages=<%=next%>">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
