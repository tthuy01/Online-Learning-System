/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.RoleDAO;
import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Role;
import model.Slider;

/**
 *
 * @author Admin
 */
public class SliderListServlet extends HttpServlet {

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
            out.println("<title>Servlet SliderListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderListServlet at " + request.getContextPath() + "</h1>");
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
        String show = request.getParameter("show");
        String hide = request.getParameter("hide");
        String index = request.getParameter("index");
        String side = request.getParameter("side");
        String id = request.getParameter("id");
        String status = "";

        if (search == null) {
            search = "";
        }
        if (show == null) {
            show = "on";
        }
        if (hide == null) {
            hide = "on";
        }
        if (index == null) {
            index = "1";
        }

        if (side == null) {
            side = "show";
        }

        if ((show.equals("on") && hide.equals("on")) || (show.equals("off") && hide.equals("off"))) {
            status = "2";
        }

        if (show.equals("on") && hide.equals("off")) {
            status = "1";
        }

        if (show.equals("off") && hide.equals("on")) {
            status = "0";
        }

        RoleDAO roledao = new RoleDAO();
        BlogDAO bd = new BlogDAO();
        SliderDAO sd = new SliderDAO();

        List<Role> listRole = roledao.getAll();
        List<Blog> listBlog = bd.getBlog();
        int pageSize = 2;
        int size = sd.getNumSlidersByFilter(search, status);
        int numPage = size % pageSize == 0 ? size / pageSize : (size / pageSize) + 1;
        if (id != null) {
            sd.updateStatusById(id);
        }
        List<Slider> listSlide = sd.getSlidersByFilter(search, status, Integer.parseInt(index), pageSize);
        
        request.setAttribute("listRole", listRole);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("listSlide", listSlide);
        request.setAttribute("search", search);
        request.setAttribute("show", show);
        request.setAttribute("hide", hide);
        request.setAttribute("numPage", numPage);
        request.setAttribute("index", index);
        request.setAttribute("side", side);

        request.getRequestDispatcher("jsp/slider_list.jsp").forward(request, response);
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
