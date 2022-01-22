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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author WilliamTrung
 */
public class CartDAO {

    public CartDTO getCartFromPreviousOrder(int orderID) {
        List<ProductDTO> list = null;
        CartDTO cart = null;
        OrderDAO o_dao = new OrderDAO();
        list = o_dao.convertOrderToItems(orderID);
        if (!list.isEmpty()) {
            cart = new CartDTO();
            for (ProductDTO productDTO : list) {
                cart.add(productDTO);
            }
        }

        return cart;
    }

    public List<ProductDTO> convertCartToList(CartDTO cart) {
        List<ProductDTO> list = new ArrayList<>();
        for (Map.Entry<String, ProductDTO> item : cart.getCart().entrySet()) {
            String key = item.getKey();
            ProductDTO value = item.getValue();
            list.add(value);
        }        
        return list;
    }
}
