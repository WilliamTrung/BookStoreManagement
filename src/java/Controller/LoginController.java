/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Shopping.CartDAO;
import Shopping.CartDTO;
import Shopping.OrderDAO;
import Shopping.ProductDAO;
import Users.UserDAO;
import Users.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN_PAGE = "adminUserManagement.jsp";
    private static final String SHOPPING_PAGE = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            CartDAO c_dao = new CartDAO();
            OrderDAO o_dao = new OrderDAO();
            ProductDAO p_dao = new ProductDAO();
            UserDTO user = dao.checkLogin(userName, password);
            HttpSession session = request.getSession();
            if (user != null) {
                String roleID = user.getRole();

                if ("ad".equals(roleID)) {
                    url = ADMIN_PAGE;
                    session.setAttribute("LOGIN_USER", user);
                    request.setAttribute("LIST_USER", dao.getListUser(""));
                } else if ("us".equals(roleID)) {
                    url = SHOPPING_PAGE;
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    if (cart == null) {
                        int orderID = o_dao.getPreviousOrder(user);
                        if (orderID == -1) {
                            session.setAttribute("ORDER_ID", o_dao.createOrder(0, null, user));
                        } else {
                            cart = c_dao.getCartFromPreviousOrder(orderID);
                            session.setAttribute("CART", cart);
                            session.setAttribute("ORDER_ID", orderID);

                        }
                    } else {
                        //in case user were pushed back to login because of check out, meaning cart is not empty
                        //load data to database
                        int orderID = o_dao.getPreviousOrder(user);
                        if (orderID == -1) {
                            orderID = o_dao.createOrder(0, null, user);
                            session.setAttribute("ORDER_ID", orderID);
                            o_dao.createOrderDetails(c_dao.convertCartToList(cart), orderID);
                        } else {
                            session.setAttribute("ORDER_ID", orderID);
                            o_dao.createOrderDetails(c_dao.convertCartToList(cart), orderID);
                        }
                        
                    }

                    session.setAttribute("LOGIN_USER", user);
                    request.setAttribute("LIST_ITEMS", p_dao.getListProduct(""));
                } else {
                    session.setAttribute("ERROR_MESSAGE", "Role is not specific!");
                }
            } else {
                session.setAttribute("ERROR_MESSAGE", "Incorrect username or password!");
            }
        } catch (Exception e) {
            log("error at login");
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
