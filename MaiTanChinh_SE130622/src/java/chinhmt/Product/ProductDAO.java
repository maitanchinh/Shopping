/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.Product;

import chinhmt.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Chinh
 */
public class ProductDAO {

    private List<String> typeList;

    public List<String> getTypeList() {
        return typeList;
    }

    public void getType() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select distinct ProductType from Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String type = rs.getString("ProductType");
                    if (this.typeList == null) {
                        this.typeList = new ArrayList<String>();
                    }
                    this.typeList.add(type);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private List<String> productList;

    public List<String> getProductList() {
        return productList;
    }

    public void getProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select ProductName from Product";
                stm = con.prepareStatement(sql);
//                stm.setString(1, type);
                rs = stm.executeQuery();
                while (rs.next()) {                    
//                    int id = rs.getInt("ProductID");
                    String name = rs.getString("ProductName");
//                    int price = rs.getInt("Price");
//                    int quantity = rs.getInt("Quantity");
//                    ProductDTO dto = new ProductDTO(id, name, price, quantity);
                    if (this.productList == null) {
                        this.productList = new ArrayList<String>();
                    }
                    this.productList.add(name);
                }
            }

        } finally{
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
