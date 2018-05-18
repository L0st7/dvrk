<%-- 
    Document   : index
    Created on : Apr 8, 2018, 5:58:45 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link href="https://dvrk-clothing.myharavan.com/admin/favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />



        <link href="backend/css/checkouts.css" rel="stylesheet"/>
    </head>
    <body class="hideresponsive authpage loginaction">

        <div id="blanklayout" >
            <div class="login">
                <div class="logincontainer ">
                    <div class="text-center mb20">
                        <h1 class="dialog-heading">DVRK CLOTHING</h1>
                        <h2 class="dialog-subheading">Đăng nhập vào quản trị</h2>
                    </div>
                    <div id="login" class="login-container">
                        <form action="UserLogin" class="form-signin form-signin_admin" id="loginform" maxlength="50" method="POST">                
                            <ul class="list-group">

                                <li class="form-group list-group-item">
                                    <input id="ShopName" name="ShopName" type="hidden" value="DVRK CLOTHING" />
                                    <input id="SubDomain" name="SubDomain" type="hidden" value="dvrk-clothing" />                                
                                    <input class="form-control username" id="UserName" maxlength="50" name="UserName" placeholder="Tài khoản đăng nhập" type="text" value="" />

                                    <i class="ico dialog-ico ico-email"></i>
                                </li>
                                <li class="form-group list-group-item">
                                    <input class="form-control" id="Password" maxlength="50" name="Password" placeholder="Mật khẩu" type="password" />

                                    <i class="ico dialog-ico ico-password"></i>
                                </li>
                                <li class="list-group-item">
                                    <button class="btn-login" type="submit">Đăng nhập</button>
                                </li>
                            </ul>
                            <div class="rememberme">
                                <input id="RememberMe" name="RememberMe" type="checkbox" value="true" /><input name="RememberMe" type="hidden" value="false" />
                                <label for="RememberMe">Duy tr&#236; đăng nhập</label>
                                <label class="inline v-a-t">&nbsp;|&nbsp;</label>
                                <a class="link-forgot-pass" href="#">Bạn qu&#234;n mật khẩu?</a>                        
                            </div>
                        </form>        
                    </div>

                </div>
            </div>

        </div>
    </body>
</html>
