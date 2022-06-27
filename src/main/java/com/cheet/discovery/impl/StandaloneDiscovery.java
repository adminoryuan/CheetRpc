package com.cheet.discovery.impl;

import com.cheet.discovery.ServerDiscovery;

import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午8:16
 */
public class StandaloneDiscovery implements ServerDiscovery {

    private  String server_addr;

    public StandaloneDiscovery(String server_addr,int port) {
        this.server_addr=server_addr;
    }

    @Override
    public String getServerAddr() {
        return server_addr;
    }
}
