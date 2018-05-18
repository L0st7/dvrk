<%-- 
    Document   : view_category_lookbook
    Created on : Apr 6, 2018, 9:06:28 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catagory</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="container">
            <div class="row col-md-6 col-md-offset-3">
                <a href="#" class="btn btn-success" style="margin-bottom: 10px;">ADD</a>
                <div class="panel panel-success">
                    <div class="panel-heading">Category Lookbook</div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>CategoryID</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>

                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="panel-footer">
                    <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>

                        <li><a href="#"</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
