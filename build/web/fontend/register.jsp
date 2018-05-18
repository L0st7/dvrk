<%-- 
    Document   : register
    Created on : Apr 30, 2018, 12:25:49 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo tài khoản - DVRK CLOTHING</title>
    </head>
    <body>
        <jsp:include page="header_menu.jsp"></jsp:include>
        <jsp:include page="left_menu.jsp"></jsp:include>

            <div id="Wrapper">
                <div id="WrapInner">
                    <header id="Header"></header>
                    <div id="ContentOuter" style="float: none;width: 100%;text-align: center;">
                        <section id="PageMain" class="account">
                            <h3 class="section_title">Create Account</h3>
                            <div class="hr"></div>
                            <form method="post" action="CustomerServlet" id="create_customer" accept-charset="UTF-8">
                                <input type="hidden" value="create_customer" name="form_type">
                                <input type="hidden" name="utf8" value="✓">
                                <div id="last_name" class="clearfix large_form">
                                    <label for="last_name" class="login">Họ và tên</label><br>
                                    <input type="text" value="" name="customer_name" id="last_name" class="large" size="30">
                                </div>

                                <div id="first_name" class="clearfix large_form">
                                    <label for="first_name" class="login">Nick name</label><br>
                                    <input type="text" value="" name="customer_account" id="first_name" class="large" size="30">
                                </div>
                                <div id="email" class="clearfix large_form">
                                    <label for="email" class="login">Số điện thoại</label><br>
                                    <input type="text" value="" name="customer_mobile" id="email" class="large" size="30">
                                </div>
                                <div id="email" class="clearfix large_form">
                                    <label for="email" class="login">Địa chỉ</label><br>
                                    <input type="text" value="" name="customer_address" id="email" class="large" size="30">
                                </div>
                                <div id="email" class="clearfix large_form">
                                    <label for="email" class="login">Email</label><br>
                                    <input type="email" value="" name="customer_email" id="email" class="large" size="30">
                                </div>

                                <div id="password" class="clearfix large_form">
                                    <label for="password" class="login">Mật khẩu</label><br>
                                    <input type="password" value="" name="customer_password" id="password" class="large password" size="30">
                                </div>

                                <p>
                                    <input type="hidden" name="command" value="addCustomer"/>
                                    <input class="btn" type="submit" value="Tạo / Xác nhận Tài khoản ">
                                    <span> &nbsp; hoặc &nbsp; <a href="view_store.jsp">Trở về cửa hàng</a></span>
                                </p>
                            </form>

                        </section>

                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
