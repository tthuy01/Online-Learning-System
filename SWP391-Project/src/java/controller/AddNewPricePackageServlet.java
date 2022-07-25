/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.PriceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Price;

/**
 *
 * @author Tran Thi Thanh Thuy
 */
public class AddNewPricePackageServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewPricePackageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewPricePackageServlet at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("cid", request.getParameter("cid"));
        request.getRequestDispatcher("jsp/add_new_price_package.jsp").forward(request, response);
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
        request.setAttribute("cid", cid);
        String name = request.getParameter("name");
        String duration_raw = request.getParameter("duration");
        String status_raw = request.getParameter("status");
        String mul_raw = request.getParameter("mul");
        String des = request.getParameter("des");
        request.setAttribute("name", name);
        request.setAttribute("duration", duration_raw);
        request.setAttribute("status", status_raw);
        request.setAttribute("mul", mul_raw);
        request.setAttribute("des", des);
        if (name == null || name.trim().equals("") || duration_raw == null || status_raw.equals("-1") || des == null || des.trim().equals("")) {
            request.setAttribute("error", "You must fill all of fields!");
            request.getRequestDispatcher("jsp/add_new_price_package.jsp").forward(request, response);
        } else {
            int duration = 0;
            float mul = 0;
            boolean status = true;
            if (status_raw.equals("0")) {
                status = false;
            }
            try {
                duration = Integer.parseInt(duration_raw);
                mul = Float.parseFloat(mul_raw);
                PriceDAO pdao = new PriceDAO();
                Price p = new Price(0, name, duration, status, mul, des);
                pdao.InsertNewPricePackage(p);
                request.setAttribute("ms", "Add new price package successfully!");
                request.getRequestDispatcher("jsp/add_new_price_package.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Duration or multiple is invalid!");
                request.getRequestDispatcher("jsp/add_new_price_package.jsp").forward(request, response);
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
