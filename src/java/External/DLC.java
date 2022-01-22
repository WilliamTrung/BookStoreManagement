/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package External;

import Users.UserError;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author WilliamTrung
 */
public class DLC {

    public static String decodeUTF8(String encode) throws UnsupportedEncodingException {
        //String encode = "Nguyá»n ThÃ nh Trung";
        byte[] bytes = encode.getBytes("ISO-8859-1");
        String decode = new String(bytes);
        return decode;
    }
    public static UserError checkValidInfoUser(String userName, String password, String confirmedPassword) {
        UserError userError = new UserError("", "", "", "", "");
        boolean check = true;

        if (password == null || password.equals("")) {
            password = "********";
        }
        if (confirmedPassword == null || confirmedPassword.equals("")) {
            confirmedPassword = "********";
        }

        if (userName.length() < 5) {
            userError.setUserNameError("Username must be least 5 character");
            check = false;
        }
        if ((password.length() < 8 || password.length() > 12) && !password.equals("********")) {
            userError.setPasswordError("Password must have length between 8 and 12");
            check = false;
        }
        if (!password.equals("********") && !confirmedPassword.equals(password)) {
            userError.setConfirmPasswordError("Confirmed password mismatch!");
            check = false;
        }

        if (check) {
            userError = null;
        }
        return userError;
    }
}
