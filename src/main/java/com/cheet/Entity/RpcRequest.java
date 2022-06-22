package com.cheet.Entity;

import com.cheet.RequestIdGenerate;

import java.util.UUID;

/**
 * @author yh
 * @date 2022/6/20 下午8:09
 */
public class RpcRequest {
    private String requestId;

    private byte MethodLen;

    private String Method;

    private  byte ReqidLen;

    private byte ArgsByteLen;

    private Object[] args;

    public RpcRequest() {
       this.requestId=UUID.randomUUID().toString();
    }

    public void setReqidLen(byte reqidLen) {
        ReqidLen = reqidLen;
    }

    public void setRequestId(String requestId) {

         this.requestId = requestId;
    }

    public byte getReqidLen() {
        return ReqidLen;
    }

    public RpcRequest(byte methodLen, String method, byte argsByteLen, Class[] args) {
        MethodLen = methodLen;
        Method = method;
        this.requestId=UUID.randomUUID().toString();
        ArgsByteLen = argsByteLen;
        this.args = args;
    }



    public void setMethodLen(byte methodLen) {
        MethodLen = methodLen;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public void setArgsByteLen(byte argsByteLen) {
        ArgsByteLen = argsByteLen;
    }

    public void setArgs(Object... args) {
        this.args = args;
    }

    public String getRequestId() {
        return requestId;
    }

    public byte getMethodLen() {
        return MethodLen;
    }

    public String getMethod() {
        return Method;
    }

    public byte getArgsByteLen() {
        return ArgsByteLen;
    }

    public Object[] getArgs() {
        return args;
    }
}
