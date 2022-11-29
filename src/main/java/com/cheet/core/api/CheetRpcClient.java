package com.cheet.core.api;

import com.cheet.discovery.ServerDiscovery;

/**
 * @author yh
 * @date 2022/6/20 下午8:03
 */
public interface CheetRpcClient {

    void SetDiscovery(ServerDiscovery discovery) throws Exception;

    Object Call(String cls,String method,Object... arg);
}
