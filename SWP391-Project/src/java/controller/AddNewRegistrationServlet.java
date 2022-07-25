/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CourseDAO;
import dal.PriceDAO;
import dal.RegistrationDAO;
import dal.RoleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.Price;
import model.Registration;
import model.Role;
import model.User;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class AddNewRegistrationServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewRegistrationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewRegistrationServlet at " + request.getContextPath() + "</h1>");
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
        CourseDAO cdao = new CourseDAO();
        List<Course> listCourse = cdao.getAll();
        PriceDAO pdao = new PriceDAO();
        List<Price> listPackagePrice = pdao.getAll();
        request.setAttribute("listPackagePrice", listPackagePrice);
        request.setAttribute("listCourse", listCourse);
        request.getRequestDispatcher("jsp/addnewregistration.jsp").forward(request, response);
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
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
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        //======================Add new registration======================
        CourseDAO cdao = new CourseDAO();
        List<Course> listCourse = cdao.getAll();
        PriceDAO pdao = new PriceDAO();
        List<Price> listPackagePrice = pdao.getAll();
        request.setAttribute("listPackagePrice", listPackagePrice);
        request.setAttribute("listCourse", listCourse);
        HttpSession session = request.getSession();
        UserDAO udao = new UserDAO();
        String email = request.getParameter("email");
        String note = request.getParameter("note");
        String pid_raw = request.getParameter("pack");
        String cid = request.getParameter("course");
        System.out.println(pid_raw);
        if (email == null || pid_raw == null || cid == null || email.trim().equals("")) {
            request.setAttribute("semail", email);
            request.setAttribute("snote", note);
            request.setAttribute("spid", pid_raw);
            request.setAttribute("scid", cid);
            request.setAttribute("activePopUp", "Active pop up");
            request.setAttribute("error", "You must fill all of fields");
            request.getRequestDispatcher("jsp/addnewregistration.jsp").forward(request, response);
        }
        if (udao.checkUser(email) == null) {
            request.setAttribute("semail", email);
            request.setAttribute("snote", note);
            request.setAttribute("spid", pid_raw);
            request.setAttribute("scid", cid);
            request.setAttribute("activePopUp", "Active pop up");
            request.setAttribute("error", "Email is not exist");
            request.getRequestDispatcher("jsp/addnewregistration.jsp").forward(request, response);
        }
        int pid = 0;
        try {
            pid = Integer.parseInt(pid_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        User u = (User) session.getAttribute("user"); //Sale insert
//        System.out.println(u);
//        System.out.println(udao.checkUser(cid));
//        System.out.println(cid);
//        System.out.println(pid);
//        System.out.println(note);
        Registration newRegistration = new Registration(getCurrentTimeStamp(), note, cid, udao.checkUser(email).getUid(), 0, pid, u.getUemail());
        RegistrationDAO rdao = new RegistrationDAO();
        rdao.insert(newRegistration);
        request.setAttribute("activePopUp", "Active pop up");
        request.setAttribute("ms", "Register successfull");
        request.setAttribute("semail", email);
        request.setAttribute("snote", note);
        request.setAttribute("spid", pid_raw);
        request.setAttribute("scid", cid);
        request.getRequestDispatcher("jsp/addnewregistration.jsp").forward(request, response);
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
