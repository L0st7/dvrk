<%-- 
    Document   : view_user
    Created on : Apr 5, 2018, 4:52:00 PM
    Author     : nguye
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page import="user.UserImpl"%>
<%@page import="user.User"%>
<%@page import="object.UserObject"%>
<%@page import="user.UserControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <%
            ConnectionPool cp = new ConnectionPoolImpl();
            UserControl uc = new UserControl(cp);
            int stt = 1;
            int pages =1;
            int total = uc.getUserCount();
            if(request.getParameter("pages") != null){
                pages = (int) Integer.parseInt(request.getParameter("pages"));
            }
            ArrayList<UserObject> items = uc.getUserObject(null, (short)pages, (byte)10);
            
        %>
        <div class="container">
            <div class="row col-md-8 col-md-offset-2">
                <a href="ViewBackend?command=add_user" style="margin-bottom: 10px;" class="btn btn-primary">ADD</a>
                <div class="panel panel-success">
                    <div class="panel-heading">User</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Username</th>
                                    <th>Fullname</th>
                                    <th>Email</th>
                                    <th>User ID</th>
                                    <th style="width: 100px;"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(UserObject item: items){
                                %>
                                <tr>
                                    <td><%=stt++%></td>
                                    <td><%=item.getUser_name()%></td>
                                    <td><%=item.getUser_fullname()%></td>
                                    <td><%=item.getUser_email()%></td>
                                    <td><%=item.getUser_id()%></td>
                                    <td>
                                        <a href="ViewBackend?command=edit_user&&user_id=<%=item.getUser_id()%>">Edit</a>
                                        <a onclick="return window.confirm('Are you sure?');" href="UserServlet?command=delete&&user_id=<%=item.getUser_id()%>">Delete</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
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
                            <li><a href="view_user.jsp?pages=<%=back%>">&laquo;</a></li>
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
                            <li><a href="view_user.jsp?pages=<%=i%>"><%=i%></a></li>
                            <%}else{%>
                            <li><a href="view_user.jsp?pages=<%=i%>"><%=i%></a></li>
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
                            <li><a href="view_user.jsp?pages=<%=next%>">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
