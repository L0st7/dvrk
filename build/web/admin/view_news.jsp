<%-- 
    Document   : view_news
    Created on : Apr 5, 2018, 5:02:53 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="container">
            <div class="row col-md-10 col-md-offset-1">
                <a href="view_add_edit_news.jsp" style="margin-bottom: 10px;" class="btn btn-primary">Add</a>
                <div class="panel panel-success">
                    <div class="panel-heading">News</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th style="width: 50px;">STT</th>
                                    <th>Ảnh</th>
                                    <th>Tên danh mục</th>
                                    <th style="width: 50px;"></th>
                                    <th style="width: 100px;"></th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td></td>
                                    <td>

                                        <img src="#" alt="" style="width: 100px;">

                                    </td>
                                    <td></td>
                                    <td>

                                    </td>
                                    <td>
                                        <a href="view_add_edit_news.jsp">Edit</a>
                                        <a onclick="return window.confirm('Are you sure?');" href="#">Delete</a>
                                    </td>
                                </tr>
                                <?php } ?>	
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>

                            <li><a href="#"></a></li>

                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
