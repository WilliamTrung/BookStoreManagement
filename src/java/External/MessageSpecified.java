/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package External;

/**
 *
 * @author WilliamTrung
 */
public class MessageSpecified {
    private String id;
    private String errorDetail;
    private String errorMessage;

    public MessageSpecified() {
    }

    public MessageSpecified(String id, String errorDetail, String errorMessage) {
        this.id = id;
        this.errorDetail = errorDetail;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
