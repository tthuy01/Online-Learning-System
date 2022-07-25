/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.QuizDAO;
import dal.RoleDAO;
import dal.SectionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Lesson;
import model.Quiz;
import model.Role;
import model.Section;

/**
 *
 * @author ADMIN
 */
public class SubjectLessonServlet extends HttpServlet {

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
            out.println("<title>Servlet SubjectLessonServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectLessonServlet at " + request.getContextPath() + "</h1>");
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
        String sectionselect_raw = request.getParameter("sectionselect");
        String statusselect_raw = request.getParameter("statusselect");
        String id_raw = request.getParameter("id");
        String type_raw = request.getParameter("type");
        String active_raw = request.getParameter("active");

        int sectionselect, statusselect;
        int id, type;
        boolean active;

        if (id_raw == null) {
            id = 0;
        } else {
            id = Integer.parseInt(id_raw);
        }
        if (type_raw == null) {
            type = 0;
        } else {
            type = Integer.parseInt(type_raw);
        }
        if (active_raw == null) {
            active = true;
        } else {
            active = Boolean.parseBoolean(active_raw);
        }

        SectionDAO sd = new SectionDAO();
        LessonDAO ld = new LessonDAO();
        QuizDAO qd = new QuizDAO();
        CourseDAO cd = new CourseDAO();

        switch (type) {
            case 1:
                Lesson l = ld.getLessonByID(id);
                Lesson lesson = new Lesson(l.getLid(), l.getLname(), l.getLvideo(), l.getCid(), l.getSid(), l.getTid(), l.getLdesc(), active);
                ld.updateLessonAR(lesson);
                break;
            case 2:
                Quiz q = qd.getQuizByIdAR(id);
                Quiz quiz = new Quiz(q.getQid(), q.getQname(), q.getQdesc(), q.getCid(), q.getTid(), q.getSid(), active);
                qd.updateQuizAR(quiz);
                break;
            case 3:
                Section s = sd.getSectionById(id);
                Section section = new Section(s.getSid(), s.getCid(), s.getSname(), s.getTid(), active);
                sd.updateSectionAR(section);
                break;
            default:
                break;
        }

        if (sectionselect_raw == null) {
            sectionselect = 0;
        } else {
            sectionselect = Integer.parseInt(sectionselect_raw);
        }
        if (statusselect_raw == null) {
            statusselect = 2;
        } else {
            statusselect = Integer.parseInt(statusselect_raw);
        }

        List<Section> listSection = sd.getListSectionByCidAR(cid, sectionselect, statusselect);
        List<Lesson> listLesson = ld.getListLessonByCidAR(cid, sectionselect, statusselect);
        List<Quiz> listQuiz = qd.getListQuizByCidAR(cid, sectionselect, statusselect);
        Course c = cd.getCourseById(cid);
        List<Section> listSection1 = sd.getListSectionByCidAR(cid, 0, 2);
        
        RoleDAO rl = new RoleDAO();
        List<Role> listRole = rl.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSection1", listSection1);
        request.setAttribute("listSection", listSection);
        request.setAttribute("listLesson", listLesson);
        request.setAttribute("listQuiz", listQuiz);
        request.setAttribute("sectionselect", sectionselect);
        request.setAttribute("statusselect", statusselect);
        request.setAttribute("course", c);
        request.getRequestDispatcher("jsp/subject_lesson.jsp").forward(request, response);
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
        String cid = request.getParameter("cid");
        String sectionselect_raw = request.getParameter("sectionselect");
        String statusselect_raw = request.getParameter("statusselect");
       
        int sectionselect = Integer.parseInt(sectionselect_raw);
        int statusselect = Integer.parseInt(statusselect_raw);

        CourseDAO cd = new CourseDAO();
        SectionDAO sd = new SectionDAO();
        LessonDAO ld = new LessonDAO();
        QuizDAO qd = new QuizDAO();

        List<Section> listSection = sd.getListSectionByCidAR(cid, sectionselect, statusselect);
        List<Lesson> listLesson = ld.getListLessonByCidAR(cid, sectionselect, statusselect);
        List<Quiz> listQuiz = qd.getListQuizByCidAR(cid, sectionselect, statusselect);
        Course c = cd.getCourseById(cid);
        List<Section> listSection1 = sd.getListSectionByCidAR(cid, 0, 2);
        
        RoleDAO rl = new RoleDAO();
        List<Role> listRole = rl.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("listSection1", listSection1);
        request.setAttribute("listSection", listSection);
        request.setAttribute("listLesson", listLesson);
        request.setAttribute("listQuiz", listQuiz);
        request.setAttribute("course", c);
        request.setAttribute("sectionselect", sectionselect);
        request.setAttribute("statusselect", statusselect);
        request.getRequestDispatcher("jsp/subject_lesson.jsp").forward(request, response);
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
