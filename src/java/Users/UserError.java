/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author WilliamTrung
 */
public class UserError {
    private String userIDError;
    private String userNameError;
    private String roleError;
    private String passwordError;
    private String confirmPasswordError;

    public UserError() {
    }

    public UserError(String userIDError, String userNameError, String roleError, String passwordError, String confirmPasswordError) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.roleError = roleError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public String getRoleError() {
        return roleError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }
    
}
