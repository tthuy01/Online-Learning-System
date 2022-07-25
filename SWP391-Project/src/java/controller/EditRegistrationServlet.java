/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CourseDAO;
import dal.GenderDAO;
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
import model.Gender;
import model.Price;
import model.Registration;
import model.Role;
import model.User;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class EditRegistrationServlet extends HttpServlet {

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
            out.println("<title>Servlet EditRegistrationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRegistrationServlet at " + request.getContextPath() + "</h1>");
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
        GenderDAO gdao = new GenderDAO();
        List<Gender> listGender = gdao.getAll();
        request.setAttribute("listGender", listGender);
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        String rid_raw = request.getParameter("rid");
        if (rid_raw != null) {
            try {
                int rid = Integer.parseInt(rid_raw);
                RegistrationDAO rdao = new RegistrationDAO();
                Registration r = rdao.getRegByRegId(rid);
//                HttpSession session = request.getSession();
//                User u = (User) session.getAttribute("user");
                UserDAO udao = new UserDAO();
                User u = udao.getUserByUid(r.getUid());
                if (u != null) {
                    request.setAttribute("rid", rid_raw);
                    request.setAttribute("email", u.getUemail());
                    request.setAttribute("name", u.getUfullname());
                    request.setAttribute("gender", u.getGid());
                    request.setAttribute("phone", u.getUphone());
                    request.setAttribute("address", u.getUaddress());
                    request.getRequestDispatcher("jsp/edit_registration.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
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
        GenderDAO gdao = new GenderDAO();
        List<Gender> listGender = gdao.getAll();
        request.setAttribute("listGender", listGender);
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String gid_raw = request.getParameter("gender");
        String phone_raw = request.getParameter("phone");
        String address = request.getParameter("address");
        request.setAttribute("email", email);
        request.setAttribute("name", name);
        request.setAttribute("gender", gid_raw);
        request.setAttribute("phone", phone_raw);
        request.setAttribute("address", address);
        System.out.println(gid_raw);
        System.out.println(phone_raw);
        if (name.trim().equals("") || gid_raw == null || phone_raw.trim().equals("") || address.trim().equals("")) {
            request.setAttribute("error", "You must fill all of fields!");
            request.getRequestDispatcher("jsp/edit_registration.jsp").forward(request, response);
        } else {
            try {
                int gid = 0;
                long phone = 0;
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("user");
                if (u != null) {
                    RegistrationDAO rdao = new RegistrationDAO();
                    String rid_raw = request.getParameter("rid");
                    System.out.println(rid_raw);
                    int rid = 0;
                    try {
                        gid = Integer.parseInt(gid_raw);
                        phone = Long.parseLong(phone_raw.trim());
                        rid = Integer.parseInt(rid_raw);
                        UserDAO udao = new UserDAO();
                        User u1 = udao.getUserByUid(rdao.getRegByRegId(rid).getUid());
                        rdao.UpdateRegistration(u1.getUid(), rid, name, gid, phone_raw, address, u.getUemail());
                        request.setAttribute("ms", "Update registration successfully!");
                        request.getRequestDispatcher("jsp/edit_registration.jsp").forward(request, response);
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Phone is invalid!");
                        request.getRequestDispatcher("jsp/edit_registration.jsp").forward(request, response);
                    }
                }
            } catch (Exception e) {
                request.setAttribute("error", "phone is invalid!");
                request.getRequestDispatcher("jsp/edit_registration.jsp").forward(request, response);
            }
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
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
