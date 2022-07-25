/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.PostDAO;
import dal.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Post;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class AddPostServlet extends HttpServlet {

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
            out.println("<title>Servlet AddPostServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPostServlet at " + request.getContextPath() + "</h1>");
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
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("listBlog", listBlog);
        request.getRequestDispatcher("jsp/addnew_post.jsp").forward(request, response);
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
        String title = request.getParameter("title");
        String category_raw = request.getParameter("category");
        String status_raw = request.getParameter("status");
        String img = request.getParameter("imgvalue");
        String desc = request.getParameter("desc");

        try {
            int bid = Integer.parseInt(category_raw);
            int status = Integer.parseInt(status_raw);
            PostDAO pd = new PostDAO();
            if (desc.equals("")) {
                Post post = new Post(0, img, title, desc, "", Boolean.parseBoolean(status_raw), bid);
                request.setAttribute("post", post);
                request.setAttribute("successfull", "Add Failed, Description can't be left blank!");
            } else if (img.equals("")) {
                Post post = new Post(0, img, title, desc, "", Boolean.parseBoolean(status_raw), bid);
                request.setAttribute("post", post);
                request.setAttribute("successfull", "Add Failed, You haven't selected a photo yet!");
            } else {
                pd.insertPost(img, title, desc, status, bid);
                request.setAttribute("successfull", "Add new Post successfully!");
            }

            BlogDAO bd = new BlogDAO();
            List<Blog> listBlog = bd.getBlog();
            request.setAttribute("listBlog", listBlog);
            RoleDAO roledao = new RoleDAO();
            List<Role> listRole = roledao.getAll();
            request.setAttribute("listRole", listRole);

            request.getRequestDispatcher("jsp/addnew_post.jsp").forward(request, response);
        } catch (NumberFormatException e) {
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
