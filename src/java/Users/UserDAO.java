/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import External.DLC;
import Utils.DBUtils;
import java.io.UnsupportedEncodingException;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WilliamTrung
 */
public class UserDAO {

    private int getStatusID(String statusName) {
        int result = 0;
        if (statusName.equals("Active")) {
            result = 1;
        }
        return result;
    }

    public boolean changeUserStatus(UserDTO user) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = "UPDATE tblUsers SET statusID=?"
                        + " WHERE userID = ?";
                stm = conn.prepareStatement(sql);
                //If active then deactive
                stm.setInt(1, this.getStatusID(user.getStatus()) == 1 ? 0 : 1);
                stm.setInt(2, user.getUserID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.changeActivation" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    private String getStatusName(int statusID) {
        String statusName = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                conn = DBUtils.getConnection();
            }
            if (conn != null) {
                String sql = " SELECT statusName FROM tblStatus"
                        + " WHERE statusID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, statusID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    statusName = rs.getString("statusName");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getStatusName" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return statusName;
    }

    public String getRoleID(String roleName) {
        String roleID = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = " SELECT roleID FROM tblRoles"
                        + " WHERE roleName = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, roleName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    roleID = rs.getString("roleID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getRoleID" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        if (roleID == null) {
            if (roleName.equals("ad")) {
                roleID = roleName;
            } else if (roleName.equals("us")) {
                roleID = roleName;
            }
        }
        return roleID;
    }

    public UserDTO checkLogin(String userName, String password) {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            userName = DLC.decodeUTF8(userName);
            if (conn != null) {
                String sql = " SELECT userID, userName, userBirthday, roleID, statusID FROM tblUsers "
                        + " WHERE userName = ? and password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String roleID = rs.getString("roleID");
                    Date birthday = rs.getDate("userBirthday");
                    int statusID = rs.getInt("statusID");
                    if (statusID == 1) {
                        user = new UserDTO(userID, userName, birthday, roleID, "", this.getStatusName(statusID));
                    }
                }
            }
        } catch (UnsupportedEncodingException | ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.checkLogin" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return user;
    }

    public List<UserDTO> getListUser(String search) {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                String sql = " SELECT userID, userName, userBirthday, roleName, u.statusID FROM tblUsers u,tblRoles r "
                        + "WHERE u.roleID=r.roleID "
                        + " AND userName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("userID");
                    String roleName = rs.getString("roleName");
                    Date birthday = rs.getDate("userBirthday");
                    String username = rs.getString("userName");
                    int statusID = rs.getInt("statusID");
                    UserDTO user = new UserDTO(userID, username, birthday, roleName, "********", this.getStatusName(statusID));
                    list.add(user);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getListUser" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        //sort by Role
        list.sort((u1, u2) -> {
            if (((UserDTO) u1).getRole().charAt(0) == 'A' && ((UserDTO) u2).getRole().charAt(0) == 'A') {
                return 0;
            }
            if (((UserDTO) u1).getRole().charAt(0) == 'A' && ((UserDTO) u2).getRole().charAt(0) != 'A') {
                return -1;
            } else {
                return 1;
            }
            //To change body of generated lambdas, choose Tools | Templates.
        });
        return list;
    }

    public boolean updateUser(UserDTO user) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (user.getPassword().equals("********")) {
                    String sql = "UPDATE tblUsers SET userName=?, roleID=? ,userBirthday=?"
                            + " WHERE userID = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, user.getUserName());
                    stm.setString(2, this.getRoleID(user.getRole()));
                    stm.setString(3, user.getUserBirthday().toString());
                    stm.setInt(4, user.getUserID());
                } else {
                    String sql = "UPDATE tblUsers SET userName=?, roleID=? ,userBirthday=?, password=?"
                            + " WHERE userID = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, user.getUserName());
                    stm.setString(2, this.getRoleID(user.getRole()));
                    stm.setString(3, user.getUserBirthday().toString());
                    stm.setString(4, user.getPassword());
                    stm.setInt(5, user.getUserID());
                }
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateUser in UserDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean createUser(UserDTO user) throws Exception{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = "INSERT INTO tblUsers(userName, userBirthday, password, roleID, statusID) "
                    + " VALUES(?,?, ?,'us','1')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserName());
                stm.setDate(2, user.getUserBirthday());
                stm.setString(3, user.getPassword());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            if(e.getMessage().contains("UNIQUE KEY")){
                throw new Exception(e);
            }
            log("Error at updateUser in UserDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
}
