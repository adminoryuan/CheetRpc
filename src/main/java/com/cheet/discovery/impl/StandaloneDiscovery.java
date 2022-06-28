package com.cheet.discovery.impl;

import com.cheet.discovery.ServerDiscovery;
import com.cheet.netty.client.NettyClient;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午8:16
 */
public class StandaloneDiscovery implements ServerDiscovery {

    private  NettyClient client;

    public StandaloneDiscovery(String server_addr,int port) throws Exception {
        client=new NettyClient();
        client.Dial(server_addr,port);

    }

    @Override
    public NettyClient getNettyClient() {
        return client;
    }
}
