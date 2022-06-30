package com.cheet.core.api.impl;

import com.cheet.core.api.CheetRpcClient;
import com.cheet.discovery.ServerDiscovery;

/**
 * @author yh
 * @date 2022/6/20 下午8:08
 */
public class CheetRpcClientImpl implements CheetRpcClient {

    private ServerDiscovery discovery;


    @Override
    public void SetDiscovery(ServerDiscovery discovery) throws Exception {
        this.discovery=discovery;

    }

    @Override
    public Object Call(String prifix,String method,Object... arg) {
        try {
            String callName=String.format("%s.%s",prifix,method);

            return this.discovery.getNettyClient().SyncCall(callName,arg);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
