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
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.QuestionList;
import model.Role;
import model.Section;
import model.Subject;
import model.User;

/**
 *
 * @author Dell
 */
public class QuestionListServlet extends HttpServlet {

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
            out.println("<title>Servlet QuestionListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionListServlet at " + request.getContextPath() + "</h1>");
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
    boolean isCheck(int d, int[] id) {
        if (id == null) {
            return false;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == d) {
                return true;
            }
        }
        return false;
    }
    int[] sub(String[] arr) {
        int[] sub = new int[arr.length];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = Integer.parseInt(arr[i]);
        }
        return sub;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
	//check user login
        HttpSession s = request.getSession();
        User u = (User) s.getAttribute("user");
        if (u == null || (u.getRid() != 5 && u.getRid() != 6)) {
            request.getRequestDispatcher("login").forward(request, response);
        }
        else {
        String name = request.getParameter("search");
        String oldSub = request.getParameter("oldsub");
        String subId = request.getParameter("subid");
        String[] secId = request.getParameterValues("secid");
        String[] qStatus = request.getParameterValues("status");
        String page_raw = request.getParameter("pageid");
        String side = request.getParameter("side");
        String err = request.getParameter("err");

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
        QuizDAO qdd = new QuizDAO();
        QuestionDAO qd = new QuestionDAO();
        int[] listStatus = {0, 1};
        List<QuestionList> listQues = null;
        List<Section> listSec = null;

        //filter & paging area
        if (side == null) side = "show";
        boolean[] sec1, sta1 = null;
        int[] sec, sta;
        int total, page, end;
        page = page_raw == null ? 1 : Integer.parseInt(page_raw);
        subId = subId == null? "CP":subId;
        sec = secId!=null? sub(secId):null;
        sta = qStatus != null ? sub(qStatus) : null;
        request.setAttribute("listQuiz", qdd.getListQuizByCID(subId));
        listSec = secd.getListSectionByCourseID(subId);
        sec1 = new boolean[listSec.size()];
        for (int i = 0; i < sec1.length; i++) sec1[i] = isCheck(listSec.get(i).getSid(), sec);
        if (secId != null) request.setAttribute("secid", sec1);
        sta1 = new boolean[listStatus.length];
        for (int i = 0; i < sta1.length; i++) sta1[i] = isCheck(listStatus[i], sta);
        if (qStatus != null) request.setAttribute("qsta", sta1);
        
        //list quiz & size of list quiz
        if (oldSub!=null&&!oldSub.equals(subId)) secId = null;
        if (name == null || name.length() == 0) {
            listQues = qd.getQuesList(0, null, subId, secId, qStatus, page);
            total = qd.getQuesList(0, null, subId, secId, qStatus, 0).size();
        } else {
            listQues = qd.getQuesList(0, name, subId, secId, qStatus, page);
            total = qd.getQuesList(0, name, subId, secId, qStatus, 0).size();
        }
        if (total > 5) {
            end = total / 5;
            if (end % 5 != 0 && total % 5 != 0) end += 1;
        } else end = 1;
        if ((name!=null&&name.length()>0)||!subId.equals("CP")||secId!=null||qStatus!=null) request.setAttribute("Clear", "clear");

        //set attribute area
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listSec", listSec);
        request.setAttribute("listQues", listQues);
        request.setAttribute("side", side);
        request.setAttribute("err", err);
        request.setAttribute("subid", subId);
        request.setAttribute("searching", name);
        request.setAttribute("total", total);
        request.setAttribute("end", end);
        request.setAttribute("page", page);
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        
        request.getRequestDispatcher("jsp/question_list.jsp").forward(request, response);
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
