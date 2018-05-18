package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.DecimalFormat;
import object.OrderObject;
import java.util.ArrayList;
import order.OrderControl;
import cp.ConnectionPoolImpl;
import cp.ConnectionPool;

public final class view_005fdashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Dashboard</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"backend/css/bootstrap.min.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"backend/ckeditor/ckeditor.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        ");

            ConnectionPool cp = new ConnectionPoolImpl();
            OrderControl oc = new OrderControl(cp);
            int total = oc.getOrderCount();
            ArrayList<OrderObject> items = oc.getOrderObject(null, (short) 1, (byte) total);
            DecimalFormat df = new DecimalFormat("###,###,###");
        
      out.write("\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Tổng doanh thu</div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        ");

                            int doanhthu = 0;
                            ArrayList<Integer> price = null;
                            for (OrderObject oo : items) {
                                price = new ArrayList<Integer>();
                                doanhthu = oo.getOrder_price();
                                price.add(doanhthu);
                            }
                            System.out.println(price.size());
                        
      out.write("  \n");
      out.write("                        ");
      out.print(df.format(doanhthu));
      out.write("₫\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Tổng đơn hàng</div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        ");
      out.print(total);
      out.write(" đơn hàng\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Đơn hàng có giá trị lớn nhất</div>\n");
      out.write("                    ");

                        int max =0;
                        for(int i=0;i<price.size();i++){
                            if(max<price.get(i)){
                                max=price.get(i);
                            }else{
                                max=max;
                            }
                        }
                        OrderObject ooMax = oc.getOrderObjectPrice(max);
                    
      out.write("\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        Mã đơn hàng: #");
      out.print(ooMax.getOrder_id());
      out.write("\n");
      out.write("                        <br>\n");
      out.write("                        Tổng giá trị đơn hàng: ");
      out.print(df.format(ooMax.getOrder_price()));
      out.write("₫\n");
      out.write("                        <br>\n");
      out.write("                        Tên khách hàng: ");
      out.print(ooMax.getOrder_fullname_customer());
      out.write("\n");
      out.write("                        <br>\n");
      out.write("                        Số điện thoại: ");
      out.print(ooMax.getOrder_phone());
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Đơn hàng có giá trị nhỏ nhất</div>\n");
      out.write("                    ");

                        int min =0;
                        for(int i=0;i<price.size();i++){
                            if(price.get(0)<price.get(i)){
                                min = price.get(0);
                            }else{
                                min = price.get(i);
                            }
                        }
                        
                    
      out.write("\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        Mã đơn hàng: ");
      out.print(min);
      out.write("\n");
      out.write("                        <br>\n");
      out.write("                        Tổng giá trị đơn hàng: \n");
      out.write("                        <br>\n");
      out.write("                        Tên khách hàng: \n");
      out.write("                        <br>\n");
      out.write("                        Số điện thoại: \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Đơn hàng bán được nhiều sản phẩm nhất</div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        Mã sản phẩm: \n");
      out.write("                        <br>\n");
      out.write("                        Mã đơn hàng: \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
