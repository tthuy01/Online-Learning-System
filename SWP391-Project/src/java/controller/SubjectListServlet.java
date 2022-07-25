/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.ManageCourseDAO;
import dal.RoleDAO;
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
import model.ManageCourse;
import model.Role;
import model.Subject;
import model.TotalLesson;
import model.User;

/**
 *
 * @author Administrator
 */
public class SubjectListServlet extends HttpServlet {

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
            out.println("<title>Servlet SubjectListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectListServlet at " + request.getContextPath() + "</h1>");
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
    private boolean ischeck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");
        if (u == null) {
            request.setAttribute("ReturnUrl", "http://localhost:8080/project/subjectlist");
            request.getRequestDispatcher("login").forward(request, response);
        }
        if (u.getRid() != 5 && u.getRid() != 6) {
            request.getRequestDispatcher("home").forward(request, response);
        }
        String index = request.getParameter("page");
        String[] sid_raw = request.getParameterValues("sid");
        String[] cstatus_raw = request.getParameterValues("cstatus");
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
        CourseDAO cd = new CourseDAO();
        SubjectDAO sd = new SubjectDAO();
        ManageCourseDAO mcd = new ManageCourseDAO();
        UserDAO ud = new UserDAO();
        BlogDAO bd = new BlogDAO();
        try {
            //Paging
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);
            //Checkbox
            List<Subject> listS = sd.getAll();
            int[] sid = new int[1000];
            int[] cstatus = new int[1000];

            if (sid_raw != null) {
                sid = new int[sid_raw.length];
                for (int i = 0; i < sid.length; i++) {
                    sid[i] = Integer.parseInt(sid_raw[i]);
                }
            } else {
                for (int i = 0; i < listS.size(); i++) {
                    sid[i] = listS.get(i).getSid();
                }
            }

            int[] listStatus = {0, 1}; //Unpublished or Published  
            if (cstatus_raw != null) {
                cstatus = new int[cstatus_raw.length];
                for (int i = 0; i < cstatus.length; i++) {
                    cstatus[i] = Integer.parseInt(cstatus_raw[i]);
                }
            } else {
                for (int i = 0; i < listStatus.length; i++) {
                    cstatus[i] = listStatus[i];
                }
            }

            List<Course> listC = cd.checkBoxCourseBySidAndPaging(sid, cstatus, search, u.getRid() == 5 ? u.getUid() : 0, indexPage);

            boolean[] sidArr = new boolean[listS.size()];
            for (int i = 0; i < sidArr.length; i++) {
                if (ischeck(listS.get(i).getSid(), sid)) {
                    sidArr[i] = true;
                } else {
                    sidArr[i] = false;
                }
            }
            if (sid_raw != null) {
                request.setAttribute("sid", sidArr);
            }

            boolean[] cstatusArr = new boolean[listStatus.length];
            for (int i = 0; i < cstatusArr.length; i++) {
                if (ischeck(listStatus[i], cstatus)) {
                    cstatusArr[i] = true;
                } else {
                    cstatusArr[i] = false;
                }
            }
            if (cstatus_raw != null) {
                request.setAttribute("cstatus", cstatusArr);
            }

            //Checkbox
            int totalCourse = cd.checkBoxCourseBySid(sid, cstatus, search, u.getRid() == 5 ? u.getUid() : 0).size();
            int endPage = totalCourse / 5;
            if (totalCourse % 5 != 0) {
                endPage++;
            }
            //Paging

            List<TotalLesson> listTL = sd.totalLesson();
            List<ManageCourse> listMC = mcd.getAll();
            List<Blog> listBlog = bd.getBlog();
            List<User> listU = ud.getAll();

            request.setAttribute("tag", indexPage);
            request.setAttribute("endPage", endPage);
            request.setAttribute("totalCourse", totalCourse);
            request.setAttribute("listCourse", listC);
            request.setAttribute("listSubject", listS);
            request.setAttribute("listTotalLesson", listTL);
            request.setAttribute("listManageCourse", listMC);
            request.setAttribute("listUser", listU);
            request.setAttribute("search", search);
            request.setAttribute("listSearchCname", cd.checkBoxCourseBySid(sid, cstatus, search, u.getRid() == 5 ? u.getUid() : 0));
            request.setAttribute("listBlog", listBlog);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

//        ------------------hide side--------------
        if(request.getParameter("side") == null) request.setAttribute("side", "show");
        
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("jsp/subject_list.jsp").forward(request, response);
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
