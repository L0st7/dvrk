package org.apache.jsp.fontend;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class view_005fhome_005fpage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>DVRK CLOTHING </title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            session.setAttribute("DISPLAY", "none");
        
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header_menu.jsp", out, false);
      out.write("\n");
      out.write("            <div id=\"Wrapper\">\n");
      out.write("                <div id=\"WrapInner\">\n");
      out.write("                    <div id=\"ContentOuter\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <style type=\"text/css\">\n");
      out.write("                            .no-touch #Header.scrolled {\n");
      out.write("                                background: none !important; \n");
      out.write("                            }\n");
      out.write("                        </style>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"HomeSlides\" class=\"flexslider full_screen\">\n");
      out.write("                <ul class=\"slides\"></ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"pullup-section\">\n");
      out.write("                <div id=\"homepage-single-image\">\n");
      out.write("                    <div id=\"homepage-single-image-container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"homepage-image-grid\">\n");
      out.write("                    <div id=\"homepage-image-grid-container\">\n");
      out.write("                        <div class=\"image-grid-row-one\">\n");
      out.write("                            <div class=\"image-grid-image igi-one\">\n");
      out.write("                                <a href=\"#\">\n");
      out.write("                                    <img src=\"theme.hstatic.net/1000178923/1000285707/14/images_grid_two_one_image_onece31.png?v=19\">\n");
      out.write("                                    <div class=\"image-text\">\n");
      out.write("                                        <span class=\"image-title\">\" Spring 2018 \"</span>\n");
      out.write("                                        <span class=\"image-desc\"></span>\n");
      out.write("                                    </div>\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"image-grid-image igi-two\">\n");
      out.write("                                <a href=\"#\">\n");
      out.write("                                    <img src=\"theme.hstatic.net/1000178923/1000285707/14/images_grid_two_one_image_twoce31.png?v=19\">\n");
      out.write("                                    <div class=\"image-text\">\n");
      out.write("                                        <span class=\"image-title\">\" I see it coming \"</span>\n");
      out.write("                                        <span class=\"image-desc\"></span>\n");
      out.write("                                    </div>\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
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
