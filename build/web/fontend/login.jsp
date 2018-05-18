<%-- 
    Document   : login
    Created on : Apr 30, 2018, 11:15:40 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tài khoản - DVRK CLOTHING</title>

    </head>
    <body>
        <jsp:include page="header_menu.jsp"></jsp:include>
        <jsp:include page="left_menu.jsp"></jsp:include>

            <div id="Wrapper">
                <div id="WrapInner">
                    <header id="Header"></header>
                    <div id="ContentOuter" style="float: none;width: 100%;text-align: center;">
                        <section id="PageMain" class="account">
                            <h3 class="section_title">Đăng nhập</h3>
                            <p class="loggin-message">Chào mùng bạn đến với trang mua hàng cho khu vực vietnam. Trang web này sẽ giúp các bạn có một trải nghiệm mua sắm các sản phẩm của DVRK dễ dàng hơn. Khởi tạo tài khoản để đặt và theo dõi đơn hàng của bạn được dễ dàng hơn,
                            </p>
                            <div class="hr"></div>
                            <form method="post" action="CustomerLogin" id="customer_login" accept-charset="UTF-8">
                                <div id="login_email" class="clearfix">
                                    <label for="customer_email" class="login">Email</label></br>
                                    <input type="email" value="" name="customer_email" id="customer_email" size="30">
                                </div>
                                <div id="login_password" class="clearfix">
                                    <label for="customer_password" class="login">Mật khẩu</label></br>
                                    <input type="password" value="" name="customer_pass" id="customer_password" class="large password" size="16">
                                </div>
                                <p>
                                    <input type="submit" value="Đăng nhập">
                                    <span> &nbsp; hoặc &nbsp; 
                                        <a href="view_store.jsp">Trở về cửa hàng</a>
                                    </span>
                                </p>
                                <div class="clearboth">&nbsp;</div>
                                <a href="#" onclick="showRecoverPasswordForm()">Quên mật khẩu?</a>
                            </form>
                            <a href="register.jsp" id="customer_register_link">Tạo / Xác nhận Tài khoản</a>
                            <div id="recover-password" style="display:none">
                                <div class="hr"></div>
                                <h3 class="section_title">Đặt lại mật khẩu</h3>
                                <form method="post" action="" accept-charset="UTF-8">
                                    <input type="hidden" value="recover_customer_password" name="form_type">
                                    <input type="hidden" name="utf8" value="✓">
                                    <p>Chúng tôi sẽ gửi cho bạn một email để đặt lại mật khẩu của bạn.</p>
                                    <div id="recover_email" class="clearfix">
                                        <label for="email" class="large">Email</label><br>
                                        <input type="email" value="" size="30" name="email" id="recover-email">
                                    </div>
                                    <p>
                                        <input type="submit" value="Gửi">
                                        <span> &nbsp; or &nbsp; 
                                            <a href="#" onclick="hideRecoverPasswordForm()">Hủy</a>
                                        </span>
                                    </p>
                                </form>
                            </div>
                        </section>
                        <script>
                            function showRecoverPasswordForm() {
                                document.getElementById('recover-password').style.display = 'block';
                                document.getElementById('customer').style.display = 'none';
                                return false;
                            }

                            function hideRecoverPasswordForm() {
                                document.getElementById('recover-password').style.display = 'none';
                                document.getElementById('customer').style.display = 'block';
                                return false;
                            }

                            if (window.location.hash == '#recover') {
                                showRecoverPasswordForm()
                            }
                        </script>
                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
