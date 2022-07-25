/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.LessonResultDAO;
import dal.QuizDAO;
import dal.QuizResultDAO;
import dal.RegistrationDAO;
import dal.SectionDAO;
import dal.TimeCourseDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.Lesson;
import model.LessonResult;
import model.Quiz;
import model.QuizResult;
import model.Registration;
import model.Section;
import model.SendEmail;
import model.TimeCourse;
import model.User;

/**
 *
 * @author ADMIN
 */
public class SectionServlet extends HttpServlet {

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
            out.println("<title>Servlet SectionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SectionServlet at " + request.getContextPath() + "</h1>");
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
        String section_raw = request.getParameter("section");
        String lesson_raw = request.getParameter("lesson");
        String next_raw = request.getParameter("next");
        String cid = request.getParameter("cid");
        String lid_raw = request.getParameter("lid");
        String end_raw = request.getParameter("end");

        QuizDAO qd = new QuizDAO();
        LessonDAO ld = new LessonDAO();
        SectionDAO sd = new SectionDAO();
        LessonResultDAO lrd = new LessonResultDAO();
        QuizResultDAO qrd = new QuizResultDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        // cua thanh nhe dao---------------------------------------------------------
        RegistrationDAO rd = new RegistrationDAO();
        CourseDAO cd = new CourseDAO();
        UserDAO ud = new UserDAO();
        SendEmail se = new SendEmail();
        TimeCourseDAO tcd = new TimeCourseDAO();
        
        Course c = cd.getCourseById(cid);
        Registration r = rd.checkEnoll(u.getUid(), cid);

        if (request.getParameter("status") != null) { //Customer an Start Course
            if (r.getPid() == 1) { // Free
                se.sendRegistration(u.getUemail(), cid, "Free");
                rd.updateByCidAndUid(new Registration("", "", cid, u.getUid(), 0, 0, null));
                tcd.insertTimeCourse(new TimeCourse(0, cid, u.getUid(), null, null));
            }
            // Tru Tien Khoa Hoc mat phi
            if (r.getPid() == 2) { //Silver
                if (Double.parseDouble(u.getUwallet()) < c.getCprice() * 0.8) {//enoughMoney
                    request.setAttribute("cid", cid);
                    request.setAttribute("enoughMoney", 1);
                    request.getRequestDispatcher("coursedetail").forward(request, response);
                } else {
                    Double newWallet = Double.parseDouble(u.getUwallet()) - c.getCprice() * 0.8;
                    se.sendRegistration(u.getUemail(), cid, "Silver");
                    User uNew = new User(u.getUid(), u.getUemail(), u.getUfullname(), u.getUimg(), u.getGid(),
                            u.getUdob(), u.getUphone(), u.getUaddress(), newWallet.toString(), u.getRid());
                    ud.updateUser(uNew, u.getUid());
                    session.removeAttribute("user");
                    session.setAttribute("user", uNew);
                    rd.updateByCidAndUid(new Registration("", "", cid, u.getUid(), 0, 0, null));
                    tcd.insertTimeCourse(new TimeCourse(0, cid, u.getUid(), null, null));
                }
            }
            if (r.getPid() == 3) {
                if (Double.parseDouble(u.getUwallet()) < c.getCprice() * 1.2) { //enoughMoney
                    request.setAttribute("cid", cid);
                    request.setAttribute("enoughMoney", 1);
                    request.getRequestDispatcher("coursedetail").forward(request, response);
                } else {
                    Double newWallet = Double.parseDouble(u.getUwallet()) - c.getCprice() * 1.2;
                    se.sendRegistration(u.getUemail(), cid, "Gold");
                    User uNew = new User(u.getUid(), u.getUemail(), u.getUfullname(), u.getUimg(), u.getGid(),
                            u.getUdob(), u.getUphone(), u.getUaddress(), newWallet.toString(), u.getRid());
                    ud.updateUser(uNew, u.getUid());
                    session.removeAttribute("user");
                    session.setAttribute("user", uNew);
                    rd.updateByCidAndUid(new Registration("", "", cid, u.getUid(), 0, 0, null));
                    tcd.insertTimeCourse(new TimeCourse(0, cid, u.getUid(), null, null));
                }
            }
            if (r.getPid() == 4) {
                if (Double.parseDouble(u.getUwallet()) < c.getCprice() * 1.6) {//enoughMoney
                    request.setAttribute("cid", cid);
                    request.setAttribute("enoughMoney", 1);
                    request.getRequestDispatcher("coursedetail").forward(request, response);
                } else {
                    Double newWallet = Double.parseDouble(u.getUwallet()) - c.getCprice() * 1.6;
                    se.sendRegistration(u.getUemail(), cid, "Diamond");
                    User uNew = new User(u.getUid(), u.getUemail(), u.getUfullname(), u.getUimg(), u.getGid(),
                            u.getUdob(), u.getUphone(), u.getUaddress(), newWallet.toString(), u.getRid());
                    ud.updateUser(uNew, u.getUid());
                    session.removeAttribute("user");
                    session.setAttribute("user", uNew);
                    // Cap nhat trang thai Registered = 1
                    rd.updateByCidAndUid(new Registration("", "", cid, u.getUid(), 0, 0, null));
                    tcd.insertTimeCourse(new TimeCourse(0, cid, u.getUid(), null, null));
                }
            }

            List<Lesson> listLessonT = ld.getListLessonByCID(cid);
            for (int i = 0; i < listLessonT.size(); i++) {
                lrd.insertLessonResult(listLessonT.get(i).getLid(), u.getUid());
            }
        }
        // cua thanh nhe dao---------------------------------------------------------

        if (cid == null) {
            cid = "0";
        }
        List<Lesson> listLesson1 = ld.getListLessonByCID(cid);
        List<LessonResult> listLessonResult1 = lrd.getListLessonByUid(u.getUid(),cid);
        List<Section> listSection1 = sd.getListSectionByCourseID(cid);
        int lid = 0;
        int next;
        int lesson = 0;
        int section = 0;

        if (section_raw == null) {
            for (int i = 0; i < listLessonResult1.size(); i++) {
                if (listLessonResult1.get(i).isLstatus() == false) {
                    int section_id = ld.getLessonByID(listLessonResult1.get(i).getLid()).getSid();
                    for (int j = 0; j < listSection1.size(); j++) {
                        if (section_id == listSection1.get(j).getSid()) {
                            section = j + 1;
                        }
                    }
                    break;
                } else {
                    section = 1;
                }
            }
        } else {
            section = Integer.parseInt(section_raw);
        }
        if (lesson_raw == null) {
            for (int i = 0; i < listLessonResult1.size(); i++) {
                if (listLessonResult1.get(i).isLstatus() == false) {
                    int section_id = ld.getLessonByID(listLessonResult1.get(i).getLid()).getSid();
                    int lesson_id = listLessonResult1.get(i).getLid();
                    List<Lesson> listLesson2 = ld.getListLessonByCIdSID(cid, section_id);
                    for (int j = 0; j < listLesson2.size(); j++) {
                        if (lesson_id == listLesson2.get(j).getLid()) {
                            lesson = j + 1;
                        }
                    }
                    break;
                } else {
                    lesson = 1;
                }
            }
        } else {
            lesson = Integer.parseInt(lesson_raw);
        }

        if (lid_raw == null) {
            for (int i = 0; i < listLessonResult1.size(); i++) {
                if (listLessonResult1.get(i).isLstatus() == false) {
                    lid = listLessonResult1.get(i).getLid();
                    break;
                } else {
                    lid = listLesson1.get(0).getLid();
                }
            }
        } else {
            lid = Integer.parseInt(lid_raw);
        }
        if (end_raw != null) {
            lrd.updateLessonResult(lid, u.getUid());
        }
        List<Section> listSection = sd.getListSectionByCourseID(cid);
        List<Lesson> listLesson = ld.getListLessonByCID(cid);
        List<Quiz> listQuiz = qd.getListQuizByCID(cid);
        List<LessonResult> listLessonResult = lrd.getListLessonByUid(u.getUid(),cid);
        List<QuizResult> listQuizResult = qrd.getListStatusQuizResultByUid(u.getUid());

        // khong next
        if (next_raw == null) {
            Lesson l = ld.getLessonByID(lid);
            Lesson checknext = ld.getLessonNext(l.getSid(), l.getLid(), l.getCid(), 1);
            Lesson checknext2 = ld.getLessonNext(l.getSid(), l.getLid(), l.getCid(), 2);
            request.setAttribute("lesson", l);
            request.setAttribute("checknext", checknext);
            request.setAttribute("checknext2", checknext2);
            Quiz q = qd.getQuizNext(l.getSid());
            if (q != null) {
                QuizResult qr = qrd.getQuizResultByQIdUid(q.getQid(), u.getUid());
                request.setAttribute("quizresult", qr);
            }
            request.setAttribute("quiz", q);

        } else {  //next                      
            next = Integer.parseInt(next_raw);
            Lesson l1 = ld.getLessonByID(lid);
            Lesson l = ld.getLessonNext(l1.getSid(), lid, l1.getCid(), next);

            if (l != null) {
                Lesson checknext = ld.getLessonNext(l.getSid(), l.getLid(), l.getCid(), 1);
                Lesson checknext2 = ld.getLessonNext(l.getSid(), l.getLid(), l.getCid(), 2);
                request.setAttribute("checknext2", checknext2);
                request.setAttribute("checknext", checknext);
                request.setAttribute("lesson", l);
                Quiz q = qd.getQuizNext(l1.getSid());
                if (q != null) {
                    QuizResult qr = qrd.getQuizResultByQIdUid(q.getQid(), u.getUid());
                    request.setAttribute("quizresult", qr);
                }
                request.setAttribute("quiz", q);
            } else {        //het lesson trong course
                if (next == -1) {
                    Lesson l2 = ld.getLessonNext(l1.getSid(), l1.getLid(), l1.getCid(), -2);
                    request.setAttribute("lesson", l2);
                    section = section - 1;
                    List<Lesson> listLesson2 = ld.getListLessonByCIdSID(l1.getCid(), l2.getSid());
                    lesson = listLesson2.size();
                    request.setAttribute("checknext2", 1);
                    Quiz q = qd.getQuizNext(l2.getSid());
                    if (q != null) {
                        QuizResult qr = qrd.getQuizResultByQIdUid(q.getQid(), u.getUid());
                        request.setAttribute("quizresult", qr);
                    }
                    request.setAttribute("quiz", q);
                }
            }
        }

        request.setAttribute("listSection", listSection);
        request.setAttribute("listLesson", listLesson);
        request.setAttribute("listQuiz", listQuiz);
        request.setAttribute("listLessonResult", listLessonResult);
        request.setAttribute("listQuizResult", listQuizResult);
        request.setAttribute("lessonCount", lesson);
        request.setAttribute("sectionCount", section);
        request.setAttribute("cid", cid);

        request.getRequestDispatcher("jsp/lesson_view.jsp").forward(request, response);
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
