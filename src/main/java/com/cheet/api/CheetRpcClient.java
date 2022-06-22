package com.cheet.api;

import java.lang.reflect.Method;

/**
 * @author yh
 * @date 2022/6/20 下午8:03
 */
public interface CheetRpcClient {

    void Connect(String addr,int port) throws Exception;

    Object Call(Class cls,String method,Object... arg);
}
