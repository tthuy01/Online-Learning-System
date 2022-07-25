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
import model.Blog;
import model.Course;
import model.Question;
import model.QuestionList;
import model.Quiz;
import model.Role;
import model.Section;
import model.Subject;

/**
 *
 * @author Dell
 */
public class QuestionDetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet QuestionDetailsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionDetailsServlet at " + request.getContextPath() + "</h1>");
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
        String cid = request.getParameter("cid");
        String sid_raw = request.getParameter("sid");
        String qid_raw = request.getParameter("qid");
        String queid_raw = request.getParameter("queid");
        String name = request.getParameter("search");
        String page_raw = request.getParameter("page");
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
        List<Quiz> listQuiz = qd.getAll();
        QuestionDAO qued = new QuestionDAO();
        
        //pageing area
        int page;
        if (side == null) side = "show";
        page = page_raw == null ? 1 : Integer.parseInt(page_raw);
        int sid = sid_raw == null ? 0 : Integer.parseInt(sid_raw);
        int qid = qid_raw == null ? 0 : Integer.parseInt(qid_raw);
        int queid = queid_raw == null ? 0 : Integer.parseInt(queid_raw);
        QuestionList q = qued.getQuesList(queid, null, null, null, null, 0).get(0);
        String[] que_cont = q.getQue_content().split("[|]");
        int num = q.getQue_result();
        String content = que_cont[0];
        content += num==1? "b."+que_cont[2]+"; c."+que_cont[3]+"; d."+que_cont[4]+" ;":
            num==2? "a."+que_cont[1]+"; c."+que_cont[3]+"; d."+que_cont[4]+" ;":
            num==3? "a."+que_cont[1]+"; b."+que_cont[2]+"; d."+que_cont[4]+" ;":
            num==4? "a."+que_cont[1]+"; b."+que_cont[2]+"; c."+que_cont[3]+" ;":"";
        String result = num==1?"a."+que_cont[1]:num==2?"b."+que_cont[2]:num==3?"c."+que_cont[3]:num==4?"d."+que_cont[4]:"";
        listSec = secd.getListSectionByCourseID(q.getCid());
        listQuiz = qd.getListQuizBySID(q.getSid());
        
        //set attribute area
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listSec", listSec);
        request.setAttribute("listQuiz", listQuiz);
        request.setAttribute("quesCur", q);
        request.setAttribute("content", content);
        request.setAttribute("result", result);
        request.setAttribute("searching", name);
        request.setAttribute("cid", cid);
        request.setAttribute("pageid", page);
        request.setAttribute("side", side);
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        
        request.getRequestDispatcher("jsp/question_details.jsp").forward(request, response);
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
        String queid_raw = request.getParameter("queid");
        String name = request.getParameter("searching");
        String cid_raw = request.getParameter("cid");
        String page_raw = request.getParameter("pageid");
        String cid = request.getParameter("listsub");
        String les_raw = request.getParameter("listles");
        String lq_raw = request.getParameter("listq");
        String quecont = request.getParameter("cont");
        String queresult = request.getParameter("res");
        String queexplan = request.getParameter("exp");
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
        List<Quiz> listQuiz = qd.getAll();
        QuestionDAO qued = new QuestionDAO();
        QuestionList ql;
        
        //paging area
        if (side == null) side = "show";
        int page;
        page = page_raw == null ? 1:Integer.parseInt(page_raw);
        int les = les_raw==null? 0:Integer.parseInt(les_raw);
        int lq = lq_raw==null? 0:Integer.parseInt(lq_raw);
        int queid = queid_raw == null ? 0 : Integer.parseInt(queid_raw);
        ql = qued.getQuesList(queid, null, null, null, null, 0).get(0);
         
        if (type.equals("update")) {
            if (!cid.equals("0")) listSec = secd.getListSectionByCourseID(cid);
            if (les != 0) listQuiz = qd.getListQuizBySID(les);
        }
        if (sub != null && sub.equals("update")) {
            if (cid.equals("0") || les_raw.equals("0") || lq_raw.equals("0"))
                request.setAttribute("Faii", "You must choose full options before updating this question!");
            else if (quecont == null||quecont.length() == 0||queresult == null||queresult.length() == 0||queexplan == null||queexplan.length() == 0) 
                request.setAttribute("Fai", "You must fill in the content or result or explantion before updating this question!");
            else {
                String[] que_cont = quecont.split("[;]");
                int num;
                num = queresult.split("[.]")[0].equals("a")? 1:queresult.split("[.]")[0].equals("b")? 2:
                    queresult.split("[.]")[0].equals("c")? 3:queresult.split("[.]")[0].equals("d")? 4:0;
                String content = que_cont[0].split("[?]")[0] + "? |";
                content += num==1? queresult.split("[.]")[1]+"|"+que_cont[0].split("[?]")[1].split("[.]")[1]+"|"+que_cont[1].split("[.]")[1]+"|"+que_cont[2].split("[.]")[1]:
                    num==2? que_cont[0].split("[?]")[1].split("[.]")[1]+"|"+queresult.split("[.]")[1]+"|"+que_cont[1].split("[.]")[1]+"|"+que_cont[2].split("[.]")[1]:
                    num==3? que_cont[0].split("[?]")[1].split("[.]")[1]+"|"+ que_cont[1].split("[.]")[1]+"|"+queresult.split("[.]")[1]+"|"+que_cont[2].split("[.]")[1]:
                    num==4? que_cont[0].split("[?]")[1].split("[.]")[1]+"|"+ que_cont[1].split("[.]")[1]+"|"+que_cont[2].split("[.]")[1]+"|"+queresult.split("[.]")[1]:"";
                Question qq = new Question(ql.getQueid(), content, num, queexplan, lq);
                qued.updateQues(qq);
                request.setAttribute("Succ", "You have already updated this question successfully.");
            }
        }
        //set attribute area
        request.setAttribute("listsub", cid);
        request.setAttribute("listles", les);
        request.setAttribute("listq", lq);
        request.setAttribute("cont", quecont);
        request.setAttribute("res", queresult);
        request.setAttribute("exp", queexplan);
        request.setAttribute("quesCur", ql);
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listSec", listSec);
        request.setAttribute("listQuiz", listQuiz);
        request.setAttribute("searching", name);
        request.setAttribute("cid", cid_raw);
        request.setAttribute("pageid", page);
        request.setAttribute("side", side);
        request.getRequestDispatcher("jsp/question_details.jsp").forward(request, response);
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
