<%-- 
    Document   : view_product_details
    Created on : Apr 16, 2018, 12:21:15 PM
    Author     : nguye
--%>

<%@page import="object.AddProductObject"%>
<%@page import="object.CartObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="object.ProductObject"%>
<%@page import="product.ProductControl"%>
<%@page import="cp.ConnectionPoolImpl"%>
<%@page import="cp.ConnectionPool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        int id = Integer.parseInt(request.getParameter("product_id"));
        ConnectionPool cp = new ConnectionPoolImpl();
        ProductControl pc = new ProductControl(cp);
        ProductObject po = pc.getProductObject(id);
        int total = pc.getProductCount();
        ArrayList<ProductObject> items = pc.getProductObject(null, (short)1, (byte)total);
        DecimalFormat df = new DecimalFormat("###,###,###");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title style="text-transform: uppercase;"><%=po.getProduct_name()%> &ndash; DVRK CLOTHING</title>
    </head>
    <body>
        <jsp:include page="header_menu.jsp"></jsp:include>

        <jsp:include page="left_menu.jsp"></jsp:include>

            <div id="Wrapper">
                <div id="WrapInner">

                    <div id="ContentOuter">
                        <link href='theme.hstatic.net/1000178923/1000285707/14/slickce31.css?v=19' rel='stylesheet' type='text/css'  media='all'  />
                        <script>
                            jQuery.fn.extend({
                                showZoom: function (a) {
                                    this.zoomContainer = $(".product-images-zoom");
                                    var b = this,
                                            c = this.attr("id");
                                    this.savedScrollTop = $(document).scrollTop();
                                    var d = this.zoomContainer.find("img").length,
                                            e = 0;
                                    this.zoomContainer.css("opacity", "1").show().one("click", $.proxy(this.hideZoom, this));

                                    if (isMobile()) {
                                        dest = $(".product-images-zoom-image-wrapper img#" + c).parent().offset().top;
                                        $('html,body').animate({scrollTop: dest}, 0, 'swing');
                                    }

                                    var f = function () {
                                        b.zoomContainer.css("opacity", "1");
                                        if (!isMobile()) {
                                            $(document).scrollTop(b.zoomContainer.find(".product-images-zoom-image-wrapper img#" + c).parent().offset().top);
                                        }
                                    };
                                    this.zoomImagesLoaded ? f() : this.zoomContainer.find("img").each(function () {
                                        var a = $(this);
                                        a.on("load", function () {
                                            e++, e === d && (b.zoomImagesLoaded = !0, f())
                                        }), a.attr("src", a.attr("data-src"))
                                    })
                                },
                                hideZoom: function () {
                                    this.zoomContainer.hide();
                                    $(document).scrollTop(this.savedScrollTop || 0);
                                }
                            });

                            $(document).ready(function () {

                                if (isMobile() || isiPad()) {
                                    $('.slickSlider').slick({
                                        autoplay: true,
                                        autoplaySpeed: 2000,
                                        fade: true,
                                        speed: 800,
                                        cssEase: 'linear',
                                        dots: true
                                    });
                                } else if (!isMobile()) {
                                    $('.slickSlider').remove();
                                }
                                ;

                                var a = $(this);
                                if (isMobile() || isiPad()) {
                                    console.log("using imageZoomMobile");
                                    $(".imageZoomMobile img").each(function (index) {
                                        $(this).on("click", function () {
                                            $(this).showZoom();
                                        });
                                    });
                                } else if (!isMobile()) {
                                    console.log("using imageZoom");
                                    $(".imageZoom img").each(function (index) {
                                        $(this).on("click", function () {
                                            $(this).showZoom();
                                        });
                                    });
                                }
                            });
                        </script>
                        <div class="product-right">
                            <!--Giao diện zoom-->
                            <div class="product-images-zoom" id="piz">

                                <div class="product-images-zoom-wrapper">
                                    <div class="product-images-zoom-image-wrapper">
                                        <img id="1" alt="<%=po.getProduct_name()%>" src="#" data-src="<%=po.getProduct_image()%>" />
                                    </div>
                                </div>
                                
                                <%
                                    if(po.getProduct_image2()!=null){
                                %>
                                <div class="product-images-zoom-wrapper">
                                    <div class="product-images-zoom-image-wrapper">
                                        <img id="1" alt="<%=po.getProduct_name()%>" src="#" data-src="<%=po.getProduct_image2()%>" />
                                    </div>
                                </div>
                                <%}%>
                                
                                <%
                                    if(po.getProduct_image3()!=null){
                                %>
                                <div class="product-images-zoom-wrapper">
                                    <div class="product-images-zoom-image-wrapper">
                                        <img id="1" alt="<%=po.getProduct_name()%>" src="#" data-src="<%=po.getProduct_image3()%>" />
                                    </div>
                                </div>
                                <%}%>
                                
                                <%
                                    if(po.getProduct_image4()!=null){
                                %>
                                <div class="product-images-zoom-wrapper">
                                    <div class="product-images-zoom-image-wrapper">
                                        <img id="1" alt="<%=po.getProduct_name()%>" src="#" data-src="<%=po.getProduct_image4()%>" />
                                    </div>
                                </div>
                                <%}%>
                                
                                <%
                                    if(po.getProduct_image5()!=null){
                                %>
                                <div class="product-images-zoom-wrapper">
                                    <div class="product-images-zoom-image-wrapper">
                                        <img id="1" alt="<%=po.getProduct_name()%>" src="#" data-src="<%=po.getProduct_image5()%>" />
                                    </div>
                                </div>
                                <%}%>

                            </div>
                            <section id="ProductMain">
                                
                                <!--Giao diện chính-->
                                <section class="product_imgs clearfix hide-on-mobile">

                                    <div class="left-image-prod">
                                        <div class="imageZoom" style="display: table-cell;  vertical-align: middle;  text-align: center;  position: relative;  width: 100%;">
                                            <img id="1" class="pp_img_lge" alt="<%=po.getProduct_name()%>" src="<%=po.getProduct_image()%>"/>
                                        </div>
                                    </div>
                                    <%
                                        if(po.getProduct_image2()!=null){
                                    %>
                                    <div class="left-image-prod">
                                        <div class="imageZoom" style="display: table-cell;  vertical-align: middle;  text-align: center;  position: relative;  width: 100%;">
                                            <img id="1" class="pp_img_lge" alt="<%=po.getProduct_name()%>" src="<%=po.getProduct_image2()%>"/>
                                        </div>
                                    </div>
                                    <%}%>
                                    
                                    <%
                                        if(po.getProduct_image3()!=null){
                                    %>
                                    <div class="left-image-prod">
                                        <div class="imageZoom" style="display: table-cell;  vertical-align: middle;  text-align: center;  position: relative;  width: 100%;">
                                            <img id="1" class="pp_img_lge" alt="<%=po.getProduct_name()%>" src="<%=po.getProduct_image3()%>"/>
                                        </div>
                                    </div>
                                    <%}%>
                                    
                                    <%
                                        if(po.getProduct_image4()!=null){
                                    %>
                                    <div class="left-image-prod">
                                        <div class="imageZoom" style="display: table-cell;  vertical-align: middle;  text-align: center;  position: relative;  width: 100%;">
                                            <img id="1" class="pp_img_lge" alt="<%=po.getProduct_name()%>" src="<%=po.getProduct_image4()%>"/>
                                        </div>
                                    </div>
                                    <%}%>
                                    
                                    <%
                                        if(po.getProduct_image5()!=null){
                                    %>
                                    <div class="left-image-prod">
                                        <div class="imageZoom" style="display: table-cell;  vertical-align: middle;  text-align: center;  position: relative;  width: 100%;">
                                            <img id="1" class="pp_img_lge" alt="<%=po.getProduct_name()%>" src="<%=po.getProduct_image5()%>"/>
                                        </div>
                                    </div>
                                    <%}%>
                                    
                                </section>
                            </section>
                            <div class="product-description">
                                <div class="product-description-inner">
                                    <h2 class="section_title"><%=po.getProduct_name()%></h2>
                                    <div class="hr"></div>
                                    <div class="product_description">
                                        <p><%=po.getProduct_intro()%></p>
                                    </div>
                                </div>
                            </div>
                            <div class="content_side_outer" style="z-index: 2;position: relative;">
                                <section id="product-1013300238" class="right-description content_side mt20">

                                    <div class="right-ipad">
                                        <div class="mobile-sec">
                                            <div class="clearboth"></div>
                                            <form action="CartServlet" method="POST" class="form-cart-wrapp clearfix" id="product-form">

                                                <input type="hidden" name="productID" value="<%=request.getParameter("product_id")%>"/>
                                                <div class="price-desktop">
                                                    <span class="price_lg price_lg">
                                                        <h4 style="font-weight: bold"><span class=money><%=df.format(po.getProduct_price())%>₫</span></h4>
                                                    </span>
                                                </div>
                                                <a class="popup-with-zoom-anim" href="#size-chart"><p style="text-align: center;" class="size-link">Xem kích thước</p></a>

                                                <div class="select-margintop">
                                                    <div class="select clearfix">
                                                        <div class="selector-wrapper">
                                                            <select name="product_name">
                                                                <option value="<%=po.getProduct_name()%>"><%=po.getProduct_name()%></option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="select clearfix">
                                                        <div class="selector-wrapper">
                                                            <select name="size">
                                                                <option value="<%=po.getProduct_size()%>"><%=po.getProduct_size()%></option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="select">
                                                        <input type="number" size="2" class="quantity" min="1" max="<%=po.getProduct_total()%>" name="count" value="1" autocomplete="off"/>
                                                    </div>
                                                </div>
                                                <div class="price_outer">
                                                    <div class="purchase clearfix">
                                                        <%
                                                            if(po.getProduct_total()==0){
                                                        %>
                                                        <button id="add-to-bag" class=" add" disabled="true" style="background: #474747;">
                                                            Hết hàng 
                                                        </button>
                                                        <%}else{%>
                                                        <input type="hidden" name="command" value="addCart"/>
                                                        <button id="add-to-bag" name="add"  class=" add">
                                                            Thêm vào giỏ 
                                                        </button>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </form>				
                                            <div class="hr"></div>
                                            <div class="show_tags">
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                        

                        <div id="size-chart" class="zoom-anim-dialog mfp-hide">
                            <ul class="size-chart-title">
                                <li tab="product-measurements" class="active">PRODUCT MEASUREMENTS</li>
                            </ul>
                            <div class="size-chart-content">
                                <div class="tab-content product-measurements">
                                    <div class="size-chart-left">
                                        <div class="measurement-container">




                                            <img src="theme.hstatic.net/1000178923/1000285707/14/size-selvedgedenimjackets-1ce31.jpg?v=19" class="img-responsive">





                                            <img src="theme.hstatic.net/1000178923/1000285707/14/size-selvedgedenimjackets-2ce31.jpg?v=19" class="img-responsive">





                                            <img src="theme.hstatic.net/1000178923/1000285707/14/size-selvedgedenimjackets-3ce31.jpg?v=19" class="img-responsive">





                                            <img src="theme.hstatic.net/1000178923/1000285707/14/size-selvedgedenimjackets-4ce31.jpg?v=19" class="img-responsive">






                                        </div>
                                    </div>
                                </div>
                                <div class="tab-content size-conversion-chart hidden">
                                </div>    
                            </div>
                        </div>
                        <script>
                            $(document).ready(function () {
                                var prefix_img_size_name = 'size-selvedgedenimjackets-';
                                $('.popup-with-zoom-anim').magnificPopup({
                                    type: 'inline',
                                    fixedContentPos: false,
                                    fixedBgPos: true,
                                    overflowY: 'auto',
                                    closeBtnInside: true,
                                    preloader: false,
                                    midClick: true,
                                    removalDelay: 300,
                                    mainClass: 'my-mfp-zoom-in'
                                });

                                $(document).scroll(function () {
                                    checkOffset();
                                });

                                function checkOffset() {
                                    if ($('.right-description').offset().top + $('.right-description').height() >= $('.recently-viewed-section').offset().top) {
                                        $('.right-description').css('position', 'absolute');
                                        $('.right-description').css('top', '-248px');
                                    }
                                    if ($(document).scrollTop() + window.innerHeight < $('.recently-viewed-section').offset().top + 210) {
                                        $('.right-description').css('position', 'fixed');
                                        $('.right-description').css('top', '35%');
                                    }

                                    if ($('.product-description-inner').offset().top + $('.product-description-inner').outerHeight()
                                            >= $('.recently-viewed-section').offset().top) {
                                        var x = $('#Footer').outerHeight() + $('.recently-viewed-section').outerHeight() + $('.product-description-inner').outerHeight() + 80;
                                        var y = $(document).height() - x;

                                        $('.product-description-inner').css('position', 'absolute');
                                        $('.product-description-inner').css('top', y);
                                    }
                                    if ($(document).scrollTop() + window.innerHeight < $('.recently-viewed-section').offset().top + 210) {
                                        $('.product-description-inner').css('position', 'fixed');
                                        $('.product-description-inner').css('top', '34%');
                                    }
                                }
                            });
                        </script>


                    </div>
                </div>
            </div>
            <%
                CartObject co =(CartObject) session.getAttribute("PRODUCTRELOAD");
                if(co!=null){
                    
            %>

            <section id="Collections" class="recently-viewed-section">
                <div class="container">
                    <div id="recently-viewed-products" class="collection clearfix" style="">
                        <div class="hr tilte-related-products"><span>Đã xem gần đây</span></div>
                        <%
                            ArrayList<AddProductObject> cartReload = co.getCart();
                            for(AddProductObject apo: cartReload){
                        %>
                        <div id="product-covered-hoodie-pink" class="product recently-viewed-product">     
                            <div class="image">      
                                <a href="view_product_details.jsp?product_id=<%=apo.getProduct_count()%>">       
                                    <img src="<%=apo.getProduct_img()%>">      
                                </a>     
                            </div>     
                            <div class="details">      
                                <a href="view_product_details.jsp?product_id=<%=apo.getProduct_count()%>">       
                                    <div class="related-title">
                                        <span><%=apo.getProduct_name()%></span>
                                    </div>       
                                    <div class="">        
                                        <span class="product_price">         
                                            <span class="">          
                                                <h4>           
                                                    <span class="money" style="font-size:18px;"><%=df.format(apo.getProduct_price())%>₫</span>          
                                                </h4>         
                                            </span>        
                                        </span>       
                                    </div>      
                                </a>     
                            </div>    
                        </div>
                        <%}%>
                    </div>
                </div>
            </section>
            <%}else{%>
            <section id="Collections" class="recently-viewed-section">
                <div class="container">
                    <div id="recently-viewed-products" class="collection clearfix" style="">
                        <div class="hr tilte-related-products"><span>Đã xem gần đây</span></div>
                          
                        </div>
                    </div>
                </div>
            </section>
            <%}%>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
