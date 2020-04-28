/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.controller;

import chinhmt.registration.RegistrationDAO;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chinh
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";
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
        String button = request.getParameter("btAction");
        String url = INVALID_PAGE;
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        try {
            //out.println("Username: " + username + " - " + password + " - " + button);
            //System.out.println("Username: " + username + " - " + password + " - " + button);
            //if (button.equals("Login")) {
            String username = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            //Call DAO (new obj va call method)
            RegistrationDAO dao = new RegistrationDAO();
            int result = dao.checkLogin(username, password);

            //process result
            if (result == 1) {
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if (!username.equals(cookie.getName())) {
                            cookie = new Cookie(username, password);
                            cookie.setMaxAge(60 * 3);
                            response.addCookie(cookie);
                        }
                    }
                }
                url = SEARCH_PAGE;

                HttpSession session = request.getSession(true);
                session.setAttribute("USERNAME", username);
                String lastname = dao.getLastName(username);
                session.setAttribute("LASTNAME", lastname);
            } else if (result == 2) {
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if (!username.equals(cookie.getName())) {
                            cookie = new Cookie(username, password);
                            cookie.setMaxAge(60 * 3);
                            response.addCookie(cookie);
                        }
                    }
                }
                url = USER_PAGE;

                HttpSession session = request.getSession(true);
                session.setAttribute("USERNAME", username);
                String lastname = dao.getLastName(username);
                session.setAttribute("LASTNAME", lastname);
            }

            //} //end if button is Login
        } catch (SQLException ex) {
            log("LoginServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming : " + ex.getMessage());
        } finally {
            //response.sendRedirect(url);
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
