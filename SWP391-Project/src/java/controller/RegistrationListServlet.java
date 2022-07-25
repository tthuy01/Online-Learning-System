/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.CourseDAO;
import dal.PriceDAO;
import dal.RegistrationDAO;
import dal.RegistrationStatusDAO;
import dal.RoleDAO;
import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.Price;
import model.Registration;
import model.RegistrationStatus;
import model.Role;
import model.Subject;
import model.SubjectCategory;
import model.User;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class RegistrationListServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistrationListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationListServlet at " + request.getContextPath() + "</h1>");
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
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        String search = request.getParameter("search");
        String sortBy = request.getParameter("sort");
        String[] subCate = request.getParameterValues("subCate");
        String[] subject = request.getParameterValues("subject");
        String[] pack = request.getParameterValues("package");
        String[] cost_raw = request.getParameterValues("totalCost");
        String[] status = request.getParameterValues("status");
        String regTimeFrom = request.getParameter("regTimeFrom");
        String regTimeTo = request.getParameter("regTimeTo");

        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        //==================For filter================
        SubjectCategoryDAO scdao = new SubjectCategoryDAO();
        List<SubjectCategory> listSubjectCategory = scdao.getAllSubjectCategory();

        SubjectDAO sdao = new SubjectDAO();
        List<Subject> listSubject = sdao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer

        PriceDAO pdao = new PriceDAO();
        List<Price> listPricePackage = pdao.getAll();

        RegistrationStatusDAO rsdao = new RegistrationStatusDAO();
        List<RegistrationStatus> listRegistrationStatus = rsdao.getAll();
        //==================For filter================
        if (search == null) {
            search = ""; //Tim tat ca cac registration
        }

        if (sortBy == null) {
            sortBy = "";
        }

        if (regTimeFrom == null || regTimeFrom.equals("")) {
            regTimeFrom = "2000-01-01";
        }

        if (regTimeTo == null || regTimeTo.equals("")) {
            regTimeTo = "2100-01-01";
        }

        if (subCate != null) {
            boolean[] cSubCate = new boolean[listSubjectCategory.size()];
            for (int i = 0; i < listSubjectCategory.size(); i++) {
                if (isCheck(String.valueOf(listSubjectCategory.get(i).getSubjectCateId()), subCate)) {
                    cSubCate[i] = true;
                } else {
                    cSubCate[i] = false;
                }
            }
            request.setAttribute("cSubCate", cSubCate);
        }

        if (subject != null) {
            boolean[] cSubject = new boolean[listSubject.size()];
            for (int i = 0; i < listSubject.size(); i++) {
                if (isCheck(String.valueOf(listSubject.get(i).getSid()), subject)) {
                    cSubject[i] = true;
                } else {
                    cSubject[i] = false;
                }
            }
            request.setAttribute("cSubject", cSubject);
        }

        if (pack != null) {
            boolean[] cPricePackage = new boolean[listPricePackage.size()];
            for (int i = 0; i < listPricePackage.size(); i++) {
                if (isCheck(String.valueOf(listPricePackage.get(i).getPackid()), pack)) {
                    cPricePackage[i] = true;
                } else {
                    cPricePackage[i] = false;
                }
            }
            request.setAttribute("cPricePackage", cPricePackage);
        }

        if (status != null) {
            boolean[] cRegistrationStatus = new boolean[listRegistrationStatus.size()];
            for (int i = 0; i < listRegistrationStatus.size(); i++) {
                if (isCheck(String.valueOf(listRegistrationStatus.get(i).getRstatusId()), status)) {
                    cRegistrationStatus[i] = true;
                } else {
                    cRegistrationStatus[i] = false;
                }
            }
            request.setAttribute("cRegistrationStatus", cRegistrationStatus);
        }

        RegistrationDAO rdao = new RegistrationDAO();
        double maxPrice = rdao.getMaxTotalCost(); //For filter

        List<String> listPrice = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listPrice.add((Math.ceil((maxPrice / 5) * i)) + " and " + (Math.ceil((maxPrice / 5) * (i + 1))));
        }

        String[] costFrom;
        String[] costTo;
        if (cost_raw != null) {
            costFrom = new String[cost_raw.length];
            costTo = new String[cost_raw.length];
            for (int i = 0; i < costFrom.length; i++) {
                int index = cost_raw[i].indexOf(" and ");
                costFrom[i] = cost_raw[i].substring(0, index - 1);
                costTo[i] = cost_raw[i].substring(index + 5, cost_raw[i].length());
            }
        } else {
            costFrom = null;
            costTo = null;
        }

        if (cost_raw != null) {
            boolean[] cTotalCost = new boolean[listPrice.size()];
            for (int i = 0; i < listPrice.size(); i++) {
                if (isCheck(listPrice.get(i), cost_raw)) {
                    cTotalCost[i] = true;
                } else {
                    cTotalCost[i] = false;
                }
            }
            request.setAttribute("cTotalCost", cTotalCost);
        }

        int page = 1;
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(xPage);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        List<Registration> listRegistration = rdao.getAllAndFilterListReg(search, subCate, subject, pack, costFrom, costTo, status, regTimeFrom, regTimeTo, sortBy, page);
        int size = rdao.countTotalOfRes(search, subCate, subject, pack, costFrom, costTo, status, regTimeFrom, regTimeTo, sortBy); //Dem tong so registration
        int rPerPage = 10;
        int num = (size % 10 == 0) ? (size / 10) : ((size / 10) + 1); //Tong so page
//        System.out.println("Search: "+search);
//        System.out.println("sortBy: "+sortBy);
//        System.out.println("subCate: "+subCate);
//        System.out.println("subject: "+subject);
//        System.out.println("pack: "+pack);
//        System.out.println("cost_raw: "+cost_raw);
//        System.out.println("status: "+status);
//        System.out.println("regTimeFrom: "+regTimeFrom);
//        System.out.println("regTimeTo: "+regTimeTo);
//        System.out.println("costFrom: "+costFrom);
//        System.out.println("costTo: "+costTo);

        List<String> listCourseName = rdao.getListCourseNameByListRegistration(listRegistration);
        List<String> listSubjectName = rdao.getListSubjectByListRegistration(listRegistration);
        List<String> listPackageName = rdao.getListPackageName(listRegistration);
        List<Double> listTotalPrice = rdao.getListTotalPrice(listRegistration);
        List<String> listValidTo = rdao.getListValidTo(listRegistration);
        UserDAO udao = new UserDAO();
        List<User> listUser = udao.getListUserByListReg(listRegistration);

        try {
            Date dFrom = new SimpleDateFormat("yyyy-MM-dd").parse(regTimeFrom);
            Date dTo = new SimpleDateFormat("yyyy-MM-dd").parse(regTimeTo);
            System.out.println(dTo);
            if (dFrom.after(dTo)) {
                request.setAttribute("ms", "Date From must be before Date To");
                request.setAttribute("subCates", subCate);
                request.setAttribute("subjects", subject);
                request.setAttribute("packs", pack);
                request.setAttribute("statuses", status);
                request.setAttribute("costs", cost_raw);
                request.setAttribute("regTimeFrom", regTimeFrom);
                request.setAttribute("regTimeTo", regTimeTo);

                CourseDAO cdao = new CourseDAO();
                List<Course> listCourse = cdao.getAll();
                List<Price> listPackagePrice = pdao.getAll();
                request.setAttribute("listPackagePrice", listPackagePrice);
                request.setAttribute("listCourse", listCourse);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("sortBy", sortBy);
                request.setAttribute("search", search);
                request.setAttribute("listUser", listUser);
                request.setAttribute("listValidTo", listValidTo);
                request.setAttribute("listTotalPrice", listTotalPrice);
                request.setAttribute("listPackageName", listPackageName);
                request.setAttribute("listSubjectName", listSubjectName);
                request.setAttribute("listCourseName", listCourseName);
                request.setAttribute("listRegistration", listRegistration);
                request.setAttribute("listSubjectCategory", listSubjectCategory); //For filter
                request.setAttribute("listSubject", listSubject); //For filter
                request.setAttribute("listPricePackage", listPricePackage); //For filter
                request.setAttribute("maxPrice", maxPrice); //For filter
                request.setAttribute("listRegistrationStatus", listRegistrationStatus); //For filter
                request.getRequestDispatcher("jsp/registration_list.jsp").forward(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(RegistrationListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("subCates", subCate);
        request.setAttribute("subjects", subject);
        request.setAttribute("packs", pack);
        request.setAttribute("statuses", status);
        request.setAttribute("costs", cost_raw);
        request.setAttribute("regTimeFrom", regTimeFrom);
        request.setAttribute("regTimeTo", regTimeTo);

        CourseDAO cdao = new CourseDAO();
        List<Course> listCourse = cdao.getAll();
        List<Price> listPackagePrice = pdao.getAll();
        request.setAttribute("listPackagePrice", listPackagePrice);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("search", search);
        request.setAttribute("listUser", listUser);
        request.setAttribute("listValidTo", listValidTo);
        request.setAttribute("listTotalPrice", listTotalPrice);
        request.setAttribute("listPackageName", listPackageName);
        request.setAttribute("listSubjectName", listSubjectName);
        request.setAttribute("listCourseName", listCourseName);
        request.setAttribute("listRegistration", listRegistration);
        request.setAttribute("listSubjectCategory", listSubjectCategory); //For filter
        request.setAttribute("listSubject", listSubject); //For filter
        request.setAttribute("listPricePackage", listPricePackage); //For filter
        request.setAttribute("maxPrice", maxPrice); //For filter
        request.setAttribute("listRegistrationStatus", listRegistrationStatus); //For filter
        request.getRequestDispatcher("jsp/registration_list.jsp").forward(request, response);

    }

    private boolean isCheck(String sc, String[] subCates) {
        if (subCates == null) {
            return false;
        } else {
            for (int i = 0; i < subCates.length; i++) {
                if (subCates[i].equals(sc)) {
                    return true;
                }
            }
            return false;
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
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
