package com.sahil.weathertask.data.pojo;

import androidx.annotation.Nullable;

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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof BaseModel) {
            BaseModel model = (BaseModel) obj;
            if (isSuccess() == model.isSuccess()) {
                if (getData() != null && getData().equals(model.getData())) {
                    return true;
                } else if (getThrowable() != null && getThrowable().equals(model.getThrowable())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
