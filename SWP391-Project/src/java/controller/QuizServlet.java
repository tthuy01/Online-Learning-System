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
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.Question;
import model.Quiz;

/**
 *
 * @author Admin
 */
public class QuizServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizServlet at " + request.getContextPath() + "</h1>");
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
        String cid = request.getParameter("cid");
        String qid = request.getParameter("qid");
        String quesid = request.getParameter("quesid");
        String answer = request.getParameter("answer");
        String previous = request.getParameter("previous");
        QuestionDAO quesdao = new QuestionDAO();
        
        int check = quesdao.getTop1QuesIdByQuizId(qid);//editttttttt
        if (quesid == null) {
            quesid = String.valueOf(check%10);//edittttttttttttt
        }

        if (answer == null || answer.equals("")) {
            answer = "0";
        }

        int pre;
        if (previous == null) {
            pre = 0;
        } else {
            pre = Integer.parseInt(previous);
        }

        CourseDAO cd = new CourseDAO();
        Course course = cd.getCourseById(cid);

        QuizDAO quizdao = new QuizDAO();
        Quiz quiz = quizdao.getQuizById(qid);

        Question ques = quesdao.getQuestionById(String.valueOf(Integer.parseInt(quesid)+(check/10)*10));// edittttttt
        int numques = quesdao.getNumQuestionByQid(qid);

        String listans = "";
        String listFlag = "";

        if (pre == 0) {
            for (int j = 1; j <= numques; j++) {
                listans += j + ":0,";
                listFlag += "0";
                if (j != numques) {
                    listFlag += "|";
                }
            }
            HttpSession session = request.getSession();
            session.setAttribute("time", new Date().getTime() + 30 * 60 * 1000);
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("listans")) {
                    String list = cooky.getValue();
                    String[] listnew = list.split(",");
                    for (int i = 0; i < listnew.length; i++) {
                        if (pre - 1 == i) {
                            listans += listnew[i].split(":")[0] + ":" + answer + ",";
                        } else {
                            listans += listnew[i] + ",";
                        }
                    }
                }
            }
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("listFlag")) {
                    listFlag = cooky.getValue();
                }
            }
        }

        Cookie cookie = new Cookie("listans", listans);
        Cookie flags = new Cookie("listFlag", listFlag);
        cookie.setMaxAge(60 * 60 * 24);
        flags.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        response.addCookie(flags);

        request.setAttribute("course", course);
        request.setAttribute("quiz", quiz);
        request.setAttribute("ques", ques);
        request.setAttribute("numques", numques);
        request.setAttribute("quesid", quesid);
//        request.setAttribute("answer", answer);
        request.setAttribute("listans", listans);
        request.setAttribute("listFlag", listFlag);

        request.setAttribute("listSubject", new SubjectDAO().getAll());
        request.setAttribute("listBlog", new BlogDAO().getBlog());
        request.getRequestDispatcher("jsp/quiz.jsp").forward(request, response);

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
