/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Role;
import model.SendEmail;
import model.User;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UserDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet UserDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        int id;
        if (id_raw == null) {
            id = 0;
        } else {
            id = Integer.parseInt(id_raw);
        }
        UserDAO ud = new UserDAO();
        User u = ud.getUserByUid(id);
        RoleDAO rl = new RoleDAO();
        List<Role> listRole = rl.getAll();
        request.setAttribute("listRole", listRole);
        request.setAttribute("user1", u);
        request.setAttribute("id", id);
        request.getRequestDispatcher("jsp/user_detail.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String id_raw = request.getParameter("id");
        String uName = request.getParameter("name");
        String email = request.getParameter("email");
        String uPhone = request.getParameter("phone");
        String uDob = request.getParameter("dob");
        String genId = request.getParameter("gender");
        String uAddress = request.getParameter("address");
        String uWallet = request.getParameter("wallet");
        String role_raw = request.getParameter("role");
        String status_raw = request.getParameter("status");
        String password = request.getParameter("password");

        int role, id;
        boolean status;
        if (id_raw == null) {
            id = 0;
        } else {
            id = Integer.parseInt(id_raw);
        }
        if (role_raw == null) {
            role = 2;
        } else {
            role = Integer.parseInt(role_raw);
        }
        if (status_raw == null) {
            status = true;
        } else {
            status = Boolean.parseBoolean(status_raw);
        }

        final String path = getFolderUploadPath();
        final Part filePart = request.getPart("img");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            File f = new File(path + File.separator + fileName);
            //System.out.println(path + File.separator + fileName);
            if (f.exists()) {
                writer.println("File " + fileName + " already exist at " + path);
            } else {
                out = new FileOutputStream(f);
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            HttpSession hs = request.getSession();
            UserDAO ud = new UserDAO();
            User u = ud.getUserByUid(id);
            String imagechange;
            if (fileName.equals("")) {
                imagechange = u.getUimg();
            } else {
                imagechange = fileName;
            }

            if (id != 0) {
                User uNew = new User(u.getUid(), u.getUemail(), ud.Encryption(u.getUpassword()), uName, imagechange, Integer.parseInt(genId == null ? "4" : genId),
                        uDob, uPhone == null ? null : uPhone, uAddress == null ? null : uAddress, uWallet == null ? null : uWallet, role, status);
                ud.updateUserAR(uNew, u.getUid());
                request.setAttribute("user1", uNew);
                request.setAttribute("successfull", "Update user detail successfully!");
            } else {
                User u2 = new User(id, email, ud.Encryption(password), uName, imagechange, Integer.parseInt(genId == null ? "4" : genId), uDob, uPhone == null ? null : uPhone, uAddress == null ? null : uAddress, uWallet == null ? null : uWallet, role, status);
                ud.insertUserAR(u2);
                SendEmail se = new SendEmail();
                se.insertUser(email, password);
                request.setAttribute("successfull", "Add new user successfully!");
            }

            request.setAttribute("id", id);
            RoleDAO rl = new RoleDAO();
            List<Role> listRole = rl.getAll();
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("jsp/user_detail.jsp").forward(request, response);
        } catch (FileNotFoundException fne) {
            writer.println("<br/> ERROR: " + fne.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public String getFolderUploadPath() {
        String path = getServletContext().getRealPath("/") + "images";
        String path2 = getServletContext().getRealPath("/").replace("\\build", "") + "images";
        String path3 = path2 + "\\avatar";
        File folderUpload = new File(path);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return path3;
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
