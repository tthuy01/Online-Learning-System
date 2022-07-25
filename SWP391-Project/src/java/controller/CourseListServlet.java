/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.LecturerDAO;
import dal.LevelDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Course;
import model.Lecturer;
import model.Level;
import model.Subject;

/**
 *
 * @author Admin
 */
public class CourseListServlet extends HttpServlet {

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
            out.println("<title>Servlet CourseListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseListServlet at " + request.getContextPath() + "</h1>");
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
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        CourseDAO cd = new CourseDAO();
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        LevelDAO ld = new LevelDAO();
        List<Level> listLevel = ld.getAll();
        LecturerDAO lecd = new LecturerDAO();
        List<Lecturer> listLec = lecd.getAll();
        double maxPrice = cd.getMaxPrice();

        String search = request.getParameter("search");
        String subject = request.getParameter("subject");
        String level = request.getParameter("level");
        String lecturer = request.getParameter("lecturer");
        String price = request.getParameter("price");
        String cpublic = request.getParameter("public");
//Paging-------------------------------------------------------------------------------
        String indexRaw = request.getParameter("index");
        int pageSize = 4;
        int index;
        if (indexRaw == null) {
            index = 1;
        } else {
            index = Integer.parseInt(indexRaw);
        }
        List<Course> listC = cd.getCourseByFilter(subject, level, lecturer, price, cpublic, search, index, pageSize);
        
        int size = cd.getCourseNumber(subject, level, lecturer, price, search);
        if (size == 0) {
            request.setAttribute("error", "NOT FOUND");
        } else {
            int endPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
            request.setAttribute("listCourse", listC);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
        }
//Paging------------------------------------------------------------------------------
        request.setAttribute("subject", subject);
        request.setAttribute("level", level);
        request.setAttribute("lecturer", lecturer);
        request.setAttribute("price", price);
        request.setAttribute("public", cpublic);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("search", search);
        request.setAttribute("size", size);

        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listLevel", listLevel);
        request.setAttribute("listLec", listLec);
        request.setAttribute("listBlog", listBlog);

        request.setAttribute("listF", cd.getFeaturedCourse());
        
        //userprofile
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));

        request.getRequestDispatcher("jsp/course_list.jsp").forward(request, response);
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
