/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CourseDAO;
import dal.LecturerDAO;
import dal.LevelDAO;
import dal.PriceDAO;
import dal.RoleDAO;
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Course;
import model.Lecturer;
import model.Level;
import model.Price;
import model.Role;
import model.Subject;
import model.SubjectCategory;
import model.TotalLesson;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class SubjectDetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet SubjectDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectDetailsServlet at " + request.getContextPath() + "</h1>");
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
        CourseDAO cd = new CourseDAO();
        SubjectCategoryDAO scd = new SubjectCategoryDAO();

        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");

        if (u.getRid() < 5) {
            response.sendRedirect("home");
        }

        if (request.getParameter("alertUpdate") != null) {
            request.setAttribute("alertUpdate", 1);
        }

        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }

        if (request.getParameter("addSubCat") != null) {
            request.setAttribute("addSubCat", 1);
        }

        if (request.getParameter("deleteSubCat") != null) {
            request.setAttribute("deleteSubCat", request.getParameter("deleteSubCat"));
        }
        if (request.getParameter("scidEdit") != null) {
            request.setAttribute("scidEdit", request.getParameter("scidEdit"));
        }
        if (request.getParameter("editSubCat") != null) {
            request.setAttribute("editSubCat", request.getParameter("editSubCat"));
        }

        // Course     
        String cid = request.getParameter("cid");
        Course c = cd.getCourseById(cid);

        // Course tagline+brief+description
        if (!c.getCdesc().trim().equals("")) {
            if (c.getCdesc().contains("|")) {
                System.out.println("Hello");
                String[] cdesc = c.getCdesc().split("[|]");
                request.setAttribute("tagline", cdesc[0].trim());
                request.setAttribute("brief", cdesc[1].trim());
                request.setAttribute("description", cdesc[2].trim());
            } else {
                request.setAttribute("tagline", "");
                request.setAttribute("brief", "");
                request.setAttribute("description", c.getCdesc());
            }
        } else {
            request.setAttribute("tagline", "");
            request.setAttribute("brief", "");
            request.setAttribute("description", "");
        }

        //Course Img
        if (c.getCimg().contains("https")) {
            request.setAttribute("cImage", 1);
        }
        request.setAttribute("course", c);
        int courseprice = (int) c.getCprice();
        request.setAttribute("courseprice", courseprice);

        //list SubjectCategory
        List<SubjectCategory> listSubjectCategory = scd.getAllSubjectCategory();
        request.setAttribute("listSubjectCategory", listSubjectCategory);

        //Price Package
        PriceDAO pdao = new PriceDAO();
        List<Price> listPricePackage = pdao.getAllPricePackage();
        request.setAttribute("listPricePackage", listPricePackage);
        if(request.getParameter("mspp") != null) request.setAttribute("mspp", "Active price package");

        //listLevel
        LevelDAO levd = new LevelDAO();
        List<Level> listLevel = levd.getAll();
        request.setAttribute("listLevel", listLevel);
        //listLecturer
        LecturerDAO lecd = new LecturerDAO();
        List<Lecturer> listLecturer = lecd.getAll();
        request.setAttribute("listLecturer", listLecturer);
        //select Search Subject Category
        SubjectDAO sd = new SubjectDAO();
        List<Subject> listSubject = sd.getAll();
        request.setAttribute("listSubject", listSubject);
        //total Lesson
        List<TotalLesson> listTL = sd.totalLesson();
        request.setAttribute("listTotalLesson", listTL);
        //Dashboard
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("jsp/subject_detail.jsp").forward(request, response);
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

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();
        SubjectDAO sd = new SubjectDAO();
        LecturerDAO lecd = new LecturerDAO();
        HttpSession hs = request.getSession();
        User u = (User) hs.getAttribute("user");

        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String ctitle = request.getParameter("ctitle");
        String subname = request.getParameter("subname");
        String lecname_raw = request.getParameter("lecname");
        String clevel = request.getParameter("clevel");
        String tagline = request.getParameter("tagline");
        String brief = request.getParameter("brief");
        String cdes_raw = request.getParameter("cdes");
        String cprice_raw = request.getParameter("cprice");
        String cstatus_raw = request.getParameter("cstatus");

        List<Course> listCourse = cd.getAll();
        Course c = cd.getCourseById(cid);

        int ok = 1;
        String error = "";
        for (int i = 0; i < listCourse.size(); i++) {
            if (subname == null || subname.trim().equals("")) {
                ok = 0;
                error = "Field Subject Category has NULL!";
                break;
            }
            if (clevel == null || clevel.trim().equals("")) {
                ok = 0;
                error = "Field Subject Level has NULL!";
                break;
            }

            if (ctitle == null || ctitle.trim().equals("")) {
                ok = 0;
                error = "Field Subject Title has NULL!";
                break;
            }

            if (tagline == null || tagline.trim().equals("")) {
                ok = 0;
                error = "Field Tag Line has NULL!";
                break;
            }
            if (brief == null || brief.trim().equals("")) {
                ok = 0;
                error = "Field Brief Information has NULL!";
                break;
            }

            if (cdes_raw == null || cdes_raw.trim().equals("")) {
                ok = 0;
                error = "Field Subject Description has NULL!";
                break;
            }
            if (u.getRid() == 6) {
                if (cprice_raw == null || cprice_raw.trim().equals("")) {
                    ok = 0;
                    error = "Field Subject Price has NULL!";
                    break;
                }
            }
        }
        if (ok == 0) {
            response.sendRedirect("subjectdetails?cid=" + cid + "&error=" + error);
        }

        Subject s = sd.getSubjectBySname(subname);// get sid
        Lecturer l = lecd.getLectByLectName(lecname_raw);// get lecid
        String newLec = "";
        if (l == null) { // ko co trong list Lect
            newLec = lecname_raw;
            lecd.insertLec(lecname_raw);
        } else {
            newLec = l.getLname();
        }
        Lecturer l_new = lecd.getLectByLectName(newLec);

        int levid = Integer.parseInt(clevel);
        double cprice = 0;
        if (u.getRid() == 6) {
            cprice = Double.parseDouble(cprice_raw);
        }

        boolean cstatus = false;
        Course c_beforeUpdate = cd.getCourseById(cid);

        String cdes = tagline + " | " + brief + " | " + cdes_raw;

        int noneLesson = 1;
        if (u.getRid() == 6) {
            //Check co LESSON trong Course hayy chua
            if (!c_beforeUpdate.isCstatus()) { //cstatus = unpus = 0
                if (cstatus_raw.equals("1")) { //cstatus = unpus = 0 -> pus = 1
                    cstatus = true;
                    List<TotalLesson> listTL = sd.totalLesson();
                    for (int i = 0; i < listTL.size(); i++) {
                        if (cid.equalsIgnoreCase(listTL.get(i).getCid())) {
                            if (listTL.get(i).getTotal() == 0) {
                                noneLesson = 0;
                                break;
                            }
                        }
                    }
                }
            } else {
                if (cstatus_raw.equals("0")) { //cstatus = unpus = 1 -> pus = 0
                    cstatus = false;
                }
            }
        }

        if (ok == 1) {
            String cImg = "";
            if (request.getPart("cimg").getSize() <= 0) { // ko thay doi img
                if (c.getCimg() == null) { // ko co cimg
                    if (noneLesson == 0) {
                        response.sendRedirect("subjectdetails?cid=" + cid + "&error=Not enough Lesson for this Course!");
                    } else {
                        cImg = "default.jpg";
                        if (u.getRid() == 6) {
                            cd.updateByAdmin(new Course(cid, cname, ctitle, cImg, cprice,
                                    cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                        } else {
                            cd.updateByExpert(new Course(cid, cname, ctitle, cImg, cprice,
                                    cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                        }
                        response.sendRedirect("subjectdetails?cid=" + cid + "&alertUpdate=" + 1);
                    }
                } else { // co cimg
                    if (noneLesson == 0) {
                        response.sendRedirect("subjectdetails?cid=" + cid + "&error=Not enough Lesson for this Course!");
                    } else {
                        if (u.getRid() == 6) {
                            cd.updateByAdmin(new Course(cid, cname, ctitle, c.getCimg(), cprice,
                                    cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                        } else {
                            cd.updateByExpert(new Course(cid, cname, ctitle, c.getCimg(), cprice,
                                    cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                        }
                        response.sendRedirect("subjectdetails?cid=" + cid + "&alertUpdate=" + 1);
                    }

                }
            } else { // thay doi img
                final String pathCourse = getFolderUploadPathCourse();
                final Part filePartCourse = request.getPart("cimg");
                final String fileName = getFileName(filePartCourse);

                OutputStream out = null;
                InputStream filecontent = null;
                final PrintWriter writer = response.getWriter();

                try {
                    File f = new File(pathCourse + File.separator + fileName);
                    if (f.exists()) {
                        response.sendRedirect("subjectdetails?cid=" + cid + "&error=Field Subject Image has Existed");
                    } else {
                        out = new FileOutputStream(f);
                        filecontent = filePartCourse.getInputStream();

                        int read = 0;
                        final byte[] bytes = new byte[1024];

                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }

                        if (noneLesson == 0) {
                            response.sendRedirect("subjectdetails?cid=" + cid + "&error=Not enough Lesson for this Course!");
                        } else {
                            if (u.getRid() == 6) {
                                cd.updateByAdmin(new Course(cid, cname, ctitle, fileName, cprice,
                                        cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                            } else {
                                cd.updateByExpert(new Course(cid, cname, ctitle, fileName, cprice,
                                        cdes, null, null, getCurrentTimeStamp(), s.getSid(), l_new.getLid(), levid, cstatus));
                            }
                            TimeUnit.SECONDS.sleep(2);
                            response.sendRedirect("subjectdetails?cid=" + cid + "&alertUpdate=" + 1);
                        }
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
