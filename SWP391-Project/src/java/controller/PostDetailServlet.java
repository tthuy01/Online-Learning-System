/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.PostDAO;
import dal.RoleDAO;
import dal.UserDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Blog;
import model.Post;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
public class PostDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet PostDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostDetailServlet at " + request.getContextPath() + "</h1>");
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
        String pid_raw = request.getParameter("pid");
        try {
            int pid = Integer.parseInt(pid_raw);
            PostDAO pd = new PostDAO();
            Post p = pd.getPostByID(pid);
            BlogDAO bd = new BlogDAO();
            List<Blog> listBlog = bd.getBlog();
            request.setAttribute("post", p);
            request.setAttribute("listBlog", listBlog);
            RoleDAO roledao = new RoleDAO();
            List<Role> listRole = roledao.getAll();
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("jsp/post_detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
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
        request.setCharacterEncoding("UTF-8");
        String pid_raw = request.getParameter("pid");
        String title = request.getParameter("title");
        String category_raw = request.getParameter("category");
        String status_raw = request.getParameter("status");
        String img = request.getParameter("imgvalue");
        String desc = request.getParameter("desc");

        try {
            int bid = Integer.parseInt(category_raw);
            int status = Integer.parseInt(status_raw);
            int pid = Integer.parseInt(pid_raw);
            PostDAO pd = new PostDAO();
            if (desc.equals("")) {
                Post p = pd.getPostByID(pid);
                Post post = new Post(pid, img, title, desc, p.getPodate(), Boolean.parseBoolean(status_raw), bid);
                request.setAttribute("post", post);
                request.setAttribute("successfull", "Update Failed, Description can't be left blank!");
            } else {
                pd.updatePost(pid, img, title, desc, status, bid);
                request.setAttribute("successfull", "Update post detail successfully!");
                Post p = pd.getPostByID(pid);
                request.setAttribute("post", p);
            }

            BlogDAO bd = new BlogDAO();
            List<Blog> listBlog = bd.getBlog();
            RoleDAO roledao = new RoleDAO();
            List<Role> listRole = roledao.getAll();
            request.setAttribute("listRole", listRole);
            request.setAttribute("listBlog", listBlog);
            request.getRequestDispatcher("jsp/post_detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        }
    }

//        try {
//            int bid = Integer.parseInt(category_raw);
//            int status = Integer.parseInt(status_raw);
//            int pid = Integer.parseInt(pid_raw);
//            PostDAO pd = new PostDAO();
//            if (desc.equals("")) {
//                Post p = pd.getPostByID(pid);
//                Post post = new Post(pid, img, title, desc, p.getPodate(), Boolean.parseBoolean(status_raw), bid);
//                request.setAttribute("post", post);
//                request.setAttribute("successfull", "Update Failed, Description can't be left blank!");
//            } else {
////                if (img.substring(0, 5).equals("https")) {
////                    pd.updatePost(pid, img.substring(12), title, desc, status, bid);
////                } else {
////                    final String path = getFolderUploadPath();
////                    final Part filePart = request.getPart("img");
////                    final String fileName = getFileName(filePart);
////
////                    OutputStream out = null;
////                    InputStream filecontent = null;
////                    final PrintWriter writer = response.getWriter();
////
////                    try {
////                        File f = new File(path + File.separator + fileName);
////                        //System.out.println(path + File.separator + fileName);
////                        if (f.exists()) {
////                            writer.println("File " + fileName + " already exist at " + path);
////                        } else {
////                            out = new FileOutputStream(f);
////                            filecontent = filePart.getInputStream();
////
////                            int read = 0;
////                            final byte[] bytes = new byte[1024];
////
////                            while ((read = filecontent.read(bytes)) != -1) {
////                                out.write(bytes, 0, read);
////                            }
////                        }
////
////                        pd.updatePost(pid, fileName, title, desc, status, bid);
////                    } catch (FileNotFoundException fne) {
////                        writer.println("<br/> ERROR: " + fne.getMessage());
////                    } catch (Exception e) {
////                        System.out.println(e);
////                    } finally {
////                        if (out != null) {
////                            out.close();
////                        }
////                        if (filecontent != null) {
////                            filecontent.close();
////                        }
////                        if (writer != null) {
////                            writer.close();
////                        }
////                    }
////                }
//                
//                pd.updatePost(pid, img.substring(12), title, desc, status, bid);
//                Post p = pd.getPostByID(pid);
//                request.setAttribute("post", p);
//                request.setAttribute("successfull", "Update post detail successfully!");
//            }
//
//            BlogDAO bd = new BlogDAO();
//            List<Blog> listBlog = bd.getBlog();
//            request.setAttribute("listBlog", listBlog);
//            request.getRequestDispatcher("jsp/post_detail.jsp").forward(request, response);
//        } catch (NumberFormatException e) {
//        }
//    private String getFileName(final Part part) {
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(
//                        content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
//
//    public String getFolderUploadPath() {
//        String path = getServletContext().getRealPath("/") + "images";
//        String path2 = getServletContext().getRealPath("/").replace("\\build", "") + "images";
//        String path3 = path2 + "\\post";
//        File folderUpload = new File(path);
//        if (!folderUpload.exists()) {
//            folderUpload.mkdirs();
//        }
//        return path3;
//    }
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
