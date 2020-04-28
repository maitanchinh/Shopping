/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinhmt.registration;

/**
 *
 * @author Chinh
 */
public class RegistrationCreateError {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullnameLengthErr;
    private String confirmNotMatched;
    private String usernameIsExisted;
    private String usernameIsNotExisted;
    private String passwordNotMatched;

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getUsernameIsNotExisted() {
        return usernameIsNotExisted;
    }

    public void setUsernameIsNotExisted(String usernameIsNotExisted) {
        this.usernameIsNotExisted = usernameIsNotExisted;
    }

    public String getPasswordNotMatched() {
        return passwordNotMatched;
    }

    public void setPasswordNotMatched(String passwordNotMatched) {
        this.passwordNotMatched = passwordNotMatched;
    }
    
}
