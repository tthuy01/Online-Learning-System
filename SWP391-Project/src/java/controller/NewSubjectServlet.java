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
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Blog;
import model.Course;
import model.ManageCourse;
import model.Role;
import model.Subject;
import model.SubjectCategory;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class NewSubjectServlet extends HttpServlet {

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
            out.println("<title>Servlet NewSubjectServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewSubjectServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");
        if (u == null) {
            request.setAttribute("ReturnUrl", "http://localhost:8080/project/subjectlist");
            request.getRequestDispatcher("login").forward(request, response);
        }
        if (u.getRid() != 6) {
            request.getRequestDispatcher("home").forward(request, response);
        }

        if (request.getParameter("alertNewSub") != null) {
            request.setAttribute("alertNewSub", 1);
        }

        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }

        SubjectCategoryDAO scd = new SubjectCategoryDAO();
        List<SubjectCategory> listSubjectCategory = scd.getAll();
        UserDAO ud = new UserDAO();
        List<User> listUbyExpert = ud.getUserByRoleExpert(5);

        request.setAttribute("listUserByExpert", listUbyExpert);
        request.setAttribute("listSubCat", listSubjectCategory);
        //Header
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog);
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        request.setAttribute("listSubject", listSubject);
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("jsp/new_subject.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public String getFolderUploadPathCourse() {
        String path = getServletContext().getRealPath("/") + "images";
        String path2 = getServletContext().getRealPath("/").replace("\\build", "") + "images";
        String path3 = path2 + "\\course";
        File folderUpload = new File(path);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return path3;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();
        ManageCourseDAO mcd = new ManageCourseDAO();
        SubjectDAO sd = new SubjectDAO();

        //Course
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String sname = request.getParameter("sname");
        String cdes = request.getParameter("cdes");
        String uidManageCourse = request.getParameter("uidManageCourse");

        List<Course> listCourse = cd.getAll();
        int ok = 1;
        String error = "";
        for (int i = 0; i < listCourse.size(); i++) {
            if (cid.toLowerCase().equals(listCourse.get(i).getCid().toLowerCase())) {
                ok = 0;
                error += "Field Subject Id has Existed!";
                break;
            } else {
                if (!cid.matches("[A-Za-z0-9_]+")) {
                    ok = 0;
                    error += "Field Subject Id has A special character!";
                    break;
                }
            }
            if (cname.toLowerCase().equals(listCourse.get(i).getCname().toLowerCase())) {
                ok = 0;
                error += "Field Subject Name has Existed!";
                break;
            }
            if (sname == null || sname.trim().equals("")) {
                ok = 0;
                error = "Field Subject Category has NULL!";
                break;
            }
            if (uidManageCourse == null || uidManageCourse.equals("-1")) {
                ok = 0;
                error = "Field Owner has NULL!";
                break;
            }
            if (cdes == null || cdes.trim().equals("")) {
                ok = 0;
                error = "Field Subject Description has NULL!";
                break;
            }
        }

        if (ok == 0) {
            response.sendRedirect("newsubject?error=" + error);
        }
        if (ok == 1) {
            final String pathCourse = getFolderUploadPathCourse();
            final Part filePartCourse = request.getPart("cimg");
            final String fileName = getFileName(filePartCourse);
            if(fileName.length() < 1) response.sendRedirect("newsubject?error=Field Subject Image has NULL");

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                File f = new File(pathCourse + File.separator + fileName);

                if (f.exists()) {
                    response.sendRedirect("newsubject?error=" + "Field Subject Image has Existed");
                } else {
                    out = new FileOutputStream(f);
                    filecontent = filePartCourse.getInputStream();

                    int read = 0;
                    final byte[] bytes = new byte[1024];

                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    Subject s = sd.getSubjectBySname(sname);
                    // get img    
                    cd.insertByAdmin(new Course(cid, cname, null, fileName, 0.0, cdes, null, null, null, s.getSid(), 0, 0, false));
                    mcd.insertManageCourseByAdmin(new ManageCourse(cid, Integer.parseInt(uidManageCourse), ""));

                    TimeUnit.SECONDS.sleep(2);
                    response.sendRedirect("newsubject?alertNewSub=" + 1);
                }

            } catch (FileNotFoundException fne) {
                writer.println("<br/> ERROR: " + fne.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
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
