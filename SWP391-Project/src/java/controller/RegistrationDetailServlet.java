/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.GenderDAO;
import dal.RegistrationDAO;
import dal.RegistrationStatusDAO;
import dal.RoleDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Course;
import model.Gender;
import model.Registration;
import model.RegistrationStatus;
import model.Role;
import model.Subject;
import model.User;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class RegistrationDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistrationDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationDetailServlet at " + request.getContextPath() + "</h1>");
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
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        String rid_raw = request.getParameter("rid");
        int rid = 0;
        try {
            rid = Integer.parseInt(rid_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        RegistrationDAO rdao = new RegistrationDAO();
        Registration r = rdao.getRegByRegId(rid);
        CourseDAO cdao = new CourseDAO();
        Course c = cdao.getCourseById(r.getCid());
        SubjectDAO sdao = new SubjectDAO();
        Subject s = sdao.getSubByCourseId(r.getCid());
        double costSale = rdao.getTotalPrice(r);
        double orPrice = rdao.getTotalPrice(r) / 0.8;
        UserDAO udao = new UserDAO();
        User u = udao.getUserByUid(r.getUid());
        String validTo = rdao.getValidToByRid(r);
        GenderDAO gdao = new GenderDAO();
        Gender g = gdao.getGenderByGenId(u.getGid());
        RegistrationStatusDAO rsdao = new RegistrationStatusDAO();
        RegistrationStatus rs = rsdao.getRegStatusByRsid(r.getRstatusId());

        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        List<Subject> listSubject = sdao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer

        request.setAttribute("registrationstatus", rs);
        request.setAttribute("gender", g);
        request.setAttribute("course", c);
        request.setAttribute("registration", r);
        request.setAttribute("subject", s);
        request.setAttribute("costSale", costSale);
        request.setAttribute("orPrice", orPrice);
        request.setAttribute("user", u);
        request.setAttribute("validTo", validTo);
        request.getRequestDispatcher("jsp/registration_detail.jsp").forward(request, response);
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
        processRequest(request, response);
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
