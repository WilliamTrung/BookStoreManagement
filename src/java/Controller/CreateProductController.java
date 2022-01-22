/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.MessageSpecified;
import Shopping.ProductDAO;
import Shopping.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WilliamTrung
 */
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "createProduct.jsp";
    private static final String SUCCESS = "SearchProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String productImage = request.getParameter("productImage");
            int price = Integer.parseInt(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String catagory = request.getParameter("catagory");
            boolean checkInfo = true;
            if (productID.trim().length() < 3 || productID.trim().length() > 8) {
                request.setAttribute("ERROR_MESSAGE", new MessageSpecified("id", "Too short or long -", "ID must be more than 2 and less than 8 characters!"));
                checkInfo = false;
            }
            if (productID.trim().length() > 5) {
                request.setAttribute("ERROR_MESSAGE", new MessageSpecified("name", "Too short or long -", "Name must be more than 5 characters!"));
                checkInfo = false;
            }
            if (checkInfo) {
                ProductDTO item = new ProductDTO(productID, price, productName, productImage, catagory, quantity, "1");
                ProductDAO p_dao = new ProductDAO();
                boolean check = p_dao.createProduct(item);
                if (check) {
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at CreateProductController: " + e.toString());
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
