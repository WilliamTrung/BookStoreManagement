/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.MessageSpecified;
import Shopping.OrderDAO;
import Shopping.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WilliamTrung
 */
public class LoadOrderDetailsController extends HttpServlet {
    private final String SUCCESS = "viewOrderDetails.jsp";
    private final String ERROR = "viewOrderDetails.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            OrderDAO o_dao= new OrderDAO();
            List<ProductDTO> list = o_dao.convertOrderToItems(orderID);
            if(list!=null){
                if(list.size()>0){
                    request.setAttribute("LIST_ORDER_DETAILS", list);
                    url = SUCCESS;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", " An Error has occur!");
                    request.setAttribute("ERROR_MESSAGE", message);
                }
            } else {
                MessageSpecified message = new MessageSpecified(null, "Error", "Cannot load from database!");
                request.setAttribute("ERROR_MESSAGE", message);
            }
        } catch (Exception e) {
            log("Error at LoadOrderDetailsController: "+e.toString());
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
