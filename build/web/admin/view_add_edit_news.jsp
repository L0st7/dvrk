<%-- 
    Document   : view_add_edit_news
    Created on : Apr 5, 2018, 5:05:50 PM
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
                <div class="panel panel-success">
                    <div class="panel-heading">Add/Edit</div>
                    <div class="panel-body">
                        <form action="<?php echo $form_action ?>" method="POST" role="form" enctype="multipart/form-data">

                            <div class="form-group form-inline">
                                <label for="" class="col-md-3">Name</label>
                                <input type="text" class="form-control" id="" placeholder="Name" name="c_name" class="col-md-7 col-md-offset-1" value="">
                            </div>

                            <div class="form-group form-inline">
                                <label for="" class="col-md-3"></label>
                                <input type="checkbox" name="c_hotnews" class="form-control" id="" class="col-md-7 col-md-offset-1" >&nbsp;Tin nổi bật
                            </div>

                            <div class="form-group form-inline">
                                <label for="" class="col-md-3">Đường dẫn ảnh</label>
                                <input type="file" class="form-control" id="" name="c_img" class="col-md-4 col-md-offset-1">

                                <img src="#">

                            </div>

                            <div class="form-group form-inline">
                                <label for="" class="col-md-2">Giới thiệu</label>
                                <div class="col-md-10">
                                    <textarea name="c_description" id="" cols="30" rows="10">
							
                                    </textarea>
                                    <script type="text/javascript">
                                    CKEDITOR.replace('c_description');
                                    </script>
                                </div>


                            </div>

                            <div class="form-group form-inline">
                                <label for="" class="col-md-2">Chi tiết</label>
                                <div class="col-md-10">
                                    <textarea name="c_content" id="" cols="30" rows="10">
							
                                    </textarea>
                                    <script type="text/javascript">
                                    CKEDITOR.replace('c_content');
                                    </script>
                                </div>


                            </div>					
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
