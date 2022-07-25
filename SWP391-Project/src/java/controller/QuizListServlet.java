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
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import model.Subject;
import model.User;

/**
 *
 * @author Dell
 */
public class QuizListServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizListServlet at " + request.getContextPath() + "</h1>");
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
    boolean isCheck2(String d, String[] id) {
        if (id == null) {
            return false;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i].equals(d)) {
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
        String notice = request.getParameter("notice");
        String qid_raw = request.getParameter("qid");
        String name = request.getParameter("search");
        String[] subId = request.getParameterValues("subid");
        String[] qStatus = request.getParameterValues("status");
        String qSort = request.getParameter("sort");
        String page_raw = request.getParameter("pageid");
        String side = request.getParameter("side");

        //DAO area
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        CourseDAO cd = new CourseDAO();
        List<Course> listCourse = cd.getAll();
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        QuizDAO qd = new QuizDAO();
        int[] listStatus = {0, 1};

        //filter & paging area
        List<QuizList> listQuiz = null;
        int qid = qid_raw == null ? 0 : Integer.parseInt(qid_raw);
        int[] sub;
        boolean[] sub1 = null;
        int[] sta;
        boolean[] sta1 = null;
        int total;
        int page;
        int end;
        page = page_raw == null ? 1 : Integer.parseInt(page_raw);
        sta = qStatus != null ? sub(qStatus) : null;
//        sub = subId != null ? sub(subId) : null;
//        sub1 = new boolean[listSubject.size()];
//        for (int i = 0; i < sub1.length; i++)
//            sub1[i] = isCheck(listSubject.get(i).getSid(), sub);
        /*fix*/
        sub1 = new boolean[listCourse.size()];
        for (int i = 0; i < sub1.length; i++)
            sub1[i] = isCheck2(listCourse.get(i).getCid(), subId);
        if (subId != null) request.setAttribute("sid", sub1);
        sta1 = new boolean[listStatus.length];
        for (int i = 0; i < sta1.length; i++) 
            sta1[i] = isCheck(listStatus[i], sta);
        if (qStatus != null) request.setAttribute("qsta", sta1);

        //list quiz & size of list quiz
        if (name == null || name.length() == 0) {
            listQuiz = qd.getQuizList(0, null, subId, qStatus, qSort, page);
            total = qd.getQuizList(0, null, subId, qStatus, qSort, -1).size();
        } else {
            listQuiz = qd.getQuizList(0, name, subId, qStatus, qSort, page);
            total = qd.getQuizList(0, name, subId, qStatus, qSort, -1).size();
        }
        if (total > 5) {
            end = total / 5;
            if (end % 5 != 0 && total % 5 != 0) end += 1;
        } else end = 1;
        if (side == null) side = "show";
        /*fix*/
        if ((name!=null&&name.length()>0)||subId!=null||qSort!=null||qStatus!=null) request.setAttribute("Clear", "clear");

        //set attribute area
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listQuiz", listQuiz);
        if (notice != null)
            request.setAttribute("suc", "You have already deleted a quiz with name "+notice+" successfully.");
        request.setAttribute("qsor", qSort);
        request.setAttribute("searching", name);
        request.setAttribute("total", total);
        request.setAttribute("page", page);
        request.setAttribute("end", end);
        request.setAttribute("side", side);
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        
        request.getRequestDispatcher("jsp/quiz_list.jsp").forward(request, response);
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
