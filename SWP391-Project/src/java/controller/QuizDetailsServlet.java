/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.QuizDAO;
import dal.RoleDAO;
import dal.SectionDAO;
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
import model.QuizList;
import model.Role;
import model.Section;
import model.Subject;

/**
 *
 * @author Dell
 */
public class QuizDetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizDetailsServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("typeac");
        String qid_raw = request.getParameter("qid");
        String pass = request.getParameter("pass"); 
        String qiddel_raw = request.getParameter("qiddel"); //del
        String name = request.getParameter("search");
        String page_raw = request.getParameter("page");
        String side = request.getParameter("side");

        //DAO area
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        CourseDAO cd = new CourseDAO();
        List<Course> listCourse = cd.getAll();
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        SectionDAO secd = new SectionDAO();
        List<Section> listSec = secd.getAll();
        QuizDAO qd = new QuizDAO();

        //pageing area
        QuizList ql = null;
        int page;
        page = page_raw == null ? 1 : Integer.parseInt(page_raw);
        if (side == null) side = "show";
        int qid = qid_raw == null ? 0 : Integer.parseInt(qid_raw);

        //set attribute area
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("typeac", type);
        request.setAttribute("searching", name);
        request.setAttribute("pass", pass);
        request.setAttribute("pageid", page);
        request.setAttribute("side", side);
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        //add action
        if (type.equals("add")) {
            request.setAttribute("listCourse", listCourse);
            request.setAttribute("listSec", listSec);
            request.getRequestDispatcher("jsp/quiz_details.jsp").forward(request, response);
        } 
        //update action
        else if (type.equals("update")) {
            ql = qd.getQuizList(qid, null, null, null, null, -1).get(0);
            listCourse = cd.getCourseBySid(ql.getSubid());
            listSec = secd.getListSectionByCourseID(ql.getCid());
            request.setAttribute("listCourse", listCourse);
            request.setAttribute("listSec", listSec);
            request.setAttribute("quizCur", ql);
            request.getRequestDispatcher("jsp/quiz_details.jsp").forward(request, response);
        }
        //del action
        if (type.equals("del")) {
            int qiddel = Integer.parseInt(qiddel_raw);
            ql = qd.getQuizList(qiddel, null, null, null, null, -1).get(0);
            qd.delQuiz(qiddel);
            response.sendRedirect("quizlist?notice=" + ql.getQname() + "&side=" + side);
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        String sub = request.getParameter("sub");
        String type = request.getParameter("typeac");
        String qid_raw = request.getParameter("qid");
        String name = request.getParameter("searching");
        String pass = request.getParameter("pass"); 
        String page_raw = request.getParameter("pageid");
        String qname = request.getParameter("qname");
        String lsub_raw = request.getParameter("listsub");
        String lc = request.getParameter("listc");
        String ls_raw = request.getParameter("lists");
        String qdesc = request.getParameter("qdesc");
        String numq = request.getParameter("numq");
        String dura = request.getParameter("dura");
        String side = request.getParameter("side");

        //DAO area
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        CourseDAO cd = new CourseDAO();
        List<Course> listCourse = cd.getAll();
        SectionDAO secd = new SectionDAO();
        List<Section> listSec = secd.getAll();
        QuizDAO qd = new QuizDAO();
        QuizList ql = null;

        //paging area
        int total;
        int page;
        int end;
        page = page_raw == null ? 1 : Integer.parseInt(page_raw);
        int lsub = Integer.parseInt(lsub_raw);
        int ls = Integer.parseInt(ls_raw);
        if (side == null) side = "show";
        int qid = qid_raw == null ? 0 : Integer.parseInt(qid_raw);
        ql = qd.getQuizList(qid, null, null, null, null, -1).get(0);
        List<QuizList> listTest = qd.getQuizList(0, null, null, null, null, -1);
        int check = 0;
         
        if (type.equals("add") || type.equals("update")) {
            if (lsub != 0) listCourse = cd.getCourseBySid(lsub);
            if (!lc.equals("0")) listSec = secd.getListSectionByCourseID(lc);
        }
        
        //add action
        if (sub != null && sub.equals("add")) {
            for (QuizList quizList : listTest)
                if (quizList.getQname().equalsIgnoreCase(qname)) check = 1;
            if (check == 1) request.setAttribute("Fai", "The name of quiz is existing!");
            else if (lsub_raw.equals("0") || lc.equals("0") || ls_raw.equals("0"))
                request.setAttribute("Fai", "You must choose full options before adding a quiz!");
            else if (qdesc == null || qdesc.length() == 0) request.setAttribute("Fai", "You must fill in the description before adding a quiz!");
            else {
                request.setAttribute("Clear", "Done");
                request.setAttribute("Suc", "You have already added a quiz with name " + qname + " successfully!.");
                Quiz q = new Quiz();
                q.setQname(qname);
                q.setQdesc(qdesc);
                q.setCid(lc);
                q.setTid(2);
                q.setSid(ls);
                qd.insertQuiz(q);
                total = name == null || name.length() == 0 ? qd.getQuizList(qid, null, null, null, null, -1).size() : 0;
                if (total > 5) {
                    end = total / 5;
                    if (end % 5 != 0 && total % 5 != 0) end += 1;
                } else end = 1;
                page = end;
            }
        }
        //update action
        if (sub != null && sub.equals("update")) {
            check = 0;
            for (QuizList quizList : listTest) 
                if (quizList.getQname().equalsIgnoreCase(qname) && !ql.getQname().equalsIgnoreCase(qname)) check = 1;
            if (check == 1) request.setAttribute("Faii", "The name of quiz is existing!");
            else if (lsub_raw.equals("0") || lc.equals("0") || ls_raw.equals("0"))
                request.setAttribute("Faii", "You must choose full options before adding a quiz!");
            else if (qdesc == null || qdesc.length() == 0) request.setAttribute("Fai", "You must fill in the description before adding a quiz!");
            else {
                request.setAttribute("Succ", "You have already updated this quiz with name " + ql.getQname() + " successfully.");
                Quiz q = new Quiz();
                q.setQid(qid);
                q.setQname(qname);
                q.setCid(lc);
                q.setSid(ls);
                q.setQdesc(qdesc);
                qd.updateQuiz(q);
            }
        }

        //set attribute area
        request.setAttribute("qname", qname);
        request.setAttribute("lsub", lsub);
        request.setAttribute("lc", lc);
        request.setAttribute("ls", ls);
        request.setAttribute("qdesc", qdesc);
        request.setAttribute("quizCur", ql);
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listSec", listSec);
        request.setAttribute("typeac", type);
        request.setAttribute("searching", name);
        request.setAttribute("pass", pass);
        request.setAttribute("pageid", page);
        request.setAttribute("side", side);
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        request.getRequestDispatcher("jsp/quiz_details.jsp").forward(request, response);
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
