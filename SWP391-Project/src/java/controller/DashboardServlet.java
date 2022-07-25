/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.LessonDAO;
import dal.RegistrationDAO;
import dal.RoleDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Course;
import model.NewRegistration;
import model.Role;
import model.SubCateProfit;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
public class DashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
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
        // get request area
        String statby = request.getParameter("statby");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String side = request.getParameter("side");
        String viewby = request.getParameter("viewby");

        // check null variable
        if (statby == null) {
            statby = "day";
        }
        if (side == null) {
            side = "show";
        }
        if (viewby == null) {
            viewby = "0";
        }

        // set default by last 7 days
        if (from == null && to == null) {
            to = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
            from = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - 86400000 * 6);
        }

        // convert to date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> listdate = new ArrayList<>();
        try {
            Date dfrom = formatter.parse(from);
            Date dto = formatter.parse(to);
            // convert from date to string and add to listdate
            if (statby.equals("day")) {
                for (long i = dfrom.getTime(); i <= dto.getTime(); i += 86400000) {
                    listdate.add(new SimpleDateFormat("MM-dd-yyyy").format(i));
                }
            }
            if (statby.equals("month")) {
                for (long i = dfrom.getTime(); i <= dto.getTime(); i += 86400000) {
                    if (!listdate.contains(new SimpleDateFormat("MM-yyyy").format(i))) {
                        listdate.add(new SimpleDateFormat("MM-yyyy").format(i));
                    }
                }
            }
            if (statby.equals("year")) {
                for (long i = dfrom.getTime(); i <= dto.getTime(); i += 86400000) {
                    if (!listdate.contains(new SimpleDateFormat("yyyy").format(i))) {
                        listdate.add(new SimpleDateFormat("yyyy").format(i));
                    }
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // DAO area
        BlogDAO bd = new BlogDAO();
        SubjectDAO subdao = new SubjectDAO();
        CourseDAO coursedao = new CourseDAO();
        RegistrationDAO regdao = new RegistrationDAO();
        LessonDAO lesdao = new LessonDAO();
        RoleDAO roledao = new RoleDAO();
        UserDAO userdao = new UserDAO();

        // access DAO area
        List<Blog> listBlog = bd.getBlog();
        int numSub = subdao.getNumOfSub();
        int numCourse = coursedao.getNumOfCourse();
        double totalProfit = regdao.getTotalProfit();
        int numLess = lesdao.getNumOfLesson();
        List<Course> listSub = coursedao.getAllDescPublicDate(from, to);
        List<Role> listRole = roledao.getAll();
        List<SubCateProfit> listSubCatePro = subdao.getProfitBySubCate(from, to, viewby);// fix for iteration 3
        List<NewRegistration> listNewReg = regdao.getTopRegistrations(from, to);
        List<User> listUser = userdao.getTopNewUser(from, to);
        List<Integer> registereds = regdao.getNumOfRegStatus(listdate, "1", statby);
        List<Integer> submitteds = regdao.getNumOfRegStatus(listdate, "0", statby);

        // set attribute area
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("numSub", numSub);
        request.setAttribute("numCourse", numCourse);
        request.setAttribute("totalProfit", totalProfit);
        request.setAttribute("numLess", numLess);
        request.setAttribute("listSub", listSub);
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubCatePro", listSubCatePro);
        request.setAttribute("listNewReg", listNewReg);
        request.setAttribute("listUser", listUser);
        request.setAttribute("side", side);// sidebar

        // set attribute for chart
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("listdate", listdate);
        request.setAttribute("registereds", registereds);
        request.setAttribute("submitteds", submitteds);

        // set attribute for statby select option
        request.setAttribute("statby", statby);
        request.setAttribute("viewby", viewby);

        // send request
        request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
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
