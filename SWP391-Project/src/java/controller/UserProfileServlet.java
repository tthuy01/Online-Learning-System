package controller;

import dal.UserDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UserProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet UserProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileServlet at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("alertUserProfile") != null) {
            request.setAttribute("alertUserProfile", "Saved Change Successfully");
        }
        if (request.getParameter("errorProfile") != null)
            request.setAttribute("alertUserProfile", request.getParameter("errorProfile"));
        request.getRequestDispatcher("jsp/user_profile.jsp").forward(request, response);
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
        String uName = request.getParameter("uname");
        String uPhone = request.getParameter("uphone");
        String uDob = request.getParameter("udob");
        String genId = request.getParameter("genId");
        String uAddress = request.getParameter("uaddress");
        String uWallet_raw = request.getParameter("uwallet");
        String uWallet = uWallet_raw.substring(0, uWallet_raw.length() - 1);

        String uImg = "";
        HttpSession hs = request.getSession();
        UserDAO ud = new UserDAO();
        User u = (User) hs.getAttribute("user");

        if (request.getPart("uimg").getSize() <= 0) { // ko thay doi img
            if (u.getUimg() == null) { // img user ko co
                uImg = "default.jpg";
                hs.removeAttribute("user");
                User uNew = new User(u.getUid(), u.getUemail(), uName, uImg, Integer.parseInt(genId == null ? "4" : genId),
                        uDob, uPhone == null ? null : uPhone, uAddress == null ? null : uAddress, uWallet == null ? "0$" : uWallet, u.getRid());
                ud.updateUser(uNew, u.getUid());
                hs.setAttribute("user", uNew);
                if (request.getParameter("url").split("project/")[1].contains("?")) {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "&alertUserProfile=1");
                } else {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "?alertUserProfile=1");
                }
            } else { // img user co
                hs.removeAttribute("user");
                User uNew = new User(u.getUid(), u.getUemail(), uName, u.getUimg(), Integer.parseInt(genId == null ? "4" : genId),
                        uDob, uPhone == null ? null : uPhone, uAddress == null ? null : uAddress, uWallet == null ? "0$" : uWallet, u.getRid());
                ud.updateUser(uNew, u.getUid());
                hs.setAttribute("user", uNew);
                if (request.getParameter("url").split("project/")[1].contains("?")) {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "&alertUserProfile=1");
                } else {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "?alertUserProfile=1");
                }
            }
        } else { // thay doi img
            final String path = getFolderUploadPath();
            final Part filePart = request.getPart("uimg");
            final String fileName = getFileName(filePart);

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                File f = new File(path + File.separator + fileName);
                if (f.exists()) {
                    if (request.getParameter("url").split("project/")[1].contains("?")) {
                        response.sendRedirect(request.getParameter("url").split("project/")[1] + "&error=Field Image has Existed");
                    } else {
                        response.sendRedirect(request.getParameter("url").split("project/")[1] + "?error=Field Image has Existed");
                    }
                } else {
                    out = new FileOutputStream(f);
                    filecontent = filePart.getInputStream();
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                }

                hs.removeAttribute("user");
                User uNew = new User(u.getUid(), u.getUemail(), uName, fileName, Integer.parseInt(genId == null ? "4" : genId),
                        uDob, uPhone == null ? null : uPhone, uAddress == null ? null : uAddress, uWallet == null ? null : uWallet, u.getRid());
                ud.updateUser(uNew, u.getUid());
                hs.setAttribute("user", uNew);
                TimeUnit.SECONDS.sleep(2);

                if (request.getParameter("url").split("project/")[1].contains("?")) {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "&alertUserProfile=1");
                } else {
                    response.sendRedirect(request.getParameter("url").split("project/")[1] + "?alertUserProfile=1");
                }
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
