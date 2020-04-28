/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.controller;

import chinhmt.Product.ProductDAO;
import chinhmt.Product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class UserController extends HttpServlet {

    private final String MARKET_PAGE = "market.jsp";
    private final String UPDATE_USER_ACCOUNT_SERVLET = "update.jsp";
    private final String LOGOUT_SERVLET = "LogoutServlet";

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
        String button = request.getParameter("btAction");
        String url = "";
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {

                if (button.equals("Shopping Now")) {
                    ProductDAO dao = new ProductDAO();
                    dao.getProduct();
                    List<String> product = new ArrayList<>();
                    product = dao.getProductList();
                    request.setAttribute("PRODUCT", product);
                    if (product != null) {
                        url = MARKET_PAGE;
                    }
//                url = VIEW_MARKET_SERVLET;
                } else if (button.equals("Update Profile")) {
                    url = UPDATE_USER_ACCOUNT_SERVLET;
                } else if (button.equals("Log out")) {
                    url = LOGOUT_SERVLET;
                }
            }
        } catch (SQLException e) {
            log("ViewMarketServlet _ SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("ViewMarketServlet _ Naming: " + e.getMessage());
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
