/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.controller;

import chinhmt.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chinh
 */
public class CheckOutServlet extends HttpServlet {

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
        try {
            //1. User goes to carts place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. User takes cart
                String username = (String) session.getAttribute("USERNAME");
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. User gets all item
                   String[] items = request.getParameterValues("chkItem");
                    if (items != null) {
                        for (String item : items) {
                            cart.checkOutItemFormCart(username, item);
                        }
                        //5. Update cart to Scope
                        session.setAttribute("CART", cart);
                    }
                }
            }
        } catch(NamingException ex){
            log("CheckOutServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckOutServlet _ SQL: " + ex.getMessage());
        }finally {
            String urlRewritting = "DispatcherController"
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewritting);
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
