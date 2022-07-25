/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BlogDAO;
import dal.RoleDAO;
import dal.SliderDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Blog;
import model.Role;
import model.Slider;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class EditSliderServlet extends HttpServlet {

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
            out.println("<title>Servlet EditSliderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSliderServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");

        RoleDAO roledao = new RoleDAO();
        BlogDAO bd = new BlogDAO();
        SliderDAO sd = new SliderDAO();

        List<Role> listRole = roledao.getAll();
        List<Blog> listBlog = bd.getBlog();
        Slider slider = sd.getSliderById(id);

        request.setAttribute("listRole", listRole);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("slider", slider);
        request.setAttribute("mess", request.getParameter("mess"));

        request.getRequestDispatcher("jsp/edit_slider.jsp").forward(request, response);
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
        SliderDAO sd = new SliderDAO();
        String id = request.getParameter("id");
        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part part = request.getPart("image");

        String fileName = part.getSubmittedFileName();
        String root = getServletContext().getRealPath("/");
        String path = root.substring(0, root.length() - 10) + "web\\images\\slider" + File.separator + fileName;
        InputStream is = part.getInputStream();
        uploadFile(is, path);

        String link = request.getParameter("link");
        String note = request.getParameter("note");
        String status = request.getParameter("status");

        if (fileName.equals("")) {
            fileName = request.getParameter("filename");
        }
        String image = fileName;

        String title = request.getParameter("title");

        boolean check;
        if (status.equals("1")) {
            check = true;
        } else {
            check = false;
        }

        Slider newslider = new Slider(Integer.parseInt(id), title, image, link, check, note);
        sd.updateSliderById(newslider);
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException ex) {
            Logger.getLogger(EditSliderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("editslider?id=" + id + "&mess=Edit Successfully");
    }

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        OutputStream out = null;
        InputStream filecontent = null;
        try {
            File f = new File(path);
            out = new FileOutputStream(f);
            filecontent = is;
            int read = 0;
            final byte[] bytes = new byte[1024 * 10];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
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
