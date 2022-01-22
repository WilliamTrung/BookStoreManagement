/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.MessageSpecified;
import Shopping.CartDTO;
import Shopping.OrderDAO;
import Shopping.ProductDTO;
import Users.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class RemoveItemCartController extends HttpServlet {

    private final String SUCCESS = "viewCart.jsp";
    private final String ERROR = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            if (productID != null) {
                cart.delete(productID);
                url = SUCCESS;
            } else {
                cart = null;
            }
            if (loginUser != null) {
                ProductDTO item = new ProductDTO(productID, 0, null, null, null, 0, null);
                OrderDAO o_dao = new OrderDAO();
                int orderID = (int)session.getAttribute("ORDER_ID");
                List<ProductDTO> itemsToRemove = new ArrayList<>();
                itemsToRemove.add(item);
                if (!o_dao.removeOrderDetails(itemsToRemove, orderID)) {
                    MessageSpecified error = new MessageSpecified("header", "ERROR", " Cannot update database!");
                    request.setAttribute("ERROR_MESSAGE", error);
                }
            }
            session.setAttribute("CART", cart);
        } catch (Exception e) {
            log("Error at RemoveItemCartController");
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
