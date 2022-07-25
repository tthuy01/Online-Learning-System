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
import dal.QuizResultDAO;
import dal.SubjectDAO;
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
import model.Quiz;
import model.QuizResult;
import model.Subject;
import model.User;

/**
 *
 * @author May Tinh Ha Anh
 */
public class QuizResultServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizResultServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizResultServlet at " + request.getContextPath() + "</h1>");
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
        String qid_raw = request.getParameter("qid");
        int qid = 1;
        try {
            qid = Integer.parseInt(qid_raw);
        } catch (NumberFormatException e) {
        }
        QuizDAO quizdao = new QuizDAO();
        Quiz q = quizdao.getQuizById(qid_raw);
        QuizResultDAO qrdao = new QuizResultDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if(u != null && qrdao.numberDoingQuiz(qid) != 0)
        {
            List<QuizResult> list = qrdao.getListQRByQid(u.getUid(), qid);
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getQgrade()>=5)
                    count++;
            }
            
            CourseDAO cdao = new CourseDAO();
            Course c = cdao.getCourseByQid(qid);
            
            QuestionDAO qdao = new QuestionDAO();
            int numOfQuestion = qdao.getNumQuestionByQid(qid_raw);
            
            double highestGrade = qrdao.getHighestGradeByQid(qid);
            
            request.setAttribute("count", count);
            request.setAttribute("quizname", q.getQname());
            request.setAttribute("highestGrade", highestGrade);
            request.setAttribute("numOfQuestion", numOfQuestion);
            request.setAttribute("course", c);
            request.setAttribute("listqr", list);
            System.out.println("Checkkkkkkkk");
            request.getRequestDispatcher("jsp/quiz_result.jsp").forward(request, response);
        }
        
        if(u != null && qrdao.numberDoingQuiz(qid) == 0)
        {
            PrintWriter out = response.getWriter();
            out.println("<h1 style='text-align: center;'>You haven't taken Quiz "+q.getQname()+" yet</h1>");
            out.println("<h1 style='text-align: center;'><a href='#'>Click here to do "+q.getQname()+"</a></h1>");
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
