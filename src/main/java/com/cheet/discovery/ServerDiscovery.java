package com.cheet.discovery;

import com.cheet.Entity.CheetAddr;
import com.cheet.netty.client.NettyClient;

/**
 * @author yh
 * @date 2022/6/27 下午8:15
 */
public interface ServerDiscovery {
    NettyClient getNettyClient();
}
