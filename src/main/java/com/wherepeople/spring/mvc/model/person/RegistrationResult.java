package com.wherepeople.spring.mvc.model.person;

/**
 * Class holding result of person registration
 */
public class RegistrationResult {
    String message;
    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
