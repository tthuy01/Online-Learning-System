/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Post;

/**
 *
 * @author ADMIN
 */
public class BlogListServlet extends HttpServlet {

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
            out.println("<title>Servlet Blog_listServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Blog_listServlet at " + request.getContextPath() + "</h1>");
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
        String bid_raw = request.getParameter("bid");
        String key = request.getParameter("searchBlog");
        if (key == null) {
            key = "";
        }
        int bid;
        if (bid_raw == null) {
            bid = 0;
        } else {
            bid = Integer.parseInt(bid_raw);
        }
        PostDAO pd = new PostDAO();
        List<Post> list1 = pd.getPost();
        BlogDAO bd = new BlogDAO();
        List<Blog> listBlog = bd.getBlog();
        Blog b = bd.getBlogByID(bid);
        if (bid != 0 && key.equals("")) {
            list1 = pd.getPostByBlogID(bid);
        }
        if (!key.equals("")) {
            list1 = pd.searchPost(key);
        }
        List<Post> listTop3 = pd.getTop3Post();
        int page;
        int numberpage = 9;
        int size = list1.size();
        int num = (size % 9 == 0 ? (size / 9) : ((size / 9) + 1));
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberpage;
        end = Math.min(page * numberpage, size);
        List<Post> list = pd.getListByPage(list1, start, end);
        request.setAttribute("listTop3", listTop3);
        request.setAttribute("searchBlog", key);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("blogname", b);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("bid", bid);

        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));

        request.getRequestDispatcher("jsp/blog_list.jsp").forward(request, response);

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
