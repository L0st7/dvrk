<%-- 
    Document   : view_add_edit_product
    Created on : Apr 5, 2018, 5:00:10 PM
    Author     : nguye
--%>

<%@page import="object.CategoryObject"%>
<%@page import="object.ProductObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="category.CategoryControl"%>
<%@page import="product.ProductControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link rel="stylesheet" type="text/css" href="backend/css/bootstrap.min.css">
	<script type="text/javascript" src="backend/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <div class="container">
            <div class="row col-md-10 col-md-offset-1">
                <div class="panel panel-primary">
                    <div class="panel-heading"><%=session.getAttribute("NAME")%></div>
                    <div class="panel-body">
                        <form action="ProductServlet" method="POST" role="form" class="form-horizontal" enctype="multipart/form-data">

                            <input type="hidden" name="productID" value="<%=session.getAttribute("PRODUCTID")%>"/>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Tên sản phẩm</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" id="" placeholder="Tên sản phẩm" name="product_name" value="<%=session.getAttribute("PRODUCT_NAME")%>">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Ảnh</label>
                                <div class="col-md-10">
                                    <input type="file" class="form-control" id="" name="product_img[]" multiple="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Danh mục</label>
                                <div class="col-md-10">
                                    <select name="product_category" id="" class="form-control">

                                        <option value="T-SHIRT">T-SHIRT</option>
                                        <option value="TOPWEAR">TOPWEAR</option>
                                        <option value="SHORTS">SHORTS</option>
                                        <option value="PANTS">PANTS</option>
                                        <option value="OUTERWEAR">OUTERWEAR</option>
                                        <option value="JEAN">JEAN</option>
                                        <option value="SALE">SALE</option>

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Giá</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" id="" placeholder="Giá sản phẩm" name="product_price" value="<%=session.getAttribute("PRODUCT_PRICE")%>">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Số lượng</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" id="" placeholder="Số lượng" name="product_total" value="<%=session.getAttribute("PRODUCT_TOTAL")%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Size</label>
                                <div class="col-md-10">
                                    <select name="product_size" id="" class="form-control">

                                        <option value="XS">XS</option>
                                        <option value="S">S</option>
                                        <option value="M">M</option>
                                        <option value="L">L</option>
                                        <option value="XL">XL</option>
                                        <option value="XXL">XXL</option>

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">Mô tả</label>
                                <div class="col-md-10">
                                    <textarea name="product_intro" id="" cols="30" rows="10">
								
                                    </textarea>
                                    <script type="text/javascript">
                                            CKEDITOR.replace("product_intro");
                                    </script>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <input type="hidden" name="command" value="addProduct"/>
                                    <button type="submit" class="btn btn-primary">Submit</button>		
                                </div>
                            </div>						

                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
