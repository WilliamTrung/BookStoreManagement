/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author WilliamTrung
 */
public class CartDTO {
    private Map<String,ProductDTO>cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> list) {
        this.cart = list;
    }
    public boolean add(ProductDTO item) {
            boolean newItem = true;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(item.getProductID())) {
            newItem=false;
            int currentQuantity = (int)this.cart.get(item.getProductID()).getQuantity();
            item.setQuantity(currentQuantity + item.getQuantity());
        }
        cart.put(item.getProductID(), item);
        return newItem;
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, ProductDTO item) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, item);
        }
    }
}
