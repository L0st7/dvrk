<%-- 
    Document   : view_home_page
    Created on : Apr 16, 2018, 11:31:41 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVRK CLOTHING </title>
    </head>
    <body>
        <%
            session.setAttribute("DISPLAY", "none");
        %>
        <jsp:include page="header_menu.jsp"></jsp:include>
            <div id="Wrapper">
                <div id="WrapInner">
                    <div id="ContentOuter">



                        <style type="text/css">
                            .no-touch #Header.scrolled {
                                background: none !important; 
                            }
                        </style>

                    </div>
                </div>
            </div>

            <div id="HomeSlides" class="flexslider full_screen">
                <ul class="slides"></ul>
            </div>
            <div class="pullup-section">
                <div id="homepage-single-image">
                    <div id="homepage-single-image-container">


                    </div>
                </div>
                <div id="homepage-image-grid">
                    <div id="homepage-image-grid-container">
                        <div class="image-grid-row-one">
                            <div class="image-grid-image igi-one">
                                <a href="#">
                                    <img src="theme.hstatic.net/1000178923/1000285707/14/images_grid_two_one_image_onece31.png?v=19">
                                    <div class="image-text">
                                        <span class="image-title">" Spring 2018 "</span>
                                        <span class="image-desc"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="image-grid-image igi-two">
                                <a href="#">
                                    <img src="theme.hstatic.net/1000178923/1000285707/14/images_grid_two_one_image_twoce31.png?v=19">
                                    <div class="image-text">
                                        <span class="image-title">" I see it coming "</span>
                                        <span class="image-desc"></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
