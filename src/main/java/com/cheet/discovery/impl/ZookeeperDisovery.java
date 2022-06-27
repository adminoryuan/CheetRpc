package com.cheet.discovery.impl;

import com.cheet.Entity.CheetAddr;
import com.cheet.Entity.ZookeeperConfig;
import com.cheet.discovery.ServerDiscovery;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午8:19
 */
public class ZookeeperDisovery implements ServerDiscovery {

    private static ZookeeperConfig zkServerAddr;

    public ZookeeperDisovery(ZookeeperConfig zkConfig) {
        zkServerAddr=zkConfig;
    }

    @Override
    public CheetAddr getServerAddr() {
        return null;
    }
}
