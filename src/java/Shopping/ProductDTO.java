/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import java.io.Serializable;


/**
 *
 * @author WilliamTrung
 */
public class ProductDTO implements Serializable{
    private String productID;
    private int price;
    private String productName;
    private String image;
    private String catagory;
    private int quantity;
    private String status;

    public ProductDTO() {
    }

    public ProductDTO(String productID, int price, String productName,String image ,String catagory, int quantity, String status) {
        this.image=image;
        this.productID = productID;
        this.price = price;
        this.productName = productName;
        this.catagory = catagory;
        this.quantity = quantity;
        this.status = status;
    }

    public String getCatagory() {
        return catagory;
    }

    public int getPrice() {
        return price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCatagoryID(String catagory) {
        this.catagory = catagory;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
