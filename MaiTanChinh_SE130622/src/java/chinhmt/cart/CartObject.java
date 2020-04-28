/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import chinhmt.util.DBHelper;

/**
 *
 * @author Chinh
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public CartObject() {
    }

    public void addItemToCart(String title) {
        if (this.items == null) {
            this.items = new HashMap<String, Integer>();
        }//end if items is not existed
        int quantity = 1;
        if (this.items.containsKey(title)) {
            quantity = this.items.get(title) + 1;
        }
        this.items.put(title, quantity);
    }

    public void removeItemFromCart(String title) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(title)) {
            this.items.remove(title);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void checkOutItemFormCart(String username, String productName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            if (this.items == null) {
                return;
            } else {
                //1. Open connection
                con = DBHelper.getConnection();
                if (con != null) {
                    if (this.items.containsKey(productName)) {
                        //2. Create SQl string
                        String sql = "Insert Cart (Username, ProductName ,Quantity) values(?,?,?)";
                        //3. Create statement
                        stm = con.prepareStatement(sql);
                        stm.setString(1, username);
                        stm.setString(2, productName);
                        stm.setInt(3, this.items.get(productName));
                        stm.executeUpdate();
                        this.items.remove(productName);
                        if (this.items.isEmpty()) {
                            this.items = null;
                        }
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
