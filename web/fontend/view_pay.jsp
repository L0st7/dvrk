<%-- 
    Document   : view_pay
    Created on : Apr 27, 2018, 9:46:24 PM
    Author     : nguye
--%>

<%@page import="object.CustomerObject"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="object.AddProductObject"%>
<%@page import="object.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="theme.hstatic.net/1000178923/1000285707/14/favicon.png?v=19" type="image/png">
        <title>
            DVRK CLOTHING - Thanh toán đơn hàng
        </title>
        <link href="css/checkouts.css" rel="stylesheet" type="text/css" media="all">
        <link href="css/check_out.css" rel="stylesheet" type="text/css" media="all">
        <script src="css/jquery_002.js" type="text/javascript"></script>
        <script src="css/jquery.js" type="text/javascript"></script>

        <script type="text/javascript">
            window.onpageshow = function (event) {
                if (event.persisted) {
                    var currentUrl = '';


                    currentUrl = '/checkouts/ce15703f6c47442ea150e4c7c0ab487f?step=1';


                    if (currentUrl)
                        window.location = currentUrl;
                }
            };



            var isInit = false;

            function funcFormOnSubmit(e) {

                if (!isInit) {
                    isInit = true;

                    $.fn.tagName = function () {
                        return this.prop("tagName").toLowerCase();
                    };
                }

                if (typeof (e) == 'string') {
                    var element = $(e);
                    var formData = e;
                } else {
                    var element = this;
                    var formData = this;
                    e.preventDefault();
                }

                $(element).find('button:submit').addClass('btn-loading');

                var formId = $(element).attr('id'), replaceElement = [], funcCallback;

                if (formId == undefined || formId == null || formId == '')
                    return;



                if (formId == 'form_next_step') {
                    formData = '.step-sections';
                    replaceElement.push('.step-sections');
                } else if (
                        formId == 'form_discount_add'
                        || formId == 'form_discount_remove'
                        || formId == 'form_update_location'


                        || formId == 'section-shipping-rate'

                        ) {
                    replaceElement.push('#form_update_location');
                    replaceElement.push('#change_pick_location_or_shipping');
                    replaceElement.push('.inventory_location_data');
                    replaceElement.push('.order-summary-toggle-inner .order-summary-toggle-total-recap');
                    replaceElement.push('.order-summary-sections');

                    replaceElement.push('#section-shipping-rate');

                }





                if (!$(formData) || $(formData).length == 0) {
                    window.location.reload();
                    return false;
                }

                if ($(formData).tagName() != 'form')
                    formData += ' :input';

                $.ajax({
                    type: 'GET',
                    url: window.location.origin + window.location.pathname + '?' + $(formData).serialize() + encodeURI('&form_name=' + formId),
                    success: function (html) {
                        if ($(html).attr('id') == 'redirect-url') {
                            window.location = $(html).val();
                        } else {
                            if (replaceElement.length > 0) {
                                for (var i = 0; i < replaceElement.length; i++)
                                {
                                    var tempElement = replaceElement[i];
                                    var newElement = $(html).find(tempElement);

                                    if (newElement.length > 0) {
                                        if (tempElement == '.step-sections')
                                            $(tempElement).attr('step', $(newElement).attr('step'));

                                        var listTempElement = $(tempElement);

                                        for (var j = 0; j < newElement.length; j++)
                                            if (j < listTempElement.length)
                                                $(listTempElement[j]).html($(newElement[j]).html());
                                    }
                                }
                            }

                            $('body').attr('src', $(html).attr('src'));
                            $(element).find('button:submit').removeClass('btn-loading');

                            if (($('body').find('.field-error') && $('body').find('.field-error').length > 0)
                                    || ($('body').find('.has-error') && $('body').find('.has-error').length > 0))
                                $("html, body").animate({scrollTop: 0}, "slow");

                            if (funcCallback)
                                funcCallback();
                        }
                    }
                }).fail(function () {
                    $(element).find('button:submit').removeClass('btn-loading');
                });

                return false;
            }
            ;
            function funcSetEvent() {

                $('body')
                        .on('change', '#section-payment-method input:radio', function () {
                            $('#section-payment-method .content-box-row.content-box-row-secondary').addClass('hidden');

                            var id = $(this).attr('id');

                            if (id) {
                                var sub = $('body').find('.content-box-row.content-box-row-secondary[for=' + id + ']')

                                if (sub && sub.length > 0) {
                                    $(sub).removeClass('hidden');
                                }
                            }
                        });
            }
            ;
            
            
            function funcInit() {
                funcSetEvent();


            }
            $(document).ready(function () {
                funcInit();
            });
        </script>




    </head>
    <body>
        <div class="content">

            <div class="wrap">
                <div class="sidebar">
                    <div class="sidebar-content">
                        <div class="order-summary order-summary-is-collapsed">
                            <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                            <div class="order-summary-sections">
                                <div class="order-summary-section order-summary-section-product-list" data-order-summary-section="line-items">
                                    <table class="product-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Hình ảnh</span></th>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Số lượng</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <%
                                            CartObject co = (CartObject) session.getAttribute("CART");
                                            DecimalFormat df = new DecimalFormat("###,###,###");
                                            for (int i = 0; i < co.getCountProduct(); i++) {
                                                AddProductObject apo = co.getProduct(i);
                                        %>
                                        <tbody>

                                            <tr class="product" data-product-id="1013300238" data-variant-id="1025587187">
                                                <td class="product-image">
                                                    <div class="product-thumbnail">
                                                        <div class="product-thumbnail-wrapper">
                                                            <img class="product-thumbnail-image" alt="" src="<%=apo.getProduct_img()%>">
                                                        </div>
                                                        <span class="product-thumbnail-quantity" aria-hidden="true"><%=apo.getProduct_count()%></span>
                                                    </div>
                                                </td>
                                                <td class="product-description">
                                                    <span class="product-description-name order-summary-emphasis" style="text-transform: uppercase;"><%=apo.getProduct_name()%></span>

                                                    <span class="product-description-variant order-summary-small-text" style="text-transform: uppercase;">
                                                        <%=apo.getProduct_name()%> / <%=apo.getProduct_size()%>
                                                    </span>

                                                </td>
                                                <td class="product-quantity visually-hidden"><%=apo.getProduct_count()%></td>
                                                <td class="product-price">
                                                    <span class="order-summary-emphasis"><%=df.format(apo.getProduct_price()*apo.getProduct_count())%>₫</span>
                                                </td>
                                            </tr>

                                        </tbody>
                                        <%}%>
                                    </table>
                                </div>

                                <div class="order-summary-section order-summary-section-discount" data-order-summary-section="discount">
                                    <form id="form_discount_add" accept-charset="UTF-8" method="post">
                                        <input name="utf8" value="✓" type="hidden">
                                        <div class="fieldset">
                                            <div class="field  ">
                                                <div class="field-input-btn-wrapper">
                                                    <div class="field-input-wrapper">
                                                        <label class="field-label" for="discount.code">Mã giảm giá</label>
                                                        <input placeholder="Mã giảm giá" class="field-input" data-discount-field="true" autocomplete="off" autocapitalize="off" spellcheck="false" size="30" id="discount.code" name="discount.code" type="text">
                                                    </div>
                                                    <button type="submit" class="field-input-btn btn btn-default btn-disabled">
                                                        <span class="btn-content">Sử dụng</span>
                                                        <i class="btn-spinner icon icon-button-spinner"></i>
                                                    </button>
                                                </div>

                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="order-summary-section order-summary-section-total-lines" data-order-summary-section="payment-lines">
                                    <table class="total-line-table">
                                        <thead>
                                            <tr>
                                                <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                                <th scope="col"><span class="visually-hidden">Giá</span></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="total-line total-line-subtotal">
                                                <td class="total-line-name">Tạm tính</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-subtotal-price-target="198000000">
                                                        <%=df.format(co.priceTotal())%>₫
                                                    </span>
                                                </td>
                                            </tr>

                                            <%
                                                if(co.priceTotal()<1000000){
                                            %>
                                            <tr class="total-line total-line-shipping">
                                                <td class="total-line-name">Phí vận chuyển</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-total-shipping-target="0">

                                                        40,000₫

                                                    </span>
                                                </td>
                                            </tr>
                                           
                                        </tbody>
                                        <tfoot class="total-line-table-footer">
                                            <tr class="total-line">
                                                <td class="total-line-name payment-due-label">
                                                    <span class="payment-due-label-total">Tổng cộng</span>
                                                </td>
                                                <td class="total-line-name payment-due">
                                                    <span class="payment-due-currency">VND</span>
                                                    <span class="payment-due-price" data-checkout-payment-due-target="198000000">
                                                        <%=df.format(co.priceTotal() + 40000)%>₫
                                                    </span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                        <%}else{%>
                                        <tr class="total-line total-line-shipping">
                                                <td class="total-line-name">Phí vận chuyển</td>
                                                <td class="total-line-price">
                                                    <span class="order-summary-emphasis" data-checkout-total-shipping-target="0">

                                                        Miễn phí

                                                    </span>
                                                </td>
                                            </tr>
                                           
                                        </tbody>
                                        <tfoot class="total-line-table-footer">
                                            <tr class="total-line">
                                                <td class="total-line-name payment-due-label">
                                                    <span class="payment-due-label-total">Tổng cộng</span>
                                                </td>
                                                <td class="total-line-name payment-due">
                                                    <span class="payment-due-currency">VND</span>
                                                    <span class="payment-due-price" data-checkout-payment-due-target="198000000">
                                                        <%=df.format(co.priceTotal())%>₫
                                                    </span>
                                                </td>
                                            </tr>
                                        </tfoot>
                                        <%}%>
                                        
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-header">
                        <a href="view_home_page.jsp" class="logo">
                            <h1 class="logo-text">DVRK CLOTHING</h1>
                        </a>

                        <ul class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="view_cart.jsp">Giỏ hàng</a>
                            </li>

                            <li class="breadcrumb-item breadcrumb-item-current">
                                Thông tin giao hàng
                            </li>

                        </ul>

                    </div>

                    <form action="PayServlet" method="POST" role="form">
                        <div class="main-content">

                        <div class="step">
                            <div class="step-sections steps-onepage" step="1">


                                <div class="section">
                                    <div class="section-header">
                                        <h2 class="section-title">Thông tin giao hàng</h2>
                                    </div>
                                    <div class="section-content section-customer-information no-mb">


                                        <p class="section-content-text">
                                            Bạn đã có tài khoản?
                                            <a href="http://dvrk.vn/account/login?urlredirect=%2Fcheckouts%2Fce15703f6c47442ea150e4c7c0ab487f%3Fstep%3D1">Đăng nhập</a>
                                        </p>


                                        <div class="fieldset">
                                            <%
                                                if(session.getAttribute("CustomerLogin")==null){
                                            %>

                                            <div class="field   ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label">Họ và tên</label>
                                                    <input placeholder="Họ và tên" class="field-input" size="30" name="customer_name" type="text"/>
                                                </div>

                                            </div>



                                            <div class="field  field-two-thirds  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="checkout_user_email">Email</label>
                                                    <input placeholder="Email" autocapitalize="off" spellcheck="false" class="field-input" size="30" id="checkout_user_email" name="customer_email" type="email"/>
                                                </div>

                                            </div>



                                            <div class="field field-required field-third  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_phone">Số điện thoại</label>
                                                    <input placeholder="Số điện thoại" autocapitalize="off" spellcheck="false" class="field-input" size="30" maxlength="11" id="billing_address_phone" name="customer_phone" type="tel"/>
                                                </div>
                                            </div>


                                            <div class="field field-required  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_address1">Địa chỉ</label>
                                                    <input placeholder="Địa chỉ" autocapitalize="off" spellcheck="false" class="field-input" size="30" id="billing_address_address1" name="customer_address" type="text"/>
                                                </div>

                                            </div>
                                            
                                            <%}else{%>
                                            <%
                                                CustomerObject cto = (CustomerObject)session.getAttribute("CustomerLogin");
                                            %>
                                                <div class="field   ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label">Họ và tên</label>
                                                    <input placeholder="Họ và tên" class="field-input" size="30" name="customer_name" type="text" value="<%=cto.getCustomer_fullname()%>"/>
                                                </div>

                                            </div>



                                            <div class="field  field-two-thirds  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="checkout_user_email">Email</label>
                                                    <input placeholder="Email" autocapitalize="off" spellcheck="false" class="field-input" size="30" id="checkout_user_email" name="customer_email" type="email" value="<%=cto.getCustomer_email()%>"/>
                                                </div>

                                            </div>



                                            <div class="field field-required field-third  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_phone">Số điện thoại</label>
                                                    <input placeholder="Số điện thoại" autocapitalize="off" spellcheck="false" class="field-input" size="30" maxlength="11" id="billing_address_phone" name="customer_phone" type="tel" value="<%=cto.getCustomer_mobile()%>"/>
                                                </div>
                                            </div>


                                            <div class="field field-required  ">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="billing_address_address1">Địa chỉ</label>
                                                    <input placeholder="Địa chỉ" autocapitalize="off" spellcheck="false" class="field-input" size="30" id="billing_address_address1" name="customer_address" type="text" value="<%=cto.getCustomer_address()%>"/>
                                                </div>

                                            </div>
                                            <%}%>
                                            

                                        </div>
                                    </div>
                                    <div id="change_pick_location_or_shipping">

                                        <div id="section-payment-method" class="section">
                                            <div class="section-header">
                                                <h2 class="section-title">Phương thức thanh toán</h2>
                                            </div>
                                            <div class="section-content">
                                                <div class="content-box">


                                                    <div class="radio-wrapper content-box-row">
                                                        <label class="radio-label" for="payment_method_id_546924">
                                                            <div class="radio-input">
                                                                <input id="payment_method_id_546924" class="input-radio" name="payment" value="Thanh toán khi nhận hàng" checked="checked" type="radio">
                                                            </div>
                                                            <span class="radio-label-primary">Thanh toán khi nhận hàng (COD) </span>
                                                        </label>
                                                    </div>


                                                    <div class="radio-wrapper content-box-row">
                                                        <label class="radio-label" for="payment_method_id_490070">
                                                            <div class="radio-input">
                                                                <input id="payment_method_id_490070" class="input-radio" name="payment" value="Chuyển khoản qua ngân hàng" type="radio">
                                                            </div>
                                                            <span class="radio-label-primary">Chuyển khoản qua ngân hàng</span>
                                                        </label>
                                                    </div>

                                                    <div class="radio-wrapper content-box-row content-box-row-secondary hidden" for="payment_method_id_490070">
                                                        <div class="blank-slate">
                                                            Ngân Hàng Viettin Bank 
                                                            Nguyễn Văn Hiệp
                                                            STK: 711AB5887301
                                                            Chi nhánh Cầu Giấy Hà Nội
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="step-footer">

                                <input type="hidden" name="command" value="addOrder"/>
                                <button type="submit" class="step-footer-continue-btn btn">Hoàn tất đơn hàng</button>
                                <a class="step-footer-previous-link" href="view_cart.jsp">
                                    Giỏ hàng
                                </a>

                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
