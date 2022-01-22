/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import Utils.DBUtils;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WilliamTrung
 */
public class ProductDAO {

    private String getCatagoryID(String catagory) {
        String result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT catagoryID "
                        + " FROM tblCatagories "
                        + " WHERE catagoryName = ?";
                stm = conn.prepareStatement(sql);
                //If active then deactive
                stm.setString(1, catagory);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("catagoryID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getCatagoryID in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return result;
    }

    public List<String> getCatagories() {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "SELECT catagoryName "
                        + " FROM tblCatagories";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("catagoryName"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getCatagories in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }

    public boolean changeProductStatus(ProductDTO item) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "UPDATE tblProducts SET statusID=?"
                        + " WHERE productID = ?";
                stm = conn.prepareStatement(sql);
                //If active then deactive
                stm.setString(1, item.getStatus().equals("1") ? "0" : "1");
                stm.setString(2, item.getProductID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at changeProductStatus in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean updateProduct(ProductDTO item) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "UPDATE tblProducts SET productName=?, productPrice = ?, productImage = ?, catagoryID = ?, quantity = ? "
                        + " WHERE productID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, item.getProductName());
                stm.setInt(2, item.getPrice());
                stm.setString(3, item.getImage());
                stm.setString(4, getCatagoryID(item.getCatagory()));
                stm.setInt(5, item.getQuantity());
                stm.setString(6, item.getProductID());

                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateProduct in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT productID, productPrice, productName, productImage,catagoryName, quantity, statusID"
                        + " FROM tblProducts p, tblCatagories c"
                        + " WHERE p.catagoryID = c.catagoryID AND productName like ?";
                stm = conn.prepareStatement(query);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String catagoryName = rs.getString("catagoryName");
                    int price = rs.getInt("productPrice");
                    int quantity = rs.getInt("quantity");
                    String statusID = rs.getString("statusID");
                    if (quantity == 0) {
                        statusID = "0";
                    }
                    String productImage = rs.getString("productImage");
                    ProductDTO product = new ProductDTO(productID, price, productName, productImage, catagoryName, quantity, statusID);
                    list.add(product);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getListProduct in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }

    public int checkQuantity(ProductDTO item) {
        int check = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT quantity, statusID"
                        + " FROM tblProducts"
                        + " WHERE productID like ?";
                stm = conn.prepareStatement(query);
                stm.setString(1, item.getProductID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quantityStorage = rs.getInt("quantity");
                    String statusID = rs.getString("statusID");
                    boolean cp = item.getQuantity() <= quantityStorage;
                    if (statusID.equals("1") && cp) {
                        check = 0;
                    } else {
                        check = quantityStorage;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at checkQuantity in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public int productSold(List<ProductDTO> items) {
        int check = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                for (ProductDTO item : items) {
                    String sql_getStorage = "SELECT quantity "
                            + " FROM tblProducts "
                            + " WHERE productID = ?";
                    stm = conn.prepareStatement(sql_getStorage);
                    stm.setString(1, item.getProductID());
                    rs = stm.executeQuery();

                    int quantityStorage = 0;
                    int quantityRemain = 0;
                    if (rs.next()) {
                        quantityStorage = rs.getInt("quantity");
                        quantityRemain = quantityStorage - item.getQuantity();
                    }
                    if (quantityRemain >= 0) {
                        String sql = "UPDATE tblProducts SET quantity = ? "
                                + " WHERE productID = ?";
                        stm = conn.prepareStatement(sql);
                        stm.setInt(1, quantityRemain);
                        stm.setString(2, item.getProductID());
                        if (stm.executeUpdate() > 0) {
                            check++;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at productSold in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public boolean createProduct(ProductDTO item) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "INSERT INTO tblProducts(productID, productPrice, productName, productImage, catagoryID, quantity, statusID) "
                        + " VALUES(?,?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, item.getProductID());
                stm.setInt(2, item.getPrice());
                stm.setString(3, item.getProductName());
                stm.setString(4, item.getImage());
                stm.setString(5, getCatagoryID(item.getCatagory()));
                stm.setInt(6, item.getQuantity());
                stm.setString(7, item.getStatus());

                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateProduct in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
}
