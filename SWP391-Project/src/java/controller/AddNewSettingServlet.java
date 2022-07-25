/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.SettingDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Setting;
import model.SettingStatus;
import model.SettingType;
import model.Subject;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class AddNewSettingServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewSettingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewSettingServlet at " + request.getContextPath() + "</h1>");
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
        //====================Footer=========================
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        SubjectDAO sudao = new SubjectDAO();
        List<Subject> listSubject = sudao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer
        //====================Footer=========================
        SettingDAO sdao = new SettingDAO();
        List<SettingType> listAllSettingType = sdao.getAllSettingType();
        List<SettingStatus> listAllSettingStatus = sdao.getAllSettingStatus();
        request.setAttribute("listAllSettingType", listAllSettingType);
        request.setAttribute("listAllSettingStatus", listAllSettingStatus);
        request.getRequestDispatcher("jsp/add_new_setting.jsp").forward(request, response);
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
        //====================Footer=========================
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        SubjectDAO sudao = new SubjectDAO();
        List<Subject> listSubject = sudao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer
        //====================Footer=========================
        SettingDAO sdao = new SettingDAO();
        List<SettingType> listAllSettingType = sdao.getAllSettingType();
        List<SettingStatus> listAllSettingStatus = sdao.getAllSettingStatus();
        request.setAttribute("listAllSettingType", listAllSettingType);
        request.setAttribute("listAllSettingStatus", listAllSettingStatus);
        String type_raw = request.getParameter("type");
        String value = request.getParameter("value");
        String order = request.getParameter("order");
        String status_raw = request.getParameter("status");
        String des = request.getParameter("des");
        boolean check = false;
        try {
            int v = Integer.parseInt(value);
            check = true;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (type_raw.equals("0") || value == null || value.trim().equals("") || order == null || order.trim().equals("") || status_raw.equals("0") || des == null || des.trim().equals("")) {
            request.setAttribute("type", type_raw);
            request.setAttribute("value", value);
            request.setAttribute("order", order);
            request.setAttribute("status", status_raw);
            request.setAttribute("des", des);
            request.setAttribute("er", "You must fill in all fields!");
            request.getRequestDispatcher("jsp/add_new_setting.jsp").forward(request, response);
        } 
        else if(type_raw.equals("1") && check == false)
        {
            request.setAttribute("type", type_raw);
            request.setAttribute("value", value);
            request.setAttribute("order", order);
            request.setAttribute("status", status_raw);
            request.setAttribute("des", des);
            request.setAttribute("er", "Value must be a number!");
            request.getRequestDispatcher("jsp/add_new_setting.jsp").forward(request, response);
        }
        else {
            int type = 0, status = 0;
            try {
                type = Integer.parseInt(type_raw);
                status = Integer.parseInt(status_raw);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            Setting s = new Setting(0, type, value, order, status, des);
            sdao.addNewSetting(s);
            request.setAttribute("type", type_raw);
            request.setAttribute("value", value);
            request.setAttribute("order", order);
            request.setAttribute("status", status_raw);
            request.setAttribute("des", des);
            request.setAttribute("ms", "Add new setting successfully");
            request.getRequestDispatcher("jsp/add_new_setting.jsp").forward(request, response);
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
