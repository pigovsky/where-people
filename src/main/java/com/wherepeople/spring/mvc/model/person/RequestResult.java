package com.wherepeople.spring.mvc.model.person;

import com.wherepeople.spring.mvc.util.WebServiceUtil;

/**
 * Class holding result of person registration
 */
public class RequestResult {
    private String message;
    private boolean success;

    public RequestResult(){}

    public RequestResult(String message) {
        this(message, false);
    }

    public RequestResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

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

    @Override
    public String toString() {
        return WebServiceUtil.GSON.toJson(this);
    }
}
