/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
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
import model.Quiz;
import model.Subject;

/**
 *
 * @author May Tinh Ha Anh
 */
public class QuizDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizDetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizDetailServlet at " + request.getContextPath() + "</h1>");
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
        //==============Thanh=====================
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        //==============Thanh=====================
        //====================Footer=========================
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        SubjectDAO sudao = new SubjectDAO();
        List<Subject> listSubject = sudao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer
        //====================Footer=========================
        QuizDAO qd = new QuizDAO();
        String qid_raw = request.getParameter("qid");
//        int qid = 1;
//        try {
//            qid = Integer.parseInt(qid_raw);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        Quiz q = qd.getQuizById(qid_raw);
        request.setAttribute("quizInfo", q);
        QuestionDAO qdao = new QuestionDAO();
        CourseDAO cdao = new CourseDAO();
        Course c = cdao.getCourseById(q.getCid());
        int numOfQuestion = qdao.getNumQuestionByQid(qid_raw);
        request.setAttribute("numOfQuestion", numOfQuestion);
        request.setAttribute("course", c);
        
        request.getRequestDispatcher("jsp/quiz_detail.jsp").forward(request, response);
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
