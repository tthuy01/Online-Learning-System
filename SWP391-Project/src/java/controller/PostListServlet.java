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
public class PostListServlet extends HttpServlet {

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
            out.println("<title>Servlet PostListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostListServlet at " + request.getContextPath() + "</h1>");
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
        String page_raw = request.getParameter("page");
        String bid_raw = request.getParameter("bid");
        String key = request.getParameter("key");
        String pid_raw = request.getParameter("pid");
        String hide_raw = request.getParameter("hide");
        int page, bid;
        PostDAO pd = new PostDAO();
        BlogDAO bd = new BlogDAO();

        int hide = 0;
        if (hide_raw != null) {
            hide = Integer.parseInt(hide_raw);
        }
        if (pid_raw != null) {
            int pid = Integer.parseInt(pid_raw);
            pd.updateStatusPost(hide, pid);
        }
        List<Blog> listBlog = bd.getBlog();
        if (key == null) {
            key = "";
        }
        if (page_raw == null) {
            page = 1;
        } else {
            page = Integer.parseInt(page_raw);
        }
        if (bid_raw == null) {
            bid = 0;
        } else {
            bid = Integer.parseInt(bid_raw);
        }
        List<Post> listPost = pd.getListPostByPage(page, bid, key);
        int size_du = pd.getCountNumber(bid, key) % 10;
        int size;
        if (size_du == 0) {
            size = pd.getCountNumber(bid, key) / 10;
            request.setAttribute("endPage", size);
        } else {
            size = pd.getCountNumber(bid, key) / 10 + 1;
            request.setAttribute("endPage", size);
        }

        request.setAttribute("listPost", listPost);
        request.setAttribute("page", page);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("key", key);
        request.setAttribute("bid", bid);
        RoleDAO roledao = new RoleDAO();
        List<Role> listRole = roledao.getAll();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("jsp/post_list.jsp").forward(request, response);
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
