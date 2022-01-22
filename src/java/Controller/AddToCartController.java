/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.MessageSpecified;
import Shopping.CartDTO;
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
public class AddToCartController extends HttpServlet {

    private final String SUCCESS = "shopping.jsp";
    private final String ERROR = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            request.setAttribute("LIST_ITEMS", new ProductDAO().getListProduct(search));
            String id = request.getParameter("productID");
            String name = request.getParameter("productName");
            int quantityAddCart = Integer.parseInt(request.getParameter("quantityToCart"));
            int price = Integer.parseInt(request.getParameter("price"));
            String catagory = request.getParameter("catagory");
            

            HttpSession session = request.getSession();
            OrderDAO o_dao = new OrderDAO();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            int orderID = -1;
            if (session.getAttribute("ORDER_ID")!=null) {
                orderID = (int)session.getAttribute("ORDER_ID");
            }
            List<ProductDTO> items = new ArrayList<>();
            
            if (quantityAddCart != 0) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                ProductDTO item = new ProductDTO(id, price, name, null, catagory, quantityAddCart, null);
                items.add(item);
                if (cart == null) {
                    cart = new CartDTO();
                }

                if (cart.add(item)) {
                    //true if add new item
                    //false if update item
                    url = SUCCESS;
                    if(loginUser!=null){
                        if (!o_dao.createOrderDetails(items, orderID)) {
                        MessageSpecified message = new MessageSpecified(null, "Error", " Cannot create Order Detail!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = ERROR;
                    }
                    }
                    
                } else {
                    url = SUCCESS;
                    if(loginUser!=null){
                    if (!o_dao.updateOrderDetails(items, orderID, "1")) {
                        MessageSpecified message = new MessageSpecified(null, "Error", " Cannot create Order Detail!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = ERROR;
                    }
                    }
                }
                session.setAttribute("CART", cart);
            }
        } catch (Exception e) {
            log("Error at CartController: " + e.toString());
            if (e.toString().contains("ull")) {
                MessageSpecified message = new MessageSpecified(null, "", " Out of stock!");
                request.setAttribute("ERROR_MESSAGE", message);
            }
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
