package com.alejandro.apiblog.web.response;

import java.io.Serializable;

public class SingleResponse<T> implements Serializable {
    private T data;
    private boolean success;

    public SingleResponse(T data) {
        this.setData(data);
        this.setSuccess();
    }

    public static <T> SingleResponse<T> newInstance(T data) {
        return new SingleResponse<>(data);
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess() {
        this.success = true;
    }
}

