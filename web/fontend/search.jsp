<%-- 
    Document   : search
    Created on : Apr 30, 2018, 1:37:07 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm - DVRK CLOTHING</title>
    </head>
    <body>
        <jsp:include page="header_menu.jsp"></jsp:include>
        <jsp:include page="left_menu.jsp"></jsp:include>

            <div id="Wrapper">
                <div id="WrapInner">
                    <div id="ContentOuter" style="margin-top: 27px; opacity: 1; margin-bottom: 100px;">


                        <section id="Collections" class="page_search">
                            <h3 class="section_title">Search</h3>
                            <div class="hr"></div>
                            <form class="search" action="SearchServlet" method="POST">
                                <input name="search" class="search_box" placeholder="Tìm kiếm trên trang web ..." type="text">
                                <button type="submit" class="search_submit ss-icon">🔎</button>
                            </form>
                        </section>



                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
