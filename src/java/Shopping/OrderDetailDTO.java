/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

/**
 *
 * @author WilliamTrung
 */
public class OrderDetailDTO {
    private int detailID;
    private int orderID;
    private String productID;
    private int quantity;
    private String status;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int detailID, int orderID, String productID, int quantity, String status) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.status = status;
    }

    public int getDetailID() {
        return detailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
