/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.GenderDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gender;
import model.User;

/**
 *
 * @author Dell
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        GenderDAO gd = new GenderDAO();
        List<Gender> genlist = gd.getAll();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        request.setAttribute("path", request.getServletPath());
        if (request.getParameter("sendMailResetPW") != null) {
            request.setAttribute("sendMailResetPW", request.getParameter("sendMailResetPW"));
        }
        request.setAttribute("genlist", genlist);
        request.getRequestDispatcher("jsp/account.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String url = request.getParameter("url");
            String e = request.getParameter("email");
            String p = request.getParameter("pass");
            String r = request.getParameter("rem");
            GenderDAO gd = new GenderDAO();
            List<Gender> genlist = gd.getAll();
            UserDAO udao = new UserDAO();
            User u = udao.checkULogin(e, p);
            String queryString = request.getQueryString();
//            System.out.println("Query string sau khi sang login:" + queryString);
            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", u);
                //====================
                //Neu dang nhap thanh cong => tao cookie
                Cookie ce = new Cookie("email", e);
                Cookie cp = new Cookie("pass", p);
                Cookie cr = new Cookie("rem", r);
                //Neu nguoi dung check - muon luu lai
                //dat time life cho cookie
                if (r != null) {
                    ce.setMaxAge(60 * 60 * 24);
                    cp.setMaxAge(60 * 60 * 24);
                    cr.setMaxAge(60 * 60 * 24);
                } else {
                    //Xoa cookie
                    ce.setMaxAge(0);
                    cp.setMaxAge(0);
                    cr.setMaxAge(0);
                }
                response.addCookie(ce);
                response.addCookie(cp);
                response.addCookie(cr);
                //====================
                if (url == null) {
                    response.sendRedirect("home");
                }
                if (url != null) {
                    System.out.println("URL sau khi sang login: " + url);
                    if (url.equals("/project/jsp/user_profile.jsp")) {
                        response.sendRedirect("/project/jsp/user_profile.jsp");
                    }
                    if (url.equals("/project/jsp/change_password.jsp")) {
                        response.sendRedirect("/project/jsp/change_password.jsp");
                    }
                    if (url.equals("/project/jsp/my_registration.jsp")) {
                        response.sendRedirect("/project/jsp/my_registration.jsp");
                    }
                    if (url.equals("/project/jsp/my_course.jsp")) {
                        response.sendRedirect("/project/jsp/my_course.jsp");
                    }
                    if (url.contains("/project/quizdetail")) {
                        response.sendRedirect(url);
                    }
                    if (url.contains("/project/quizresult")) {
                        response.sendRedirect(url);
                    }
                    if (url.equals("/project/jsp/registration_list.jsp")) {
                        response.sendRedirect("/project/jsp/registration_list.jsp");
                    }
                    if (url.contains("/project/registrationdetail")) {
                        response.sendRedirect(url);
                    }
                    if (url.equals("/project/jsp/setting.jsp")) {
                        response.sendRedirect("/project/jsp/setting.jsp");
                    }
                    if (url.contains("/project/settingdetail")) {
                        response.sendRedirect(url);
                    }
                    if (url.contains("/project/subjectdetails")) {
                        response.sendRedirect(url);
                    }
                    if (url.contains("/project/deleteregistration")) {
                        response.sendRedirect(url);
                    }
                    if (url.equals("/project/jsp/add_new_price_package.jsp")) {
                        response.sendRedirect("/project/jsp/add_new_price_package.jsp");
                    }
                    if (url.contains("/project/updatepricepackage")) {
                        response.sendRedirect(url);
                    }
                    if (url.equals("/project/addnewsetting")) {
                        response.sendRedirect("/project/addnewsetting");
                    }
                    if (url.contains("/project/updatesetting")) {
                        response.sendRedirect(url);
                    }
                    if (url.equals("/project/jsp/addnewregistration.jsp")) {
                        response.sendRedirect("/project/jsp/addnewregistration.jsp");
                    }
                    if (url.contains("/project/editregistration")) {
                        response.sendRedirect(url);
                    }
                    if (url.contains("/project/dashboard")) {
                        response.sendRedirect(url);
                    }
                }
            } else {
                request.setAttribute("err_log", "Email or password is invalid!");
                request.setAttribute("path", request.getServletPath());
                request.setAttribute("genlist", genlist); /*fix*/
                request.getRequestDispatcher("jsp/account.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
