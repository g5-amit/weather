package com.sahil.weathertask.data.pojo;

public class BaseModel<T> {
    private boolean success;
    private T data;
    private Throwable throwable;

    public BaseModel(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BaseModel(boolean success, Throwable throwable) {
        this.success = success;
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
