/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.GenderDAO;
import dal.LecturerDAO;
import dal.LessonDAO;
import dal.PriceDAO;
import dal.RegistrationDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.Gender;
import model.Lecturer;
import model.Lesson;
import model.Price;
import model.Registration;
import model.Subject;
import model.User;

public class CourseDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet CourseDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseDetailServlet at " + request.getContextPath() + "</h1>");
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
        //course detail
        LecturerDAO lecd = new LecturerDAO();
        SubjectDAO sd = new SubjectDAO();
        CourseDAO cd = new CourseDAO();
        String cid = request.getParameter("cid");
        Course c = cd.getCourseById(cid);
        String[] cdesc = c.getCdesc().split("[|]");
        request.setAttribute("tagline", cdesc[0].trim());
        request.setAttribute("brief", cdesc[1].trim());
        request.setAttribute("description", cdesc[2].trim());
        request.setAttribute("course", c);

        Lecturer l = lecd.getLectByLectID(c.getLecid());
        request.setAttribute("lecturer", l);

        List<Course> list = cd.getCourseBySid(c.getSid(), c.getCid());
        request.setAttribute("suggestlist", list);

        List<Subject> listSubject = sd.getAll();
        request.setAttribute("listSubject", listSubject);

        // course register
        GenderDAO gd = new GenderDAO();
        List<Gender> lGen = gd.getAll();
        request.setAttribute("listGender", lGen);

        // package price
        PriceDAO pd = new PriceDAO();
        List<Price> lPrice = pd.getAll();
        request.setAttribute("listPrice", lPrice);

        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");
        if (u != null) {
            // check enroll
            RegistrationDAO regd = new RegistrationDAO();
            Registration r = regd.checkEnoll(u.getUid(), cid); // user Registration
            request.setAttribute("myRegistration", r);
            if (r != null) {
                if (r.getRstatusId() == 0) { // submitted
                    request.setAttribute("status", "submitted");
                } else { //Registered
                    request.setAttribute("status", "registered");
                }
            }
        }

        if (request.getParameter("eidtRegistration") != null) {
            request.setAttribute("eidtRegistration", 1);
        }

        if (request.getParameter("enoughMoney") != null) {
            request.setAttribute("enoughMoney", "Enough Money");
        } else {
            if (request.getParameter("alertUserProfile") != null) {
                request.setAttribute("alertUserProfile", "Saved Change Successfully");
            }
            if (request.getParameter("errorProfile") != null) {
                request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
            }
        }
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog);

        request.getRequestDispatcher("jsp/course_detail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LecturerDAO lecd = new LecturerDAO();
        SubjectDAO sd = new SubjectDAO();
        CourseDAO cd = new CourseDAO();
        GenderDAO gd = new GenderDAO();
        PriceDAO pd = new PriceDAO();
        LessonDAO ld = new LessonDAO();

        String pack_price = request.getParameter("package_price");
        String cid = request.getParameter("cid");
        Course c = cd.getCourseById(cid);
        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");
        RegistrationDAO regd = new RegistrationDAO();
        List<Lesson> listLesson = ld.getListLessonByCID(cid);
        
        if (pack_price.equals("Free")) {
            if (regd.checkEnoll(u.getUid(), cid) != null) {// check Da dang ky chua
                regd.updatePackagePriceByCidAndUid(cid, u.getUid(), 1);
            } else {
                regd.insert(new Registration(getCurrentTimeStamp(), null, cid, u.getUid(), 0, 1, u.getUemail()));
            }
            
        }
        if (pack_price.equals("Silver")) {
            if (regd.checkEnoll(u.getUid(), cid) != null) {// check Da dang ky chua
                regd.updatePackagePriceByCidAndUid(cid, u.getUid(), 2);
            } else {
                regd.insert(new Registration(getCurrentTimeStamp(), null, cid, u.getUid(), 0, 2, u.getUemail()));
            }
        }
        if (pack_price.equals("Gold")) {
            if (regd.checkEnoll(u.getUid(), cid) != null) {// check Da dang ky chua
                regd.updatePackagePriceByCidAndUid(cid, u.getUid(), 3);
            } else {
                regd.insert(new Registration(getCurrentTimeStamp(), null, cid, u.getUid(), 0, 3, u.getUemail()));
            }
        }
        if (pack_price.equals("Diamond")) {
            if (regd.checkEnoll(u.getUid(), cid) != null) {// check Da dang ky chua
                regd.updatePackagePriceByCidAndUid(cid, u.getUid(), 4);
            } else {
                regd.insert(new Registration(getCurrentTimeStamp(), null, cid, u.getUid(), 0, 4, u.getUemail()));
            }
        }
        String[] cdesc = c.getCdesc().split("[|]");
        request.setAttribute("tagline", cdesc[0].trim());
        request.setAttribute("brief", cdesc[1].trim());
        request.setAttribute("description", cdesc[2].trim());
        request.setAttribute("course", c);

        Lecturer l = lecd.getLectByLectID(c.getLecid());
        request.setAttribute("lecturer", l);

        List<Course> list = cd.getCourseBySid(c.getSid(), c.getCid());
        request.setAttribute("suggestlist", list);

        List<Subject> listSubject = sd.getAll();
        request.setAttribute("listSubject", listSubject);

        // course register
        List<Gender> lGen = gd.getAll();
        request.setAttribute("listGender", lGen);

        // package price
        List<Price> lPrice = pd.getAll();
        request.setAttribute("listPrice", lPrice);

        Registration r = regd.checkEnoll(u.getUid(), cid);
        if (r != null) {
            if (r.getRstatusId() == 0) { // submitted
                request.setAttribute("status", "submitted");
            } else { //Registered
                request.setAttribute("status", "registered");
            }
        }

        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog);

        request.setAttribute("successful", "successful");
        request.getRequestDispatcher("jsp/course_detail.jsp").forward(request, response);
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
