package com.cheet.api;

import com.cheet.discovery.ServerDiscovery;

import java.lang.reflect.Method;

/**
 * @author yh
 * @date 2022/6/20 下午8:03
 */
public interface CheetRpcClient {

    void Connect(ServerDiscovery discovery) throws Exception;

    Object Call(String cls,String method,Object... arg);
}
