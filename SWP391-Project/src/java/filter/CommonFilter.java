/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author May Tinh Ha Anh
 */
public class CommonFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public CommonFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("CommonFilter:DoBeforeProcessing");
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
            log("CommonFilter:DoAfterProcessing");
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

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("CommonFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        String queryString = httpRequest.getQueryString();
//        System.out.println(queryString);
//        System.out.println(httpRequest.getRequestURI());
//        System.out.println("Path: "+url);
        HttpSession session = httpRequest.getSession();
        User u = (User) session.getAttribute("user");

        //Common feature
        if (u == null && url.startsWith("/userprofile")) //=================Ghep code roi sua==============
        {
            httpRequest.setAttribute("url", "?url=/project/jsp/user_profile.jsp");
            httpRequest.setAttribute("err_log", "Please login to use this feature!");
            httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
        }
        if (u == null && url.startsWith("/changepassword")) {
            httpRequest.setAttribute("url", "?url=/project/jsp/change_password.jsp");
            httpRequest.setAttribute("err_log", "Please login to use this feature!");
            httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
        }

        //Customer feature
        if (url.startsWith("/myregistration")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/my_registration.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 2)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/mycourse")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/my_course.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 2)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/quizdetail")) {
            if (u == null) {
//                httpRequest.setAttribute("queryString", httpRequest.getQueryString());
                httpRequest.setAttribute("url", "?url=/project/quizdetail?"+httpRequest.getQueryString());
//                System.out.println("?url=/project/quizdetail?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 2)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/quizresult")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/quizresult?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 2)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/registrationlist")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/registration_list.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 3 && u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/registrationdetail")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/registrationdetail?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 3 && u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/settinglist")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/setting.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/settingdetail")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/settingdetail?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/subjectdetails")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/subjectdetails?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6 && u.getRid() != 5)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/deleteregistration")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/deleteregistration?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 2)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/addnewpricepackage")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/add_new_price_package.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/updatepricepackage")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/updatepricepackage?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/addnewsetting")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/addnewsetting");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/updatesetting")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/updatesetting?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/addnewregistration")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/jsp/addnewregistration.jsp");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6 && u.getRid() != 3)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/editregistration")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/editregistration?"+httpRequest.getQueryString());
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6 && u.getRid() != 3)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        if (url.startsWith("/dashboard")) {
            if (u == null) {
                httpRequest.setAttribute("url", "?url=/project/dashboard");
                httpRequest.setAttribute("err_log", "Please login to use this feature!");
                httpRequest.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
            }
            if(u != null)
            {
                if(u.getRid() != 6 && u.getRid() != 4)
                {
                    httpResponse.sendRedirect("accessdenied");
                }
            }
        }
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
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
                log("CommonFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CommonFilter()");
        }
        StringBuffer sb = new StringBuffer("CommonFilter(");
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
