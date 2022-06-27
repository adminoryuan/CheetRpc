package com.cheet.discovery.impl;

import com.cheet.Entity.CheetAddr;
import com.cheet.Entity.ZookeeperConfig;
import com.cheet.discovery.ServerDiscovery;
import com.cheet.netty.client.NettyClient;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午8:19
 */
public class ZookeeperDisovery implements ServerDiscovery {

    private static ZookeeperConfig zkServerAddr;

    private NettyClient clientHash[];
    public ZookeeperDisovery(ZookeeperConfig zkConfig) {
        zkServerAddr=zkConfig;
    }

    @Override
    public NettyClient getNettyClient() {
        return null;
    }
}
