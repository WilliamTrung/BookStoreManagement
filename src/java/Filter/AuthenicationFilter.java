
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package Filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Users.UserDTO;

/**
 *
 * @author WilliamTrung
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthenicationFilter implements Filter {

    private final List<String> USER;
    private final List<String> ADMIN;
    private final List<String> FREE;
    private final String AD = "ad";
    private final String US = "us";
    private final String LOGIN = "welcomePage.jsp";

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public AuthenicationFilter() {
        USER = new ArrayList<>();
        USER.add("");
        USER.add("headerUser.jsp");
        USER.add("CheckoutController");
        USER.add("updateSelf.jsp");
        USER.add("viewHistoryPurchase.jsp");
        USER.add("checkout.jsp");
        USER.add("LoadOrderDetailsController"); 
        USER.add("LoadOrderController"); 
        
        ADMIN = new ArrayList<>();
        ADMIN.add("");

        ADMIN.add("SearchController");
        ADMIN.add("CreateProductController");
        ADMIN.add("createProduct.jsp");
        ADMIN.add("SearchProductController");
        ADMIN.add("updateSelf.jsp");
        ADMIN.add("updateUser.jsp");
        ADMIN.add("viewHistoryPurchaseAdmin.jsp");
        ADMIN.add("adminUserManagement.jsp");
        ADMIN.add("adminUserChange.jsp");
        ADMIN.add("adminProductManagement.jsp");
        ADMIN.add("adminProductChange.jsp");
        ADMIN.add("headerAdmin.jsp");
        ADMIN.add("ChangeStatusController");
        ADMIN.add("ChangeStatusProductController");
        ADMIN.add("UpdateController");
        ADMIN.add("LoadOrderDetailsController"); 
        ADMIN.add("LoadOrderController"); 
        FREE = new ArrayList<>();
        
        FREE.add("");
        FREE.add("header.jsp");
        FREE.add("register.jsp");
        FREE.add("viewCart.jsp");
        FREE.add("shopping.jsp");
        FREE.add("viewCart.jsp");
        FREE.add("error.jsp");
        FREE.add("AddToCartController");
        FREE.add("RegisterController");
        FREE.add("UpdateCartController");
        FREE.add("RemoveItemCartController");
        FREE.add("LoadItemCartController");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        //chain.doFilter(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.contains(".jpg") || uri.contains(".gif") || uri.contains(".png")) {
            chain.doFilter(request, response);
        } else {
            if (uri.contains("welcomePage.jsp") || uri.contains("MainController") || uri.contains("LoginController")||uri.contains("LogoutController")) {
                chain.doFilter(request, response);
                return;
            }
            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            HttpSession session = req.getSession();
            if (FREE.contains(resource)) {
                chain.doFilter(request, response);
            } else if (session == null || session.getAttribute("LOGIN_USER") == null) {
                res.sendRedirect(LOGIN);
            } else {
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                String role = loginUser.getRole();
                if (AD.equals(role) && ADMIN.contains(resource)) {
                    chain.doFilter(request, response);
                } else if (US.equals(role) && USER.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(LOGIN);
                }
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
