/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.sql.Date;

/**
 *
 * @author WilliamTrung
 */
public class UserDTO implements Comparable<UserDTO>{
    private int userID;
    private String userName;
    private Date userBirthday;
    private String role;
    private String password;
    private String status;  
    public UserDTO(int userID, String userName, Date userBirthday, String role, String password, String status) {
        this.userID = userID;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.role = role;
        this.password = password;
        this.status = status;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
    public UserDTO() {
    }

    @Override
    public int compareTo(UserDTO o) {
        if(this.userID > o.userID)
            return 1;
        else if(this.userID < o.userID)
            return -1;
        else return 0;
    }


    
}
