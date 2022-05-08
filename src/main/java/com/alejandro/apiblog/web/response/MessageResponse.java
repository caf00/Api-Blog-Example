package com.alejandro.apiblog.web.response;

import java.io.Serializable;

public class MessageResponse implements Serializable {

    private String message;
    private boolean success;

    public MessageResponse(String message) {
        this.setMessage(message);
        this.setSuccess();
    }

    public static MessageResponse newInstance(String message) {
        return new MessageResponse(message);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess() {
        this.success = true;
    }
}
