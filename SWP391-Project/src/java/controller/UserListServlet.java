/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.RoleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserListServlet extends HttpServlet {

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
            out.println("<title>Servlet UserListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListServlet at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String gender_raw = request.getParameter("gender");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("role");
        String phone = request.getParameter("phone");
        String status_raw = request.getParameter("status");
        String sort_raw = request.getParameter("sort");
        String page_raw = request.getParameter("page");
        
        int page,gender,role,status,sort;
        if (page_raw == null) {
            page = 1;
        } else {
            page = Integer.parseInt(page_raw);
        }
        if(gender_raw == null) {
            gender = 0;
        }else {
            gender = Integer.parseInt(gender_raw);
        }
        if(role_raw == null){
            role = 0;
        }else{
            role = Integer.parseInt(role_raw);
        }
        if(status_raw==null){
            status = 2;
        }else{
            status = Integer.parseInt(status_raw);
        }
        if(sort_raw==null){
            sort = 1;
        }else{
            sort = Integer.parseInt(sort_raw);
        }
        
        UserDAO ud = new UserDAO();
        List<User> list = ud.getListUserAR(gender, role, status, name, email, phone, sort,page);
        
        int count = ud.getListUserAR(gender, role, status, name, email, phone, sort,0).size();
        int size_du = count % 5;
        int size;
        if (size_du == 0) {
            size = count / 5;
            request.setAttribute("endPage", size);
        } else {
            size = count / 5 + 1;
            request.setAttribute("endPage", size);
        }
        RoleDAO rl = new RoleDAO();
        List<Role> listRole = rl.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("name", name);
        request.setAttribute("gender", gender);
        request.setAttribute("email", email);
        request.setAttribute("role", role);
        request.setAttribute("phone", phone);
        request.setAttribute("status", status);
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);
        request.setAttribute("list", list);
        request.getRequestDispatcher("jsp/user_list.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String gender_raw = request.getParameter("gender");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("role");
        String phone = request.getParameter("phone");
        String status_raw = request.getParameter("status");
        String sort_raw = request.getParameter("sort");
        String page_raw = request.getParameter("page");
        
        int page,gender,role,status,sort;
        if (page_raw == null) {
            page = 1;
        } else {
            page = Integer.parseInt(page_raw);
        }
        if(gender_raw == null) {
            gender = 0;
        }else {
            gender = Integer.parseInt(gender_raw);
        }
        if(role_raw == null){
            role = 0;
        }else{
            role = Integer.parseInt(role_raw);
        }
        if(status_raw==null){
            status = 2;
        }else{
            status = Integer.parseInt(status_raw);
        }
        if(sort_raw==null){
            sort = 1;
        }else{
            sort = Integer.parseInt(sort_raw);
        }
        
        UserDAO ud = new UserDAO();
        List<User> list = ud.getListUserAR(gender, role, status, name, email, phone, sort,page);
        
        int count = ud.getListUserAR(gender, role, status, name, email, phone, sort,0).size();
        int size_du = count % 5;
        int size;
        if (size_du == 0) {
            size = count / 5;
            request.setAttribute("endPage", size);
        } else {
            size = count / 5 + 1;
            request.setAttribute("endPage", size);
        }
        RoleDAO rl = new RoleDAO();
        List<Role> listRole = rl.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("name", name);
        request.setAttribute("gender", gender);
        request.setAttribute("email", email);
        request.setAttribute("role", role);
        request.setAttribute("phone", phone);
        request.setAttribute("status", status);
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);
        request.setAttribute("list", list);
        request.getRequestDispatcher("jsp/user_list.jsp").forward(request, response);
        
        
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
