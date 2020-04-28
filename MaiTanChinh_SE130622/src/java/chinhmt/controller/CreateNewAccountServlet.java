/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.controller;

import chinhmt.registration.RegistrationCreateError;
import chinhmt.registration.RegistrationDAO;
import chinhmt.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chinh
 */
public class CreateNewAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "create.jsp";
    private final String LOGIN_PAGE = "login.html";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            //1. Verify/check all users errors
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 30 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required from 6 to 20 characters");
            } else if (!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLengthErr("Full name is required from 6 to 50 characters");
            }
            //2. proccess
            if (foundErr) {
                //forward errors  to error page to show
                request.setAttribute("CREATEERRORS", errors);
            } else{
                //call DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
            
        } catch (SQLException ex){
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL: " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed!!!");
                request.setAttribute("CREATEERRORS", errors);
            }
        } catch (NamingException ex){
            log("CreateNewAccountServlet _ Naming: " + ex.getMessage());
        }
        finally {
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
