/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WilliamTrung
 */
public class MainController extends HttpServlet {
    private final String LOGIN="LoginController";
    private final String REGISTER="RegisterController";
    private final String ADMIN_CHANGESTATUS="ChangeStatusController";
    private final String ADMIN_CREATE_PRODUCT="CreateProductController";
    private final String ADMIN_CHANGESTATUS_PRODUCT="ChangeStatusProductController";
    private final String ADMIN_SEARCH_USERS="SearchUserController";
    private final String ADMIN_SEARCH_PRODUCTS="SearchProductController";
    private final String ADMIN_UPDATE="update.jsp";
    private final String ADMIN_UPDATE_PRODUCT="updateProduct.jsp";
    private final String ADMIN_UPDATE_CONFIRMED="UpdateController";
    private final String ADMIN_UPDATE_PRODUCT_CONFIRMED="UpdateProductController";
    private final String USER_VIEW_HISTORY_PURCHASE="LoadOrderController";
    private final String ADMIN_VIEW_HISTORY_PURCHASE="LoadOrderAdminController";
    private final String LOGOUT="LogoutController";
    private final String ERROR="error.jsp";
    private final String VIEW_ORDER_DETAILS="LoadOrderDetailsController";
    private final String SHOPPING_LOAD_ITEMS="LoadItemController";
    private final String SHOPPING_ADD_TO_CART="CartController";
    private final String SHOPPING_UPDATE_CART="UpdateCartController";
    private final String SHOPPING_REMOVE_ITEM_CART="RemoveItemCartController";
    private final String SHOPPING_CHECKOUT="CheckoutController";
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
        String url=ERROR;
        try {
            
            String action=request.getParameter("action");
            if ("Login".equals(action)) {
                url=LOGIN;
            } else if ("More order details".equals(action)) {
                url=VIEW_ORDER_DETAILS;
            } else if ("Search".equals(action)) {
                url=ADMIN_SEARCH_USERS;
            } else if ("View History".equals(action)) {
                url=USER_VIEW_HISTORY_PURCHASE;
            } else if ("View History Admin".equals(action)) {
                url=ADMIN_VIEW_HISTORY_PURCHASE;
            } else if ("Create Product".equals(action)) {
                url=ADMIN_CREATE_PRODUCT;
            } else if ("SearchProduct".equals(action)) {
                url=ADMIN_SEARCH_PRODUCTS;
            } else if ("Update".equals(action)) {
                url=ADMIN_UPDATE;
            } else if ("Confirm Update".equals(action)) {
                url=ADMIN_UPDATE_CONFIRMED;
            } else if ("Modify item".equals(action)) {
                url=ADMIN_UPDATE_PRODUCT_CONFIRMED;
            } else if ("Logout".equals(action)) {
                url=LOGOUT;
            } else if ("Register".equals(action)) {
                url=REGISTER;
            } else if ("ChangeStatus".equals(action)) {
                url=ADMIN_CHANGESTATUS;
            } else if ("ChangeStatusProduct".equals(action)) {
                url=ADMIN_CHANGESTATUS_PRODUCT;
            } else if ("LoadItem".equals(action)) {
                url=SHOPPING_LOAD_ITEMS;
            } else if ("Add to Cart".equals(action)) {
                url=SHOPPING_ADD_TO_CART;
            } else if ("Update Cart".equals(action)) {
                url=SHOPPING_UPDATE_CART;
            } else if ("RemoveItemInCart".equals(action)) {
                url=SHOPPING_REMOVE_ITEM_CART;
            } else if ("Clear Cart".equals(action)) {
                url=SHOPPING_REMOVE_ITEM_CART;
            } else if ("Continue to checkout".equals(action)) {
                url=SHOPPING_CHECKOUT;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "Action is not available");
            }
        } catch (Exception e) {
            log("Error at MainController: "+e.toString());
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
