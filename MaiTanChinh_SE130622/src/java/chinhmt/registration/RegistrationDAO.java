/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import chinhmt.util.DBHelper;

/**
 *
 * @author ThinkKING
 */
public class RegistrationDAO implements Serializable {

    public int checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select isAdmin From Registration Where Username = ? And Password = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute statement
                rs = stm.executeQuery();
                //5.Process
                if (rs.next()) {
                    if (rs.getBoolean("isAdmin")) {
                        return 1;
                    } else return 2;
                }

            } //end if con is not null
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

        return 0;
    }

    private List<RegistrationDTO> studentList;

    public List<RegistrationDTO> getStudentList() {
        return studentList;
    }

    public void searchLastName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select Username, Password, LastName, isAdmin "
                        + "From Registration "
                        + "Where LastName like ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4.Execute statement
                rs = stm.executeQuery();
                //5.Process
                while (rs.next()) { // su dung while do lay du lieu nhieu dong
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String lastname = rs.getString("LastName");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, lastname, role);
                    if (this.studentList == null) {
                        this.studentList = new ArrayList<RegistrationDTO>();
                    } //end while rs
                    this.studentList.add(dto);
                } //end if con is not null

            } //end if con is not null
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

    public boolean deleteAccount(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Open connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4.Execute statement
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }

            } //end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }

    public boolean updateAccount(String username, String password, boolean role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make Connection 
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql statement
                String sql = "Update Registration Set Password = ?, isAdmin = ? Where Username = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }//end if con 
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public String getLastName(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String lastname = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Select LastName From Registration where Username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    lastname = rs.getString("LastName");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();;
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lastname;
    }

    public boolean createAccount(RegistrationDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Open connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Insert into Registration(Username, Password, Lastname, isAdmin)"
                        + "Values(?, ?, ?, ?)";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastname());
                stm.setBoolean(4, dto.isRole());
                //4.Execute statement
                int row = stm.executeUpdate();
                //5.Process
                if (row > 0) {
                    return true;
                }

            } //end if con is not null
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return false;
    }
    
    public boolean updateUserAccount(String username, String password, String lastname) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update Registration Set Password = ?, LastName = ? Where Username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, lastname);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally{
            if (stm != null) {
                stm.close();
            } if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
