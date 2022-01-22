/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import Users.UserDTO;
import Utils.DBUtils;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WilliamTrung
 */
public class OrderDAO {

    public boolean checkOutOrder(List<ProductDTO> items, int orderID) {
        boolean check = false;
        int total = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblOrderDetails "
                    + " SET statusID = ? "
                    + " WHERE orderID = ?";
            stm = conn.prepareStatement(sql);
            for (ProductDTO item : items) {
                stm.setString(1, "0");
                stm.setInt(2, orderID);
                check = stm.executeUpdate() > 0;
                total += item.getPrice() * item.getQuantity();
            }

            sql = "UPDATE tblOrders "
                    + " SET statusID = ?, orderTotal = ? "
                    + " WHERE orderID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "0");
            stm.setInt(2, total);
            stm.setInt(3, orderID);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
            log("Error at checkOutOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
    public boolean updateOrderDetails(List<ProductDTO> items, int orderID, String statusID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblOrderDetails "
                    + " SET quantity=?, price=?, statusID = ? "
                    + " WHERE orderID = ?";
            stm = conn.prepareStatement(sql);
            for (ProductDTO item : items) {
                stm.setInt(1, item.getQuantity());
                stm.setInt(2, item.getPrice());
                stm.setString(3, statusID);
                stm.setInt(4, orderID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at updateOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
    public boolean removeOrderDetails(List<ProductDTO> items, int orderID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM tblOrderDetails "
                    + " WHERE productID = ? AND orderID = ?";
            stm = conn.prepareStatement(sql);
            for (ProductDTO item : items) {
                stm.setString(1, item.getProductID());
                stm.setInt(2, orderID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at updateOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public int getPreviousOrder(UserDTO loginUser) {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT orderID "
                    + " FROM tblOrders "
                    + " WHERE userID = ? AND statusID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, loginUser.getUserID());
            stm.setInt(2, 1);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = rs.getInt("orderID");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getPreviousOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public boolean createOrderDetails(List<ProductDTO> items, int orderID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblOrderDetails(orderID, productID, quantity, price, statusID) "
                    + " VALUES(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            for (ProductDTO item : items) {
                stm.setInt(1, orderID);
                stm.setString(2, item.getProductID());
                stm.setInt(3, item.getQuantity());
                stm.setDouble(4, item.getPrice());
                stm.setInt(5, 1);
                stm.executeUpdate();
                check = true;
            }
        } catch (Exception e) {
            log("Error at createOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public int createOrder(int total, List<ProductDTO> items, UserDTO user) {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblOrders(userID, orderDate, orderTotal, statusID) "
                    + " VALUES(?,?,?,?)";
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, user.getUserID());
            stm.setDate(2, new Date(System.currentTimeMillis()));
            stm.setDouble(3, total);
            stm.setInt(4, 1);
            if (stm.executeUpdate() > 0) {
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int orderID = rs.getInt(1);
                    createOrderDetails(items, orderID);
                    check = orderID;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at createOrder() in OrderDAO" + e.toString());
        } finally {
             DBUtils.closeQueryConnection(conn, stm, rs);
          }
         return check;
    }

//for viewing history purchase
    public List<OrderDTO> getOrderByUserID(int userID, String statusID) {
        List<OrderDTO> order = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, orderDate, orderTotal "
                        + " FROM tblOrders o, tblStatus s "
                        + " WHERE o.statusID = s.statusID AND userID = ? AND o.statusID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setString(2, statusID);
                rs = stm.executeQuery();
                order = new ArrayList<>();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    int total = rs.getInt("orderTotal");
                    order.add(new OrderDTO(orderID, userID, orderDate, total, statusID));
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return order;
    }

    public List<ProductDTO> convertOrderToItems(int orderID) {
        List<ProductDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT o.productID, p.productName, p.productImage, c.catagoryName, o.quantity, o.price, o.statusID "
                        + " FROM tblOrderDetails o, tblProducts p, tblCatagories c"
                        + " WHERE orderID = ? AND o.productID = p.productID AND c.catagoryID = p.catagoryID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    String statusID = rs.getString("statusID");
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("statusID");
                    String catagory = rs.getString("catagoryName");
                    list.add(new ProductDTO(productID, price, productName, productImage, catagory, quantity, statusID));
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }
}
