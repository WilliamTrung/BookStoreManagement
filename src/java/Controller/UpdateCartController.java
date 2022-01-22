/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Shopping.CartDTO;
import External.MessageSpecified;
import Shopping.OrderDAO;
import Shopping.ProductDAO;
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
public class UpdateCartController extends HttpServlet {

    private final String SUCCESS = "viewCart.jsp";
    private final String ERROR = "viewCart.jsp";

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
        String url = ERROR;
        try {
            //initialize
            OrderDAO o_dao = new OrderDAO();
            String[] items = request.getParameterValues("uri");
            String[] quantities = request.getParameterValues("quantity");
            List<Integer> quantity = new ArrayList<>();
            for (String i : quantities) {
                quantity.add(Integer.parseInt(i));
            }
            List<ProductDTO> itemsInCart = new ArrayList<>();
            List<ProductDTO> itemsToRemove = new ArrayList<>();

            for (int i = 0; i < items.length; i++) {
                 String[] item = items[i].split("-");
                if (quantity.get(i) > 0) {
                    itemsInCart.add(new ProductDTO(item[0], Integer.parseInt(item[2]), item[1], null, item[3], quantity.get(i), null));
                } else {
                    itemsToRemove.add(new ProductDTO(item[0], 0, null, null, null, 0, null));
                }
            }
            //to update cart
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            for (int i = 0; i < itemsInCart.size(); i++) {
                int check = dao.checkQuantity(itemsInCart.get(i));
                //if quantity is valid <--> check = 0
                //if quantity is not valid <--> check = amount items in storage
                if (check == 0) {
                    String productID = itemsInCart.get(i).getProductID();
                    ProductDTO product = itemsInCart.get(i);
                    cart.update(productID, product);
                    url = SUCCESS;
                } else {
                    String id = itemsInCart.get(i).getProductID();
                    ProductDTO product = itemsInCart.get(i);
                    product.setQuantity(check);
                    cart.update(id, product);
                    MessageSpecified error = new MessageSpecified(id, "Invalid quantity", "Max at " + check);
                    request.setAttribute("ERROR_MESSAGE", error);
                    url = ERROR;
                }
            }
            int orderID = -1;
            if (session.getAttribute("ORDER_ID") != null) {
                orderID = (int) session.getAttribute("ORDER_ID");
                if (loginUser != null) {
                    if (!o_dao.updateOrderDetails(itemsInCart, orderID, "1")) {
                        MessageSpecified error = new MessageSpecified("header", "ERROR", " Cannot update database!");
                        request.setAttribute("ERROR_MESSAGE", error);
                    }
                }
            }

            //to remove if quantity = 0
            for (int i = 0; i < itemsToRemove.size(); i++) {
                cart.delete(itemsToRemove.get(i).getProductID());
            }
            if (loginUser != null) {
                if (!o_dao.removeOrderDetails(itemsToRemove, orderID)) {
                    /*
                    MessageSpecified error = new MessageSpecified("header", "ERROR", " Cannot update database!");
                    request.setAttribute("ERROR_MESSAGE", error);
                    */
                }
            }
            session.setAttribute("CART", cart);
        } catch (NumberFormatException e) {
            log("Error at UpdateCartController: " + e.toString());
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
