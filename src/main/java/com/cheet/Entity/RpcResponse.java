package com.cheet.Entity;

/**
 * @author yh
 * @date 2022/6/20 下午8:09
 */
public class RpcResponse {


    boolean IsSuccess;

    String requestId;

    Object Data;


    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setData(Object data) {
        Data = data;
    }



    public boolean isSuccess() {
        return IsSuccess;
    }

    public String getRequestId() {
        return requestId;
    }

    public Object getData() {
        return Data;
    }
}
