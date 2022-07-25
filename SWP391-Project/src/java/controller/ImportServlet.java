/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class ImportServlet extends HttpServlet {

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
            out.println("<title>Servlet ImportServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImportServlet at " + request.getContextPath() + "</h1>");
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
//        String cid = request.getParameter("cid");
//        QuizDAO qd = new QuizDAO();
//        request.setAttribute("listQuiz", qd.getListQuizByCID(cid));
//        request.getRequestDispatcher("jsp/question_import.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        Part part = request.getPart("file");
        InputStream input = part.getInputStream();
        String cid = request.getParameter("cid");
        String qid = request.getParameter("qid");

        QuestionDAO quesdao = new QuestionDAO();
        String err = "";
        if (quesdao.getNumQuestionByQid(qid) >= 10) {
            err = "This quiz of subject includes questions before!";
        } else {
            try {
                XSSFWorkbook wb = new XSSFWorkbook(input);//creating Workbook instance that refers to .xlsx file  
                XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file
                itr.next();
                while (itr.hasNext()) {
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                    String content = "";
                    int result = 0;
                    String note = "";
                    int count = 0;
                    while (cellIterator.hasNext()) {
                        count++;
                        Cell cell = cellIterator.next();
                        switch (cell.getCellTypeEnum()) {
                            case STRING:    //field that represents string cell type  
                                if (count <= 5) {
                                    content += cell.getStringCellValue() + " | ";
                                }
                                if (count == 6) {
                                    switch (cell.getStringCellValue().toLowerCase()) {
                                        case "a":
                                            result = 1;
                                            break;
                                        case "b":
                                            result = 2;
                                            break;
                                        case "c":
                                            result = 3;
                                            break;
                                        case "d":
                                            result = 4;
                                            break;
                                    }
                                }
                                if (count == 7) {
                                    note = cell.getStringCellValue();
                                }
                                break;
                            case NUMERIC:    //field that represents number cell type  
                                if (count <= 5) {
                                    content += cell.getNumericCellValue() + " | ";
                                }
                                break;
                            default:
                        }
                    }
                    content = content.substring(0, content.length() - 3);// fix format
                    quesdao.importQuestions(content, result, note, Integer.parseInt(qid));// import question
                    err = "You have already added questions for this quiz!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("queslist?subid=" + cid + "&err=" + err);
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
