/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.SettingDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Setting;
import model.SettingStatus;
import model.SettingType;
import model.Subject;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class SettingListServlet extends HttpServlet {

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
            out.println("<title>Servlet SettingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingListServlet at " + request.getContextPath() + "</h1>");
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
        String search = request.getParameter("search");
        String sortBy = request.getParameter("sort");
        String[] typeId = request.getParameterValues("type");
        String[] statusId = request.getParameterValues("status");
        //====================Footer=========================
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        request.setAttribute("listBlog", listBlog); //For footer

        SubjectDAO sdao = new SubjectDAO();
        List<Subject> listSubject = sdao.getAll();
        request.setAttribute("listSubject", listSubject); //For footer
        //====================Footer=========================
        
        //====================Loading data===================
        SettingDAO sedao = new SettingDAO();
        List<SettingType> listSettingType = sedao.getAllSettingType();
        List<SettingStatus> listSettingStatus = sedao.getAllSettingStatus();
        //====================Loading data===================
        if (search == null) {
            search = ""; //Tim tat ca cac registration
        }

        if (sortBy == null) {
            sortBy = "";
        }
        
        if (typeId != null) {
            boolean[] cTypeId = new boolean[listSettingType.size()];
            for (int i = 0; i < listSettingType.size(); i++) {
                if (isCheck(String.valueOf(listSettingType.get(i).getSettingTypeId()), typeId)) {
                    cTypeId[i] = true;
                } else {
                    cTypeId[i] = false;
                }
            }
            request.setAttribute("cTypeId", cTypeId);
        }
        
        if (statusId != null) {
            boolean[] cStatusId = new boolean[listSettingStatus.size()];
            for (int i = 0; i < listSettingStatus.size(); i++) {
                if (isCheck(String.valueOf(listSettingStatus.get(i).getSettingStatusId()), statusId)) {
                    cStatusId[i] = true;
                } else {
                    cStatusId[i] = false;
                }
            }
            request.setAttribute("cStatusId", cStatusId);
        }
           
        int size = sedao.countTotalOfSetting(search, typeId, statusId);
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
        List<Setting> listSetting = sedao.getSetting(search, sortBy, typeId, statusId, page);
        int sPerPage = 4;
        int num = (size % sPerPage == 0) ? (size / sPerPage) : ((size / sPerPage) + 1); //Tong so page
        
        List<SettingType> listSettingTypeBySettingList = sedao.getListSettingTypeBySettingList(listSetting);
        List<SettingStatus> listSettingStatusBySettingList = sedao.getListSettingStatusBySettingList(listSetting);
        
        request.setAttribute("size", size);
        request.setAttribute("listSettingTypeBySettingList", listSettingTypeBySettingList);
        request.setAttribute("listSettingStatusBySettingList", listSettingStatusBySettingList);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("search", search);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("typeId", typeId);
        request.setAttribute("statusId", statusId);
        request.setAttribute("listSetting", listSetting);
        request.setAttribute("listSettingType", listSettingType);
        request.setAttribute("listSettingStatus", listSettingStatus);
        request.getRequestDispatcher("jsp/setting.jsp").forward(request, response);
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
