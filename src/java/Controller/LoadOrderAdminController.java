/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.MessageSpecified;
import Shopping.OrderDAO;
import Shopping.OrderDTO;
import Users.UserDAO;
import Users.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WilliamTrung
 */
public class LoadOrderAdminController extends HttpServlet {
private final String SUCCESS = "viewHistoryPurchaseAdmin.jsp";
    private final String ERROR = "viewHistoryPurchaseAdmin.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            OrderDAO o_dao = new OrderDAO();
            UserDAO u_dao = new UserDAO();
            List<UserDTO> users = u_dao.getListUser("");
            List<OrderDTO> list = new ArrayList<>();
            for (UserDTO user : users) {
                list.addAll(o_dao.getOrderByUserID(user.getUserID(), "0"));
                list.addAll(o_dao.getOrderByUserID(user.getUserID(), "1"));
            }
            Collections.sort(list);
                if (list.size()>0) {
                    request.setAttribute("LIST_ORDERS", list);
                    request.setAttribute("LIST_USER", users);
                    url = SUCCESS;
                }
                else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Have not purchase anything!");
                    request.setAttribute("ERROR_MESSAGE", message);
                }           
        } catch (Exception e) {
            log("Error at LoadOrderController: "+e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
