/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.controller;

import chinhmt.registration.RegistrationDAO;
import chinhmt.registration.RegistrationUpdateError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chinh
 */
public class UpdateUserAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "update.jsp";
    private final String USER_PAGE = "user.jsp";

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
        PrintWriter out = response.getWriter();
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtLastname");
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("USERNAME");
        RegistrationUpdateError errors = new RegistrationUpdateError();

        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            if (session != null) {
                if (password.trim().length() < 6 || password.trim().length() > 30) {
                    foundErr = true;
                    errors.setPasswordLengthErr("Password is required from 6 to 20 character");
                } else if (!confirm.trim().equals(password.trim())) {
                    foundErr = true;
                    errors.setConfirmNotMatched("Confirm must match password");
                }
                if (fullname.trim().length() < 6 || fullname.trim().length() > 50) {
                    foundErr = true;
                    errors.setFullnameLengthErr("Full name is required from 6 to 50 characters");
                }
                if (foundErr) {
                    request.setAttribute("UPDATEERRORS", errors);
                } else {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.updateUserAccount(username, password, fullname);
                    if (result) {
                        url = USER_PAGE;
                    }
                }
            }
        } catch (SQLException e) {
            log("UpdateUserAccountServlet _ SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("UpdateUserAccountServlet _ Naming: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
        processRequest(request, response);
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
