/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class CheckoutController extends HttpServlet {

    private final String SUCCESS ="checkout.jsp";
    private final String ERROR = "checkout.jsp";
    private final String LOGIN = "welcomePage.jsp";
    private UserDTO loginUser = null;
    private String[] items = null;
    private int total = 0;
    private List<ProductDTO> itemsInCart = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            if(loginUser == null){
                url = LOGIN;
            } else {
            //initialize items in cart
            items = request.getParameterValues("uri");         
            total = Integer.parseInt(request.getParameter("total"));
            
            itemsInCart = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                String[] item = items[i].split("-");      
                itemsInCart.add(new ProductDTO(item[0],Integer.parseInt(item[2]), item[1], null, item[3], Integer.parseInt(item[4]), null));
            }
            //purchase
                
                int orderID = -1;
                if(session.getAttribute("ORDER_ID")!=null){
                    orderID=(int)session.getAttribute("ORDER_ID");
                }
                if(orderID != -1){
                    ProductDAO p_dao = new ProductDAO();
                    url=SUCCESS;
                    int item = p_dao.productSold(itemsInCart);
                    OrderDAO o_dao = new OrderDAO();
                    if(item == itemsInCart.size()){
                        o_dao.checkOutOrder(itemsInCart, orderID);
                        request.setAttribute("CHECK_OUT_COMPLETED", "Purchase successfully!");
                        o_dao.createOrder(0, null, loginUser);
                    }                        
                    else {
                        request.setAttribute("ERROR_MESSAGE", "Error occur in database! (Out of stock)");
                    }
                    session.setAttribute("CART", null);
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Failed to purchase!");
                }                           
            }  
        } catch (Exception e) {
            log("Error at PurchaseCotnroller: "+e.toString());
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
