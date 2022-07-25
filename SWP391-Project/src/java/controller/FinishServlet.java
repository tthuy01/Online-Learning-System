/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.QuesResult;
import model.QuizResult;
import model.User;

/**
 *
 * @author Admin
 */
public class FinishServlet extends HttpServlet {

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
            out.println("<title>Servlet FinishServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishServlet at " + request.getContextPath() + "</h1>");
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
        String qid = request.getParameter("qid");
        QuestionDAO quesdao = new QuestionDAO();
        QuizDAO quizdao = new QuizDAO();
        int check = quesdao.getTop1QuesIdByQuizId(qid);//editttttttttttttt

        int numques = quesdao.getNumQuestionByQid(qid);
        Cookie[] cookies = request.getCookies();
        String listFlag = "";
        String listans = "";
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("listFlag")) {
                listFlag = cooky.getValue();
            }
            if (cooky.getName().equals("listans")) {
                listans = cooky.getValue();
            }
        }

        String[] ans = listans.split(",");
        String[] flags = listFlag.split("[|]");
//        "1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,"   ans
//                0|0|0|0|0|0|0|0|0|0   flag

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        List<QuesResult> quesresults = new ArrayList<>();
        for (int i = 0; i < numques; i++) {
            int quesid = i + 1 + (check/10)*10;//editttttttttttttttttttttttttttttt
            boolean status;
            boolean flag;
            if (ans[i].split(":")[1].equals("0")) {
                status = false;
            } else {
                status = true;
            }
            if (flags[i].equals("1")) {
                flag = true;
            } else {
                flag = false;
            }
            quesresults.add(new QuesResult(quesid, quesid, u.getUid(),
                    status, flag, Integer.parseInt(ans[i].split(":")[1]), quizdao.countQuizResult() + 1));
        }

        DateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long start = ((long) session.getAttribute("time") - 30 * 60 * 1000);
        String qstart = obj.format(start);

        long qgrade = 0;
        List<Integer> listresult = quesdao.getQuesResultByQuizId(qid);
        for (int i = 0; i < numques; i++) {
            int answer = Integer.parseInt(ans[i].split(":")[1]);
            if (answer == listresult.get(i)) {
                qgrade += 10 / numques;
            }
        }

        long qend = new Date().getTime();

        if (qend - start <= 30 * 60 * 1000) {
            quizdao.insertQuizResult(new QuizResult(numques, Integer.parseInt(qid), u.getUid(), qgrade >= 5 ? true : false, qgrade, qstart, obj.format(qend)));//insert quiz
            for (QuesResult quesresult : quesresults) {
                quesdao.insertQuesAnswer(quesresult);//insert question 
            }
            response.sendRedirect("review?qid=" + qid + "&qrid=" + quizdao.countQuizResult());
        } else {
            response.sendRedirect("section?cid=" + quizdao.getQuizById(qid).getCid());
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
