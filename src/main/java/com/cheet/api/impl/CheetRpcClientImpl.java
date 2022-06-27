package com.cheet.api.impl;

import com.cheet.api.CheetRpcClient;
import com.cheet.discovery.ServerDiscovery;
import com.cheet.netty.client.NettyClient;
import io.netty.channel.Channel;

import java.lang.reflect.Method;

/**
 * @author yh
 * @date 2022/6/20 下午8:08
 */
public class CheetRpcClientImpl implements CheetRpcClient {

    private ServerDiscovery discovery;


    @Override
    public void Connect(ServerDiscovery discovery) throws Exception {
        this.discovery=discovery;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    discovery
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        Thread.sleep(1000);
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
