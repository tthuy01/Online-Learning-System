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
import javax.servlet.http.HttpSession;
import model.Blog;
import model.QuestionMix;
import model.QuizMix;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
public class ReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet ReviewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewServlet at " + request.getContextPath() + "</h1>");
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
        
        HttpSession ses = request.getSession();
        User un = (User) ses.getAttribute("user");
        if (un == null || un.getRid()!= 2) {
            request.getRequestDispatcher("login").forward(request, response);
        }
        else {
        String qid = request.getParameter("qid");
        String qrid = request.getParameter("qrid");
        String quesid = request.getParameter("quesid");
//        String prev = request.getParameter("prev");
        CourseDAO cd = new CourseDAO();
        QuizDAO quid = new QuizDAO();
        QuestionDAO quesd = new QuestionDAO();
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        QuizMix quimix = quid.getInfoBy2Id(qid, qrid);
        List<QuestionMix> listques = quesd.getAllInfoBy2Id(qid, qrid);

        int ques_cur = quesd.getTop1QuesIdByQuizId(qid);
        if (quesid == null) {
            quesid = String.valueOf(ques_cur);
        }
//        int pre;
//        if (prev == null) {
//            pre = 0;
//        } else {
//            pre = Integer.parseInt(prev);
//        }
        QuestionMix quesmix = quesd.getInfoBy3Id(qid, qrid, quesid);
        /*fix*/
        String timeTaken = quid.getTimeById(qrid).split("[|]")[0];
        String time = timeTaken.split(":")[1] + " minutes " + timeTaken.split(":")[2].split("[.]")[0] + " seconds.";
        String dayTakenSta = quid.getTimeById(qrid).split("[|]")[1];
        String daySta = dayTakenSta.split(" ")[0].split("-")[2] + "/" + dayTakenSta.split(" ")[0].split("-")[1] + "/" + dayTakenSta.split(" ")[0].split("-")[0];
        String hourSta = dayTakenSta.split(" ")[1].split(":")[0] + ":" + dayTakenSta.split(" ")[1].split(":")[1] + ":" + dayTakenSta.split(" ")[1].split(":")[2].split("[.]")[0]
                + (Integer.parseInt(dayTakenSta.split(" ")[1].split(":")[0]) > 12 ? " PM" : " AM");
        String dayTakenFin = quid.getTimeById(qrid).split("[|]")[2];
        String dayFin = dayTakenFin.split(" ")[0].split("-")[2] + "/" + dayTakenFin.split(" ")[0].split("-")[1] + "/" + dayTakenFin.split(" ")[0].split("-")[0];
        String hourFin = dayTakenFin.split(" ")[1].split(":")[0] + ":" + dayTakenFin.split(" ")[1].split(":")[1] + ":" + dayTakenFin.split(" ")[1].split(":")[2].split("[.]")[0]
                + (Integer.parseInt(dayTakenFin.split(" ")[1].split(":")[0]) > 12 ? " PM" : " AM");

        int numques = listques.size();
        int mark = quesd.getAllAnsweredBy2Id(qid, qrid);
        String grade = String.format("%,.2f", quimix.getQgrade());
        String percent = String.format("%,.2f", quimix.getQgrade() * 10);

        /*fix*/
        request.setAttribute("quimix", quimix);
        request.setAttribute("quesmix", quesmix);
        request.setAttribute("listques", listques);
        request.setAttribute("numques", numques);
        request.setAttribute("start", daySta + ", " + hourSta);
        request.setAttribute("end", dayFin + ", " + hourFin);
        request.setAttribute("time", time);
        request.setAttribute("mark", mark);
        request.setAttribute("grade", grade);
        request.setAttribute("percent", percent);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);

        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        
        request.getRequestDispatcher("jsp/quiz_review.jsp").forward(request, response);
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
