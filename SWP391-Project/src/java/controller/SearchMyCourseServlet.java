/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.Subject;
import model.User;

/**
 *
 * @author May Tinh Ha Anh
 */
public class SearchMyCourseServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchMyCourseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchMyCourseServlet at " + request.getContextPath() + "</h1>");
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
        String search = request.getParameter("search");
        String[] subCate_raw = request.getParameterValues("subCate");
        if (search == null) {
            search = ""; //Tim tat ca cac subject
        }

        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        SubjectDAO sdao = new SubjectDAO();
        List<Subject> listSubject = sdao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u != null) {
            UserDAO udao = new UserDAO();
            String role = udao.getRole(u);
            if (role.toLowerCase().equals("Customer".toLowerCase())) {
                CourseDAO cdao = new CourseDAO();
                List<Course> listMyCourseWPaging = cdao.searchMyCourse(u.getUid(), search);

                int page, cPerPage = 4;
                int size = listMyCourseWPaging.size(); //Tong so my course
                int num = size % 4 == 0 ? size / 4 : ((size / 4) + 1); //So trang (page)
                String xPage = request.getParameter("page");
                if (xPage == null) {
                    page = 1;
                } else {
                    try {
                        page = Integer.parseInt(xPage);
                    } catch (NumberFormatException e) {
                        page = 1;
                    }
                }
                int start, end;
                start = (page - 1) * cPerPage; //Lay ra phan tu dau tien cua trang
                end = Math.min(page * cPerPage, size); //Lay phan tu cuoi cung cua trang
                List<Course> listMyCourse = cdao.getListCourseByPage(listMyCourseWPaging, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);

                request.setAttribute("listMyCourse", listMyCourse);
                request.setAttribute("search", search);
                request.getRequestDispatcher("jsp/my_course.jsp").forward(request, response);
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
