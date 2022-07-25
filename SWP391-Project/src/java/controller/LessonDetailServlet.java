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
import dal.TypeDAO;
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
import model.Type;

/**
 *
 * @author ADMIN
 */
public class LessonDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet LessonDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessonDetailServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String type_raw = request.getParameter("type");
        String cid = request.getParameter("cid");
        try {
            int id, type;
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

            CourseDAO cd = new CourseDAO();
            SectionDAO sd = new SectionDAO();
            LessonDAO ld = new LessonDAO();
            TypeDAO td = new TypeDAO();
            
            if (id == 0) {
                List<Course> listCourse = cd.getAll();
                List<Type> listType = td.getAll();
                Lesson lesson = new Lesson(id, null, null, cid, 1, 1, null);
                List<Section> listSection = sd.getListSectionByCourseIDAR(cid);
                request.setAttribute("listSection", listSection);
                request.setAttribute("lesson", lesson);
                request.setAttribute("listCourse", listCourse);
                request.setAttribute("listType", listType);
            }
            if (type == 1) {
                Lesson lesson = ld.getLessonByID(id);
                List<Section> listSection = sd.getListSectionByCourseIDAR(lesson.getCid());
                List<Course> listCourse = cd.getAll();
                List<Type> listType = td.getAll();
                cid = lesson.getCid();
                request.setAttribute("lesson", lesson);
                request.setAttribute("listSection", listSection);
                request.setAttribute("listCourse", listCourse);
                request.setAttribute("listType", listType);
            }
            if (type == 3) {
                Section section = sd.getSectionById(id);
                List<Course> listCourse = cd.getAll();
                List<Type> listType = td.getAll();
                cid = section.getCid();
                request.setAttribute("section", section);
                request.setAttribute("listCourse", listCourse);
                request.setAttribute("listType", listType);
            }
            RoleDAO roledao = new RoleDAO();
            List<Role> listRole = roledao.getAll();
            request.setAttribute("listRole", listRole);
            request.setAttribute("cid", cid);
            request.setAttribute("id", id);
            request.setAttribute("type", type);
            request.getRequestDispatcher("jsp/lesson_detail.jsp").forward(request, response);
        } catch (IOException e) {
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
        request.setCharacterEncoding("UTF-8");
        String submit_value = request.getParameter("savechange");
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String category_raw = request.getParameter("category");
        String type_raw = request.getParameter("type");
        String cid = request.getParameter("course");
        String video = request.getParameter("video");
        String desc = request.getParameter("desc");
        String checkchange = request.getParameter("checkchange");
        String cid_back = request.getParameter("cid");

        int type = Integer.parseInt(type_raw);
        int id = Integer.parseInt(id_raw);

        CourseDAO cd = new CourseDAO();
        SectionDAO sd = new SectionDAO();
        LessonDAO ld = new LessonDAO();
        TypeDAO td = new TypeDAO();

        try {
            if (submit_value == null) {
                if (id == 0 && type == 1) {
                    int category;
                    if (category_raw == null) {
                        category = 0;
                    } else {
                        if (type == 1 && checkchange == null) {
                            category = Integer.parseInt(category_raw);
                        } else {
                            category = 1;
                            cid = cid_back;
                        }

                    }
                    Lesson lesson = new Lesson(id, name, video, cid, category, type, desc);
                    request.setAttribute("lesson", lesson);
                    request.setAttribute("id", id);

                } else if (type == 1) {
                    int category = Integer.parseInt(category_raw);
                    String vd = "https://www.youtube.com/embed/" + video.substring(32, 43);
                    Lesson lesson = new Lesson(id, name, vd, cid, category, type, desc);
                    request.setAttribute("lesson", lesson);
                } else if (id == 0 && type == 3) {
                    Section section = new Section(id, category_raw, name, type);
                    request.setAttribute("section", section);
                    request.setAttribute("id", id);
                }
                List<Section> listSection = sd.getListSectionByCourseIDAR(cid);
                List<Course> listCourse = cd.getAll();
                List<Type> listType = td.getAll();
                request.setAttribute("listType", listType);
                request.setAttribute("listSection", listSection);
                request.setAttribute("listCourse", listCourse);
            } else {
                if (type == 1 && id != 0) {
                    int category = Integer.parseInt(category_raw);
                    String vd = "https://www.youtube.com/embed/" + video.substring(32, 43);
                    Lesson lesson = new Lesson(id, name, vd, cid, category, type, desc);
                    ld.updateLesson(lesson);
                    request.setAttribute("lesson", lesson);
                    request.setAttribute("successfull", "Update lesson detail successfully!");
                }
                if (type == 3 && id != 0) {
                    Section section = new Section(id, category_raw, name, type);
                    sd.updateSection(section);
                    request.setAttribute("section", section);
                    request.setAttribute("successfull", "Update section detail successfully!");
                }
                if (id == 0 && type == 1) {
                    int category = Integer.parseInt(category_raw);
                    String vd = "https://www.youtube.com/embed/" + video.substring(32, 43);
                    Lesson lesson = new Lesson(id, name, vd, cid_back, category, type, desc);
                    ld.insertLesson(lesson);
                    request.setAttribute("id", 0);
                    Lesson l = new Lesson(id, null, null, cid_back, 1, 1, null);
                    List<Section> listSection = sd.getListSectionByCourseIDAR(cid_back);
                    request.setAttribute("listSection", listSection);
                    request.setAttribute("lesson", l);
                    request.setAttribute("successfull", "Add new lesson successfully!");
                }
                if (type == 3 && id == 0) {
                    Section section = new Section(id, category_raw, name, type);
                    sd.insertSection(section);
                    Section s = new Section(id, cid_back, null, type);
                    request.setAttribute("section", s);
                    List<Section> listSection = sd.getListSectionByCourseIDAR(cid_back);
                    request.setAttribute("listSection", listSection);
                    request.setAttribute("id", 0);
                    request.setAttribute("successfull", "Add new section successfully!");
                }
                if (id != 0) {
                    List<Section> listSection = sd.getListSectionByCourseIDAR(cid);
                    request.setAttribute("listSection", listSection);
                }

                List<Course> listCourse = cd.getAll();
                List<Type> listType = td.getAll();
                request.setAttribute("listType", listType);

                request.setAttribute("listCourse", listCourse);
                
            }
            RoleDAO roledao = new RoleDAO();
            List<Role> listRole = roledao.getAll();
            request.setAttribute("listRole", listRole);
            request.setAttribute("cid", cid_back);
            request.setAttribute("id", id);
            request.setAttribute("type", type);
            request.getRequestDispatcher("jsp/lesson_detail.jsp").forward(request, response);
        } catch (IOException e) {
        }

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
