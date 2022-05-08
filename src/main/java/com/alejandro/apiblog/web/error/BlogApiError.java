package com.alejandro.apiblog.web.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogApiError {

    private Integer status;
    private String reason;
    private String message;
    private List<String> validations;
    private boolean success;

    private BlogApiError(Integer status, String message, String reason, List<String> validations) {
        this.setStatus(status);
        this.setMessage(message);
        this.setReason(reason);
        this.setValidations(validations);
        this.setSuccess();
    }

    public static BlogApiError create(Integer status, String message, String reason, List<String> validations) {
        return new BlogApiError(status, message, reason, validations);
    }

    public static BlogApiError create(Integer status, String message, String reason) {
        return new BlogApiError(status, message, reason, null);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("reason", reason);
        map.put("message", message);
        map.put("validations", validations);
        map.put("success", success);
        return map;
    }

    public Integer getStatus() {
        return status;
    }

    private void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public List<String> getValidations() {
        return validations;
    }

    private void setValidations(List<String> validations) {
        this.validations = validations;
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess() {
        this.success = false;
    }

    public String getReason() {
        return reason;
    }

    private void setReason(String reason) {
        this.reason = reason;
    }
}
