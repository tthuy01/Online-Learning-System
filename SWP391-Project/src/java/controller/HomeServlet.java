/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.PostDAO;
import dal.SliderDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Course;
import model.Post;
import model.Slider;
import model.Subject;

/**
 *
 * @author Dell
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        PostDAO pd = new PostDAO();
        List<Post> listEarlyPost = pd.getTop4Posts("early");
        List<Post> listLastestPost = pd.getTop4Posts("lastest");
        CourseDAO cd = new CourseDAO();
        List<Course> listFreeCourse = cd.getTop4Courses("free");
        List<Course> listFeeCourse = cd.getTop4Courses("fee");
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listFreeCourse", listFreeCourse);
        request.setAttribute("numberFr", listFreeCourse.size());
        request.setAttribute("listFeeCourse", listFeeCourse);
        request.setAttribute("numberF", listFeeCourse.size());
        request.setAttribute("listEarlyPost", listEarlyPost);
        request.setAttribute("numberE", listEarlyPost.size());
        request.setAttribute("listLastestPost", listLastestPost);
        request.setAttribute("numberL", listLastestPost.size());
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);

        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null) {
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        }

        SliderDAO sliderdao = new SliderDAO();
        List<Slider> slider = sliderdao.getAll();
        request.setAttribute("sliders", slider);
        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);

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
