/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.SubjectCategoryDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;

/**
 *
 * @author Administrator
 */
public class AddDimensionServlet extends HttpServlet {

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
            out.println("<title>Servlet AddDimensionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDimensionServlet at " + request.getContextPath() + "</h1>");
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
        SubjectCategoryDAO scd = new SubjectCategoryDAO();
        SubjectDAO sd = new SubjectDAO();

        if (request.getParameter("scid") != null) {
            String cid = request.getParameter("cid");
            String scid_raw = request.getParameter("scid");
            
            if (request.getParameter("action").equals("edit")) {
                try {
                    String scname = request.getParameter("scname");
                    int scid = Integer.parseInt(scid_raw);
                    scd.update(scname, scid);
                    response.sendRedirect("subjectdetails?cid=" + cid + "&editSubCat=1&scidEdit="+scid_raw);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

            } else { // truong hop delete
                try {
                    int scid = Integer.parseInt(scid_raw);
                    List<Subject> listSub = sd.getAll();
                    int ok = 1;
                    for (int i = 0; i < listSub.size(); i++) {
                        if (listSub.get(i).getScate() == scid) {
                            ok = 0;
                            break;
                        }
                    }
                    if (ok == 0) {
                        response.sendRedirect("subjectdetails?cid=" + cid + "&deleteSubCat=0");
                    }
                    if (ok == 1) {
                        scd.delete(scid);
                        response.sendRedirect("subjectdetails?cid=" + cid + "&deleteSubCat=1");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        } else {
            String cid = request.getParameter("cid");
            String scname = request.getParameter("scname");
            scd.insertSubjectCategory(scname);
            response.sendRedirect("subjectdetails?cid=" + cid + "&addSubCat=1");
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
